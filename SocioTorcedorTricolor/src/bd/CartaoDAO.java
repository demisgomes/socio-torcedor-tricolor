package bd;

import java.util.ArrayList;

import dominio.Cartao;
import dominio.Produto;
import dominio.Socio;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class CartaoDAO {
	
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

	public CartaoDAO (Context context){
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
	
	public CartaoDAO openBd(){
		bdHelper= new BDhelper(context);
		bancoDados = bdHelper.getWritableDatabase();
		System.out.println(this+" "+"------------Opened Bd--------");
		return this;
	}
	public void closeBd(){
		bancoDados.close();
		System.out.println(this+" "+"------------Closed Bd--------");
	}
	
	public void inserirCartoes(){
		
		
	}
	
	public ArrayList <Cartao> carregueListaProdutos(){
		ArrayList <Cartao> ListaDeCartoes=new ArrayList<Cartao>();
		ListaDeCartoes.add(new Cartao("4984464042794074", "633", "DEMIS MOACIR GOMES", "09/16", 400, "10292347448"));
		ListaDeCartoes.add(new Cartao("1029234744835104", "123", "LUCAS VICTOR GOMES", "03/17", 256, "10292347484"));
		ListaDeCartoes.add(new Cartao("9999999999999911", "911", "MOACIR AMARO GOMES", "06/18", 600, "37181211404"));
		ListaDeCartoes.add(new Cartao("1111111111111111", "111", "ALDA MARIA DA SILVA", "02/16", 2683,"12131415161"));
		ListaDeCartoes.add(new Cartao("7897894564561231", "789", "CHIRISTIAN SPINELLI", "04/18", 3435, "78978945612"));
		ListaDeCartoes.add(new Cartao("9879876546543213", "944", "IOHANSON CASSIANO", "08/15", 4761, "98798765432"));
		
		return ListaDeCartoes;
		
	}
	
	public String insertCartaoHelper(String numero, String codSeguranca, String titular, String vencimento,  float limite, String cpfTitular){
		String sql="INSERT INTO tabelaCartoes (numero, codSeguranca, titular, vencimento, limite, cpfTitular) VALUES ('"+numero+"','"+codSeguranca+"','"+titular+"','"+vencimento+"', '"+limite+"', '"+cpfTitular+"')";
		return sql;
	}
	
	public void populeCartoes(){
		try{
			openBd();
			ArrayList<Cartao> lista=carregueListaProdutos();
			for (int i=0;i<lista.size();i++){
				Cartao c=(Cartao)lista.get(i);		
				String sql=insertCartaoHelper(c.getNumero(), c.getCodSeguranca(), c.getTitular(), c.getVencimento(), c.getLimite(), c.getCpfTitular());
				bancoDados.execSQL(sql);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			closeBd();
		}
	}
	
	public Cartao validarCartao(Cartao cartao){
		try{
			openBd();
			
			String pesquisa="SELECT * FROM tabelaCartoes WHERE numero LIKE '"+cartao.getNumero()+"' AND codSeguranca LIKE'"+cartao.getCodSeguranca()+"' AND titular LIKE '"+cartao.getTitular()+"' AND vencimento LIKE '"+cartao.getVencimento()+"' AND cpfTitular LIKE '"+cartao.getCpfTitular()+"'";
			Cursor cursor= bancoDados.rawQuery(pesquisa, null);
			
			cursor.moveToNext();
			Cartao c= new Cartao(cursor.getString(cursor.getColumnIndex("numero")), cursor.getString(cursor.getColumnIndex("codSeguranca")), cursor.getString(cursor.getColumnIndex("titular")), cursor.getString(cursor.getColumnIndex("vencimento")), cursor.getFloat(cursor.getColumnIndex("limite")), cursor.getString(cursor.getColumnIndex("cpfTitular")), cursor.getInt(cursor.getColumnIndex("_id")));
			return c;
			
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
		finally{
			closeBd();
		}
	}
	
	public void updateLimite(Cartao cartao, float novoLimite){
		try{
			openBd();
			
			String pesquisa="UPDATE tabelaCartoes SET limite = '"+novoLimite+"' WHERE _id = '"+cartao.getIdUnico()+"'";
			bancoDados.execSQL(pesquisa);
				
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			closeBd();
		}
		
	}
	
	public boolean validarCartaoCadastro(String numCartao, String cpf){
		try{
			openBd();
			
			String pesquisa="SELECT * FROM tabelaCartoes WHERE numero LIKE '"+numCartao+"' AND cpfTitular LIKE '"+cpf+"'";
			Cursor cursor=bancoDados.rawQuery(pesquisa, null);
			
			cursor.moveToFirst();
			String num=cursor.getString(cursor.getColumnIndex("numero"));
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
		finally{
			closeBd();
		}
	}
	
	public void inserirIdSocioCartao(Socio socio){
		try{
			openBd();
			String pesquisaSocio="SELECT _id FROM tabelaSocios WHERE cpf like '"+socio.getCpf()+"'";
			Cursor cursor1=bancoDados.rawQuery(pesquisaSocio, null);
			cursor1.moveToFirst();
			int id=cursor1.getInt(cursor1.getColumnIndex("_id"));
			String pesquisa="UPDATE tabelaCartoes SET idSocio = '"+id+"' WHERE cpfTitular LIKE '"+socio.getCpf()+"'";
			bancoDados.execSQL(pesquisa);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			closeBd();
		}
	}
	
	public Cartao retornarCartao(String cpfSocio){
		try{
			openBd();
			
			String pesquisa="SELECT * FROM tabelaCartoes WHERE cpfTitular LIKE '"+cpfSocio+"'";
			Cursor cursor=bancoDados.rawQuery(pesquisa, null);
			
			cursor.moveToFirst();
			Cartao c= new Cartao(cursor.getString(cursor.getColumnIndex("numero")), cursor.getString(cursor.getColumnIndex("codSeguranca")), cursor.getString(cursor.getColumnIndex("titular")), cursor.getString(cursor.getColumnIndex("vencimento")), cursor.getFloat(cursor.getColumnIndex("limite")), cursor.getString(cursor.getColumnIndex("cpfTitular")), cursor.getInt(cursor.getColumnIndex("_id")));
			return c;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
		finally{
			closeBd();
		}
	}
}
