package bd;

import java.util.ArrayList;

import dominio.Socio;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import bd.Banco.BDhelper;


public class SocioDAO {
	
	private final Context context;
	private final String tabelaProdutos="tabelaProdutos";
	private final String tabelaSocios="tabelaSocios";
	private final String tabelaPontosUsuario="tabelaPontosUsuario";
	private static int versaoBD=1;
	private BDhelper bdHelper;
	private SQLiteDatabase bancoDados;
	private String nomeBanco="SocioTorcedorDB";
	// o context é a tela em que o banco será iniciado
	//construtor da classe

	public SocioDAO (Context context){
		this.context=context;
	}
	
	class BDhelper extends SQLiteOpenHelper{

		public BDhelper(Context context) {
			super(context, nomeBanco, null, versaoBD);
			// TODO Auto-generated constructor stb
		}
		
		
		//método de BDHelper que cria o banco
		@Override
		public void onCreate(SQLiteDatabase db) {
				//TABELA DE USUÁRIOS (tabelaUsuarios)
				String sql = "CREATE TABLE IF NOT EXISTS "+tabelaSocios+" (_id INTEGER PRIMARY KEY, nome TEXT, dataNascimento TEXT, email TEXT, senha TEXT, sexo TEXT, pontos INTEGER, ranking INTEGER, tipoSocio TEXT, cpf TEXT, telefone TEXT)";
				db.execSQL(sql);
				//TABELA DE EVENTOS (tabelaEventos)
				String sqlEvento = "CREATE TABLE IF NOT EXISTS "+tabelaProdutos+" (_id INTEGER PRIMARY KEY, codigo TEXT, nome TEXT, preco FLOAT, pontos INTEGER, adquirido INTEGER)";
				db.execSQL(sqlEvento);
				String sqlPontosUsuario = "CREATE TABLE IF NOT EXISTS "+tabelaPontosUsuario+" (_id INTEGER PRIMARY KEY, idProduto INTEGER, idUsuario INTEGER, pontosAdquiridos INTEGER, dataCompra TEXT, foiCodigo INTEGER)";
				db.execSQL(sqlPontosUsuario);
		}

		
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			
				String sql ="DROP TABLE IF EXISTS "+tabelaSocios;
				db.execSQL(sql);
				
				String sql2 = "DROP TABLE IF EXISTS "+ tabelaProdutos;
				db.execSQL(sql2);
		}
		
		
	}
	
	public void criarBanco(){
		try {
			openBd();
			bdHelper.onCreate(bancoDados);
			System.out.println("banco Criado");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}finally{
			closeBd();
		}
	}
	
	public SocioDAO openBd(){
		bdHelper= new BDhelper(context);
		bancoDados = bdHelper.getWritableDatabase();
		System.out.println(this+" "+"------------Opened Bd--------");
		return this;
	}
	public void closeBd(){
		bancoDados.close();
		System.out.println(this+" "+"------------Closed Bd--------");
	}
	
	public void updatePontosSocio(Socio socio, int pontos){
		try{
			openBd();
			int novosPontos=socio.getPontos()+pontos;
			//String update="UPDATE "+tabelaSocios+" SET nome='"+socio.getNome()+"', dataNascimento='26/11/2005', email='"+socio.getEmail()+"', senha='"+socio.getSenha()+"', sexo='"+socio.getSenha()+"', pontos='"+novosPontos+"', ranking='"+socio.getRanking()+"', tipoSocio='"+socio.getTipoSocio()+"', cpf='"+socio.getCpf()+"', telefone='"+socio.getTelefone()+"'";
			String update= "UPDATE "+tabelaSocios+" SET pontos='"+novosPontos+"' WHERE _id LIKE '"+socio.getIdUnico()+"'";
			bancoDados.execSQL(update);
			validarLogin(socio.getEmail(), socio.getSenha(), "SemAbrirEFechar");
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			closeBd();
		}
	}
	
	public int usuarioGetId(String email){
		try {
			openBd();
			Cursor cursor;
			String sql="SELECT * FROM tabelaSocios WHERE email LIKE '"+email+"'";
			cursor=bancoDados.rawQuery(sql, null);
			cursor.moveToFirst();
			return cursor.getInt(cursor.getColumnIndex("_id"));
		} catch (Exception e) {
			// TODO: handle exception 
			e.printStackTrace();
			return 0;
		}
		finally{
			//closeBd();
		}
	}
	
	public boolean validarLogin(String email, String senha){
		try {
			openBd();
			Cursor cursor;
			String sql="SELECT * FROM tabelaSocios WHERE email LIKE '"+email+"' AND senha LIKE '"+senha+"'";
			cursor=bancoDados.rawQuery(sql, null);
			cursor.moveToFirst();
			Socio s=new Socio(cursor.getString(cursor.getColumnIndex("nome")), cursor.getString(cursor.getColumnIndex("email")), cursor.getString(cursor.getColumnIndex("senha")),cursor.getString(cursor.getColumnIndex("senha")), cursor.getString(cursor.getColumnIndex("cpf")), cursor.getString(cursor.getColumnIndex("telefone")),cursor.getString(cursor.getColumnIndex("tipoSocio")), cursor.getString(cursor.getColumnIndex("sexo")), cursor.getInt(cursor.getColumnIndex("pontos")), cursor.getInt(cursor.getColumnIndex("ranking")));
			s.setIdUnico(cursor.getInt(cursor.getColumnIndex("_id")));
			Socio.socioLogado=s;
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		finally{
			closeBd();
		}
	}
	
	public boolean validarLogin(String email, String senha, String semAbrirEFechar){
		try {
			Cursor cursor;
			String sql="SELECT * FROM tabelaSocios WHERE email LIKE '"+email+"' AND senha LIKE '"+senha+"'";
			cursor=bancoDados.rawQuery(sql, null);
			cursor.moveToFirst();
			Socio s=new Socio(cursor.getString(cursor.getColumnIndex("nome")), cursor.getString(cursor.getColumnIndex("email")), cursor.getString(cursor.getColumnIndex("senha")),cursor.getString(cursor.getColumnIndex("senha")), cursor.getString(cursor.getColumnIndex("cpf")), cursor.getString(cursor.getColumnIndex("telefone")),cursor.getString(cursor.getColumnIndex("tipoSocio")), cursor.getString(cursor.getColumnIndex("sexo")), cursor.getInt(cursor.getColumnIndex("pontos")), cursor.getInt(cursor.getColumnIndex("ranking")));
			s.setIdUnico(cursor.getInt(cursor.getColumnIndex("_id")));
			s.setSituacao(cursor.getInt(cursor.getColumnIndex("situacao")));
			Socio.socioLogado=s;
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}
	
	public void cadastrarSocio(Socio socio){
		try{
			openBd();
			String sql=insertSocioHelper(socio.getNome(), socio.getEmail(), socio.getSenha(), socio.getConfSenha(), socio.getCpf(), socio.getTelefone(), socio.getTipoSocio(), socio.getSexo());
			bancoDados.execSQL(sql);			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			closeBd();
		}
	}
	public String insertSocioHelper(String nome, String email, String senha, String confSenha,
			String cpf, String telefone, String tipoSocio, String sexo){
		String sql="INSERT INTO tabelaSocios (nome, dataNascimento, email, senha, sexo, pontos, ranking, tipoSocio, cpf, telefone, situacao) VALUES ('"+nome+"', '26/11/2005','"+email+"','"+senha+"', '"+sexo+"', '0', '0', '"+tipoSocio+"', '"+cpf+"', '"+telefone+"', '0')";
		return sql;
	}
}

