package dominio;

import java.util.ArrayList;

public class Produto {
	private String codigo;
	private String nomeProduto;
	private float preco;
	private int pontos;
	private static ArrayList<Produto> listaProdutosSeparados;
	private String dataCompra;
	public String getDataCompra() {
		return dataCompra;
	}
	public void setDataCompra(String dataCompra) {
		this.dataCompra = dataCompra;
	}
	public int getPontosAdquiridos() {
		return pontosAdquiridos;
	}
	public void setPontosAdquiridos(int pontosAdquiridos) {
		this.pontosAdquiridos = pontosAdquiridos;
	}
	private int pontosAdquiridos;
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNomeProduto() {
		return nomeProduto;
	}
	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}
	public float getPreco() {
		return preco;
	}
	public void setPreco(float preco) {
		this.preco = preco;
	}
	
	public Produto(String nome,String codigo,float preco, int pontos){
		setNomeProduto(nome);
		setCodigo(codigo);
		setPreco(preco);
		setPontos(pontos);
	}
	public int getPontos() {
		return pontos;
	}
	public void setPontos(int pontos) {
		this.pontos = pontos;
	}
	public static ArrayList<Produto> getListaProdutosSeparados() {
		return listaProdutosSeparados;
	}
	public static void setListaProdutosSeparados(ArrayList<Produto> listaProdutosSeparados) {
		Produto.listaProdutosSeparados = listaProdutosSeparados;
	}
}
