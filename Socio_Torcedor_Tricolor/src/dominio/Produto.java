package dominio;

public class Produto {
	private String codigo;
	private String nomeProduto;
	private float preco;
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
	
	public Produto(String nome,String codigo,float preco){
		setNomeProduto(nome);
		setCodigo(codigo);
		setPreco(preco);
	}
}
