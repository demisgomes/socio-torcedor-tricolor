package bd;

import java.util.ArrayList;

import negocio.CalculoPontos;
import dominio.Produto;
import dominio.Socio;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Banco {
	
	private final Context context;
	private final String tabelaProdutos="tabelaProdutos";
	private final String tabelaSocios="tabelaSocios";
	private final String tabelaPontosUsuario="tabelaPontosUsuario";
	private final String tabelaCartoes="tabelaCartoes";
	private final String tabelaMensalidades="tabelaMensalidades";
	private final String tabelaCodigos="tabelaCodigos";
	private static int versaoBD=1;
	private BDhelper bdHelper;
	private SQLiteDatabase bancoDados;
	private String nomeBanco="SocioTorcedorDB";
	// o context é a tela em que o banco será iniciado
	//construtor da classe
	
	private ArrayList ListaDeProdutos;
	public Banco (Context context){
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
				String sql = "CREATE TABLE IF NOT EXISTS "+tabelaSocios+" (_id INTEGER PRIMARY KEY, nome TEXT, dataNascimento TEXT, email TEXT, senha TEXT, sexo TEXT, pontos INTEGER, ranking INTEGER, tipoSocio TEXT, cpf TEXT, telefone TEXT, situacao INTEGER, rua TEXT, numero TEXT, bairro TEXT, cidade TEXT, estado TEXT)";
				db.execSQL(sql);
				//TABELA DE EVENTOS (tabelaEventos)
				String sqlEvento = "CREATE TABLE IF NOT EXISTS "+tabelaProdutos+" (_id INTEGER PRIMARY KEY, codigo TEXT, nome TEXT, preco FLOAT, pontos INTEGER, adquirido INTEGER)";
				db.execSQL(sqlEvento);
				String sqlPontosUsuario = "CREATE TABLE IF NOT EXISTS "+tabelaPontosUsuario+" (_id INTEGER PRIMARY KEY, idProduto INTEGER, idUsuario INTEGER, pontosAdquiridos INTEGER, dataCompra TEXT, foiCompra INTEGER)";
				db.execSQL(sqlPontosUsuario);
				String sqlCartoes = "CREATE TABLE IF NOT EXISTS "+tabelaCartoes+" (_id INTEGER PRIMARY KEY, numero TEXT, codSeguranca TEXT, titular TEXT, vencimento TEXT, limite FLOAT, cpfTitular TEXT, idSocio INTEGER)";
				db.execSQL(sqlCartoes);
				String sqlMensalidades = "CREATE TABLE IF NOT EXISTS "+tabelaMensalidades+" (_id INTEGER PRIMARY KEY, preco FLOAT, dataVencimento TEXT, idSocio INTEGER, emDia INTEGER, dataPagamento TEXT, pontosAdquiridos INTEGER)";
				db.execSQL(sqlMensalidades);
				
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
	
	public Banco openBd(){
		bdHelper= new BDhelper(context);
		bancoDados = bdHelper.getWritableDatabase();
		System.out.println(this+" "+"------------Opened Bd--------");
		return this;
	}
	public void closeBd(){
		bancoDados.close();
		System.out.println(this+" "+"------------Closed Bd--------");
	}
	
	public ArrayList <Produto> carregueListaProdutos(){
		ListaDeProdutos=new ArrayList<Produto>();
		ListaDeProdutos.add(new Produto("Camisa Oficial", "G7R4-T9Y0", (float)189.90, 1500));
		ListaDeProdutos.add(new Produto("Calção Oficial", "G7R2-T4I0", (float)99.90, 1200));
		ListaDeProdutos.add(new Produto("Camisa Polo", "GRR4-T5Y0", (float)89.90, 1000));
		ListaDeProdutos.add(new Produto("Garrafa Oficial", "G7R4-T9Q0", (float)9.90, 500));
		ListaDeProdutos.add(new Produto("Ingresso- STA X CEA", "G7R2-T9Y0", (float)15.00, 600));
		ListaDeProdutos.add(new Produto("Camisa Oficial", "G7R3-T9Y0", (float)189.90, 1500));
		ListaDeProdutos.add(new Produto("Ingresso- STA X CEA", "G7R5-T9Y0", (float)15.00, 600));
		ListaDeProdutos.add(new Produto("Garrafa Oficial", "G7R6-T9Y0", (float)9.90, 500));
		ListaDeProdutos.add(new Produto("Ingresso- STA X CEA", "G7R7-T9Y0", (float)15.00, 600));
		ListaDeProdutos.add(new Produto("Camisa Oficial", "G7R8-T9Y0", (float)189.90, 1500));
		ListaDeProdutos.add(new Produto("c", "ZZZZ-FFFF", (float)189.90, 1000));
		ListaDeProdutos.add(new Produto("c", "ZZZZ-FFGG", (float)189.90, 1000));
		ListaDeProdutos.add(new Produto("c", "ZZZZ-FFHH", (float)189.90, 1000));
		
		return ListaDeProdutos;
		
	}
	
	public String insertProdutoHelper(String nome, String codigo, float preco, int pontos){
		String sql="INSERT INTO tabelaProdutos (codigo, nome, preco, pontos, adquirido) VALUES ('"+codigo+"','"+nome+"','"+preco+"','"+pontos+"', '0')";
		return sql;
	}
	
	
	
	public void populeBanco(){
		try{
			openBd();
			ArrayList<Produto> lista=carregueListaProdutos();
			for (int i=0;i<lista.size();i++){
				Produto p=(Produto)lista.get(i);
				String sql=insertProdutoHelper(p.getNomeProduto(), p.getCodigo(), p.getPreco(), p.getPontos());
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
	
	public Produto retorneProduto(String codigo){
		try {
			Cursor cursor;
			openBd();
			String sql="SELECT * FROM tabelaProdutos WHERE codigo LIKE '"+codigo+"'";
			cursor=bancoDados.rawQuery(sql, null);
			cursor.moveToFirst();
			System.out.println(cursor.getCount());
			System.out.println(cursor.getString(cursor.getColumnIndex("nome")));
			Produto p=new Produto(cursor.getString(cursor.getColumnIndex("nome")), cursor.getString(cursor.getColumnIndex("codigo")), cursor.getFloat(cursor.getColumnIndex("preco")), cursor.getInt(cursor.getColumnIndex("pontos")));
			return p;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
		finally{
			closeBd();
		}
	}
	
	//Método para retornar sem abrir ou fechar a conexão.
	//para métodos que usem esta característica
	public Produto retorneProduto(String codigo, String semFecharEAbrir){
		try {
			Cursor cursor;
			String sql="SELECT * FROM tabelaProdutos WHERE codigo LIKE '"+codigo+"'";
			cursor=bancoDados.rawQuery(sql, null);
			cursor.moveToFirst();
			System.out.println(cursor.getCount());
			System.out.println(cursor.getString(cursor.getColumnIndex("nome")));
			Produto p=new Produto(cursor.getString(cursor.getColumnIndex("nome")), cursor.getString(cursor.getColumnIndex("codigo")), cursor.getFloat(cursor.getColumnIndex("preco")), cursor.getInt(cursor.getColumnIndex("pontos")));
			return p;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
		
	}
	
	public Produto retorneProduto(int id, String semAbrirEFechar){
		try {
			Cursor cursor2;
			openBd();
			String sql="SELECT * FROM tabelaProdutos WHERE _id LIKE '"+id+"'";
			cursor2=bancoDados.rawQuery(sql, null);
			cursor2.moveToFirst();
			System.out.println(cursor2.getCount());
			System.out.println(cursor2.getString(cursor2.getColumnIndex("nome")));
			Produto p=new Produto(cursor2.getString(cursor2.getColumnIndex("nome")), cursor2.getString(cursor2.getColumnIndex("codigo")), cursor2.getFloat(cursor2.getColumnIndex("preco")), cursor2.getInt(cursor2.getColumnIndex("pontos")));
			return p;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
		finally{
			closeBd();
		}
	}
	
	//retornar se o produto não for adquirido
	public Produto retorneProduto(String codigo, int adquirido){
		try {
			Cursor cursor2;
			openBd();
			String sql="SELECT * FROM tabelaProdutos WHERE codigo LIKE '"+codigo+"' AND adquirido = '0'";
			cursor2=bancoDados.rawQuery(sql, null);
			cursor2.moveToFirst();
			Produto p=new Produto(cursor2.getString(cursor2.getColumnIndex("nome")), cursor2.getString(cursor2.getColumnIndex("codigo")), cursor2.getFloat(cursor2.getColumnIndex("preco")), cursor2.getInt(cursor2.getColumnIndex("pontos")));
			return p;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
		finally{
			closeBd();
		}
	}
	
	
	
	public void editarSocio(Socio socio, int id){
		try{
			openBd();
			String update="UPDATE "+tabelaSocios+" SET nome='"+socio.getNome()+"', dataNascimento='26/11/2005', email='"+socio.getEmail()+"', senha='"+socio.getSenha()+"', sexo='"+socio.getSexo()+"', telefone='"+socio.getTelefone()+"' WHERE _id = '"+id+"'";
			bancoDados.execSQL(update);	
			
			Socio.setSocioLogado(socio);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			closeBd();
		}
	}
	
	public void excluirSocio(int id){
		try{
			openBd();
			String update="UPDATE "+tabelaSocios+" SET nome='', dataNascimento='', email='', senha='', sexo='', telefone='' WHERE _id = '"+id+"'";
			bancoDados.execSQL(update);	
			
			Socio.setSocioLogado(null);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			closeBd();
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
	
	public ArrayList <Produto> retorneListaProdutos(){
		try {
			Cursor cursor;
			openBd();
			String sql="SELECT * FROM tabelaProdutos";
			cursor=bancoDados.rawQuery(sql, null);
			cursor.moveToFirst();
			ArrayList<Produto> listaProdutos=new ArrayList<Produto>();
			for(int i=1;i<cursor.getCount();i++){
				Produto p=new Produto(cursor.getString(cursor.getColumnIndex("nome")), cursor.getString(cursor.getColumnIndex("codigo")), cursor.getFloat(cursor.getColumnIndex("preco")),cursor.getInt(cursor.getColumnIndex("pontos")));
				listaProdutos.add(p);
				if(i!=cursor.getCount()-1){
					cursor.moveToNext();
				}	
			}
			return listaProdutos;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
		finally{
			closeBd();
		}
	}
	
	
	
	public int conteSocios(){
		try {
			Cursor cursor;
			openBd();
			String sql="SELECT COUNT (*) FROM tabelaSocios";
			cursor=bancoDados.rawQuery(sql, null);
			cursor.moveToFirst();
			
			return cursor.getInt(0);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return 0;
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
	
	public int usuarioGetId(String email, String semAbrirOuFechar){
		try {
			Cursor cursor2;
			String sql="SELECT * FROM tabelaSocios WHERE email LIKE '"+email+"'";
			cursor2=bancoDados.rawQuery(sql, null);
			cursor2.moveToFirst();
			return cursor2.getInt(cursor2.getColumnIndex("_id"));
		} catch (Exception e) {
			// TODO: handle exception 
			e.printStackTrace();
			return 0;
		}
	}
	
	public void updatePontosSocio(Socio socio, int pontos){
		try{
			openBd();
			int novosPontos=socio.getPontos()+pontos;
			//String update="UPDATE "+tabelaSocios+" SET nome='"+socio.getNome()+"', dataNascimento='26/11/2005', email='"+socio.getEmail()+"', senha='"+socio.getSenha()+"', sexo='"+socio.getSenha()+"', pontos='"+novosPontos+"', ranking='"+socio.getRanking()+"', tipoSocio='"+socio.getTipoSocio()+"', cpf='"+socio.getCpf()+"', telefone='"+socio.getTelefone()+"'";
			String update= "UPDATE "+tabelaSocios+" SET pontos='"+novosPontos+"' WHERE _id LIKE '"+usuarioGetId(socio.getEmail())+"'";
			bancoDados.execSQL(update);
			validarLogin(socio.getEmail(), socio.getSenha());
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			closeBd();
		}
	}
	
	public void updatePontosSocio(Socio socio, int pontos, String semAbrirEFechar){
		try{
			int novosPontos=socio.getPontos()+pontos;
			//String update="UPDATE "+tabelaSocios+" SET nome='"+socio.getNome()+"', dataNascimento='26/11/2005', email='"+socio.getEmail()+"', senha='"+socio.getSenha()+"', sexo='"+socio.getSenha()+"', pontos='"+novosPontos+"', ranking='"+socio.getRanking()+"', tipoSocio='"+socio.getTipoSocio()+"', cpf='"+socio.getCpf()+"', telefone='"+socio.getTelefone()+"'";
			String update= "UPDATE "+tabelaSocios+" SET pontos='"+novosPontos+"' WHERE _id LIKE '"+usuarioGetId(socio.getEmail())+"'";
			bancoDados.execSQL(update);
			validarLogin(socio.getEmail(), socio.getSenha());
		}
		catch(Exception e){
			e.printStackTrace();
		}
	
	}
	
	public void inserirProdutos(){
		ListaDeProdutos=new ArrayList<Produto>();
		ListaDeProdutos.add(new Produto("Calção Oficial", "G7A2-T4I0", (float)99.90, 500));
		ListaDeProdutos.add(new Produto("Camisa Polo", "GRB4-T5Y0", (float)49.90, 800));
		ListaDeProdutos.add(new Produto("Garrafa Oficial", "G9R4-T9Q0", (float)9.90, 400));
		ListaDeProdutos.add(new Produto("Toalha Oficial", "G9R5-T9Y0", (float)19.90, 500));
		ListaDeProdutos.add(new Produto("Garrafa Oficial", "G9R6-T9Y0", (float)189.90, 400));
		ListaDeProdutos.add(new Produto("Garrafa Oficial", "G9R4-T7Q0", (float)9.90, 400));
		ListaDeProdutos.add(new Produto("Toalha Oficial", "G9R5-T7Y0", (float)19.90, 500));
		ListaDeProdutos.add(new Produto("Garrafa Oficial", "G9R6-T7Y0", (float)189.90, 400));
		try{
			openBd();
			ArrayList<Produto> lista=ListaDeProdutos;
			for (int i=0;i<lista.size();i++){
				Produto p=(Produto)lista.get(i);
				String sql=insertProdutoHelper(p.getNomeProduto(), p.getCodigo(), p.getPreco(), p.getPontos());
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
	
	public void deletarProduto(Produto produto){
		try {
			openBd();
			System.out.println(produto.getCodigo());
			String sql="DELETE FROM tabelaProdutos WHERE codigo LIKE '"+produto.getCodigo()+"'";
			bancoDados.execSQL(sql);
		} catch (Exception e) {
			// TODO: handle exception 
			e.printStackTrace();
		}
		finally{
			closeBd();
		}
	}
	
	public void deletarProduto(Produto produto, String semAbrireFechar){
		try {
			String sql="DELETE FROM tabelaProdutos WHERE codigo LIKE '"+produto.getCodigo()+"'";
			bancoDados.rawQuery(sql, null);
		} catch (Exception e) {
			// TODO: handle exception 
			e.printStackTrace();
		}
	}
	
	public ArrayList <Produto> retorneListaProdutosSeparados(){
		try {
			Cursor cursor;
			openBd();
			String sql="SELECT * FROM tabelaProdutos WHERE adquirido != 1 AND nome != 'c' GROUP BY nome";
			cursor=bancoDados.rawQuery(sql, null);
			cursor.moveToFirst();
			ArrayList<Produto> listaProdutos=new ArrayList<Produto>();
			for(int i=0;i<cursor.getCount();i++){
				Produto p=new Produto(cursor.getString(cursor.getColumnIndex("nome")), cursor.getString(cursor.getColumnIndex("codigo")), cursor.getFloat(cursor.getColumnIndex("preco")), cursor.getInt(cursor.getColumnIndex("pontos")));
				listaProdutos.add(p);
				System.out.println(p.getNomeProduto());
				if(i!=cursor.getCount()-1){
					cursor.moveToNext();
				}	
			}
			Produto.setListaProdutosSeparados(listaProdutos);
			return listaProdutos;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
		finally{
			closeBd();
		}
	}
	
	public ArrayList <Produto> retorneListaHistorico(Socio socio){
		try {
			Cursor cursor;
			openBd();
			String sql="SELECT idProduto, pontosAdquiridos, dataCompra FROM tabelaPontosUsuario WHERE idUsuario = '"+usuarioGetId(Socio.getSocioLogado().getEmail())+"' AND foiCompra = '1'";
			cursor=bancoDados.rawQuery(sql, null);
			cursor.moveToFirst();
			ArrayList<Produto> listaHistorico=new ArrayList<Produto>();
			for(int i=0;i<cursor.getCount();i++){
				//Produto p=new Produto(cursor.getString(cursor.getColumnIndex("nome")), cursor.getString(cursor.getColumnIndex("codigo")), cursor.getFloat(cursor.getColumnIndex("preco")), cursor.getInt(cursor.getColumnIndex("pontos")));
				Produto p=retorneProduto(cursor.getInt(cursor.getColumnIndex("idProduto")), "");
				p.setDataCompra(cursor.getString(cursor.getColumnIndex("dataCompra")));
				p.setPontosAdquiridos(cursor.getInt(cursor.getColumnIndex("pontosAdquiridos")));
				listaHistorico.add(p);
				if(i!=cursor.getCount()-1){
					cursor.moveToNext();
				}	
			}
			
			return listaHistorico;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
		finally{
			closeBd();
		}
	}
	
	public ArrayList <Produto> retorneListaHistorico(Socio socio, String pontos){
		try {
			Cursor cursor;
			openBd();
			String sql="SELECT idProduto, pontosAdquiridos, dataCompra FROM tabelaPontosUsuario WHERE idUsuario = '"+socio.getIdUnico()+"' AND foiCompra= '0'";
			cursor=bancoDados.rawQuery(sql, null);
			cursor.moveToFirst();
			ArrayList<Produto> listaHistorico=new ArrayList<Produto>();
			for(int i=0;i<cursor.getCount();i++){
				//Produto p=new Produto(cursor.getString(cursor.getColumnIndex("nome")), cursor.getString(cursor.getColumnIndex("codigo")), cursor.getFloat(cursor.getColumnIndex("preco")), cursor.getInt(cursor.getColumnIndex("pontos")));
				Produto p=retorneProduto(cursor.getInt(cursor.getColumnIndex("idProduto")), "");
				p.setDataCompra(cursor.getString(cursor.getColumnIndex("dataCompra")));
				p.setPontosAdquiridos(cursor.getInt(cursor.getColumnIndex("pontosAdquiridos")));
				//p.setPontos(0);
				listaHistorico.add(p);
				if(i!=cursor.getCount()-1){
					cursor.moveToNext();
				}	
			}
			
			return listaHistorico;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
		finally{
			closeBd();
		}
	}
	
	public int getCountProduto(String nomeProduto){
		try {
			Cursor cursor;
			openBd();
			String sql="SELECT COUNT(nome) FROM tabelaProdutos WHERE nome LIKE '"+nomeProduto+"' AND adquirido <> 1";
			cursor=bancoDados.rawQuery(sql, null);
			cursor.moveToFirst();
			
			return cursor.getInt(0);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return 0;
		}
		finally{
			closeBd();
		}
	}
	
	//Compra
	public void inserirCompraHistorico(String nomeProduto, int qtd, Socio socio, String data){
		try {
			openBd();
			Cursor cursor;
			String sql="SELECT * FROM tabelaProdutos WHERE nome LIKE '"+nomeProduto+"' AND adquirido = '0'";
			cursor=bancoDados.rawQuery(sql, null);
			cursor.moveToFirst();
			for(int i=0;i<qtd;i++){
				Produto p=new Produto(cursor.getString(cursor.getColumnIndex("nome")), cursor.getString(cursor.getColumnIndex("codigo")), cursor.getFloat(cursor.getColumnIndex("preco")), cursor.getInt(cursor.getColumnIndex("pontos")));
				String update= "UPDATE "+tabelaProdutos+" SET adquirido='1' WHERE _id LIKE '"+cursor.getInt(0)+"'";
				bancoDados.execSQL(update);
				//CalculoPontos calculo =new CalculoPontos(null, socio, p.getPreco());
				String inserePontosUsuario="INSERT INTO "+tabelaPontosUsuario+"(idProduto, idUsuario, pontosAdquiridos, dataCompra, foiCompra) VALUES ('"+cursor.getInt(0)+"', '"+usuarioGetId(socio.getEmail())+"', '0', '"+data+"', '0')";
				bancoDados.execSQL(inserePontosUsuario);
				//String updateUsuario= "UPDATE "+tabelaSocios+" SET pontos='"+(calculo.getPontos()+socio.getPontos())+"' WHERE _id LIKE '"+usuarioGetId(socio.getEmail())+"'";
				//bancoDados.execSQL(updateUsuario);
				if(i!=cursor.getCount()-1){
					cursor.moveToNext();
				}
				else{
					updatePontosSocio(Socio.getSocioLogado(), p.getPontos()*qtd, "");
				}
			}
			
		} catch (Exception e) {
			// TODO: handle exception 
			e.printStackTrace();
		}
		finally{
			closeBd();
		}
		
		
	}
	
	public int retorneIdProduto(Produto produto){
		try {
			Cursor cursor;
			openBd();
			String sql="SELECT _id FROM tabelaProdutos WHERE codigo LIKE '"+produto.getCodigo()+"'";
			cursor=bancoDados.rawQuery(sql, null);
			cursor.moveToFirst();
			
			return cursor.getInt((cursor.getColumnIndex("_id")));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return 0;
		}
		finally{
			closeBd();
		}
	}
	
	public int retorneIdProduto(Produto produto, String semAbrirEFechar){
		try {
			Cursor cursor;
			String sql="SELECT _id FROM tabelaProdutos WHERE codigo LIKE '"+produto.getCodigo()+"' AND adquirido <> 1";
			cursor=bancoDados.rawQuery(sql, null);
			cursor.moveToFirst();
			
			return cursor.getInt(0);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return 0;
		}
	}
	public void inserirCompraHistorico(Produto produto, Socio socio, String data, int pontos){
		try {
			openBd();
			String update= "UPDATE "+tabelaProdutos+" SET adquirido='1' WHERE codigo LIKE '"+produto.getCodigo()+"'";
			bancoDados.execSQL(update);
			String inserePontosUsuario="INSERT INTO "+tabelaPontosUsuario+"(idProduto, idUsuario, pontosAdquiridos, dataCompra, foiCompra) VALUES ('"+retorneIdProduto(produto)+"', '"+usuarioGetId(socio.getEmail())+"', '"+pontos+"', '"+data+"', '0')";
			bancoDados.execSQL(inserePontosUsuario);
			
		} catch (Exception e) {
			// TODO: handle exception 
			e.printStackTrace();
		}
		finally{
			closeBd();
		}
		
		
	}
	
	
	
	public void inserirCompraHistoricoDinheiro(String nomeProduto, int qtd, Socio socio, String data){
		try {
			openBd();
			Cursor cursor;
			String sql="SELECT * FROM tabelaProdutos WHERE nome LIKE '"+nomeProduto+"' AND adquirido = '0'";
			cursor=bancoDados.rawQuery(sql, null);
			cursor.moveToFirst();
			for(int i=0;i<qtd;i++){
				Produto p=new Produto(cursor.getString(cursor.getColumnIndex("nome")), cursor.getString(cursor.getColumnIndex("codigo")), cursor.getFloat(cursor.getColumnIndex("preco")), cursor.getInt(cursor.getColumnIndex("pontos")));
				String update= "UPDATE "+tabelaProdutos+" SET adquirido='1', preco = '"+(p.getPreco())+"' WHERE _id LIKE '"+cursor.getInt(0)+"'";
				bancoDados.execSQL(update);
				CalculoPontos calculo =new CalculoPontos(socio, p.getPreco());
				String inserePontosUsuario="INSERT INTO "+tabelaPontosUsuario+"(idProduto, idUsuario, pontosAdquiridos, dataCompra, foiCompra) VALUES ('"+cursor.getInt(0)+"', '"+socio.getIdUnico()+"','"+calculo.getPontos()+"' , '"+data+"', '1')";
				bancoDados.execSQL(inserePontosUsuario);
				String updateUsuario= "UPDATE "+tabelaSocios+" SET pontos='"+(calculo.getPontos()+socio.getPontos())+"' WHERE _id LIKE '"+socio.getIdUnico()+"'";
				bancoDados.execSQL(updateUsuario);
				if(i!=cursor.getCount()-1){
					cursor.moveToNext();
				}
				else{
					updatePontosSocio(Socio.getSocioLogado(), p.getPontos()*qtd, "");
				}
			}
			
		} catch (Exception e) {
			// TODO: handle exception 
			e.printStackTrace();
		}
		finally{
			closeBd();
		}
	
	}
	
}
