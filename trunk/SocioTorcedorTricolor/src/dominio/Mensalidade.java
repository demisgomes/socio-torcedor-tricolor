package dominio;

public class Mensalidade {
	
	private float preco;
	private String dataVencimento;
	private int emDia;
	private int idUnico;
	private Socio socio;
	
	public Mensalidade(float preco, String dataVencimento, int emDia,
			int idUnico, Socio socio) {
		super();
		this.preco = preco;
		this.dataVencimento = dataVencimento;
		this.emDia = emDia;
		this.idUnico = idUnico;
		this.socio = socio;
	}
	public Mensalidade(float preco, String dataVencimento, int emDia) {
		super();
		this.preco = preco;
		this.dataVencimento = dataVencimento;
		this.emDia = emDia;
	}
	public Mensalidade(float preco, String dataVencimento, int emDia,
			Socio socio) {
		super();
		this.preco = preco;
		this.dataVencimento = dataVencimento;
		this.emDia = emDia;
		this.socio = socio;
	}
	public int getIdUnico() {
		return idUnico;
	}
	public void setIdUnico(int idUnico) {
		this.idUnico = idUnico;
	}
	public Socio getSocio() {
		return socio;
	}
	public void setSocio(Socio socio) {
		this.socio = socio;
	}
	public float getPreco() {
		return preco;
	}
	public void setPreco(float preco) {
		this.preco = preco;
	}
	public String getDataVencimento() {
		return dataVencimento;
	}
	public void setDataVencimento(String dataVencimento) {
		this.dataVencimento = dataVencimento;
	}
	public int getEmDia() {
		return emDia;
	}
	public void setEmDia(int emDia) {
		this.emDia = emDia;
	}

}
