package dominio;

public class Compra {
	private int qtdProdutos;
	private Produto produto;
	private static Compra compraAtual;
	public Compra(int qtdProdutos, Produto produto) {
		super();
		this.qtdProdutos = qtdProdutos;
		this.produto = produto;
	}
	/**
	 * @return the qtdProdutos
	 */
	public int getQtdProdutos() {
		return qtdProdutos;
	}
	/**
	 * @param qtdProdutos the qtdProdutos to set
	 */
	public void setQtdProdutos(int qtdProdutos) {
		this.qtdProdutos = qtdProdutos;
	}
	/**
	 * @return the produto
	 */
	public Produto getProduto() {
		return produto;
	}
	/**
	 * @param produto the produto to set
	 */
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	/**
	 * @return the compraAtual
	 */
	public static Compra getCompraAtual() {
		return compraAtual;
	}
	/**
	 * @param compraAtual the compraAtual to set
	 */
	public static void setCompraAtual(Compra compraAtual) {
		Compra.compraAtual = compraAtual;
	}
	
}
