package dominio;

public class Cartao {
	
	private String numero;
	private String codSeguranca;
	private String titular;
	private String vencimento;
	private String cpfTitular;
	private int idUnico;
	private float limite;
	
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getCodSeguranca() {
		return codSeguranca;
	}
	public void setCodSeguranca(String codSeguranca) {
		this.codSeguranca = codSeguranca;
	}
	public String getTitular() {
		return titular;
	}
	public void setTitular(String titular) {
		this.titular = titular;
	}
	public String getVencimento() {
		return vencimento;
	}
	public void setVencimento(String vencimento) {
		this.vencimento = vencimento;
	}
	public int getIdUnico() {
		return idUnico;
	}
	public void setIdUnico(int idUnico) {
		this.idUnico = idUnico;
	}
	public float getLimite() {
		return limite;
	}
	public void setLimite(float limite) {
		this.limite = limite;
	}
	public String getCpfTitular() {
		return cpfTitular;
	}
	public void setCpfTitular(String cpfTitular) {
		this.cpfTitular = cpfTitular;
	}
	public Cartao(String numero, String codSeguranca, String titular,
			String vencimento, float limite, String cpfTitular, int idUnico) {
		super();
		this.numero = numero;
		this.codSeguranca = codSeguranca;
		this.titular = titular;
		this.vencimento = vencimento;
		this.idUnico = idUnico;
		this.limite = limite;
		this.cpfTitular=cpfTitular;
	}
	
	public Cartao(String numero, String codSeguranca, String titular,
			String vencimento,  float limite, String cpfTitular) {
		super();
		this.numero = numero;
		this.codSeguranca = codSeguranca;
		this.titular = titular;
		this.vencimento = vencimento;
		this.limite = limite;
		this.cpfTitular=cpfTitular;
	}

}
