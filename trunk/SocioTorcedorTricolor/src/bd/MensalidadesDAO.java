package bd;


import java.util.ArrayList;
import java.util.Date;

import dominio.Mensalidade;
import dominio.Produto;
import dominio.Socio;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class MensalidadesDAO {
	
	private final Context context;
	private final String tabelaProdutos="tabelaProdutos";
	private final String tabelaSocios="tabelaSocios";
	private final String tabelaPontosUsuario="tabelaPontosUsuario";
	private final String tabelaCartoes="tabelaCartoes";
	private static int versaoBD=1;
	private BDhelper bdHelper;
	private SQLiteDatabase bancoDados;
	private String nomeBanco="SocioTorcedorDB";
	// o context é a tela em que o banco será iniciado
	//construtor da classe
	public MensalidadesDAO (Context context){
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
				String sqlCartoes = "CREATE TABLE IF NOT EXISTS "+tabelaCartoes+" (_id INTEGER PRIMARY KEY, numero TEXT, codSeguranca TEXT, titular TEXT, vencimento TEXT, limite FLOAT, cpfTitular TEXT)";
				db.execSQL(sqlCartoes);
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
	
	public MensalidadesDAO openBd(){
		bdHelper= new BDhelper(context);
		bancoDados = bdHelper.getWritableDatabase();
		System.out.println(this+" "+"------------Opened Bd--------");
		return this;
	}
	public void closeBd(){
		bancoDados.close();
		System.out.println(this+" "+"------------Closed Bd--------");
	}
	
	public void inserirMensalidade(Socio socio, String mes, Banco banco){
		try{
			openBd();
			
			float preco = 0;
			if(socio.getTipoSocio().equals("Master")){
				preco=100;
			}
			
			if(socio.getTipoSocio().equals("Ouro")){
				preco=80;
			}

			if(socio.getTipoSocio().equals("Prata")){
				preco=40;
			}	

			if(socio.getTipoSocio().equals("Patrimonial")){
				preco=20;
			}
			String sql= "INSERT INTO tabelaMensalidades(preco, dataVencimento, emDia, idSocio) VALUES ('"+preco+"','05/"+mes+"','0','"+banco.usuarioGetId(Socio.getSocioLogado().getEmail())+"')";	
			bancoDados.execSQL(sql);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			closeBd();
		}
	}

}
