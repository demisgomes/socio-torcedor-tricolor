package dominio;

public class Socio {
	
	private String nome,email,senha,confSenha,cpf,telefone,tipoSocio,sexo;
	private int pontos,ranking;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getTipoSocio() {
		return tipoSocio;
	}

	public void setTipoSocio(String tipoSocio) {
		this.tipoSocio = tipoSocio;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getConfSenha() {
		return confSenha;
	}

	public static Socio getSocioLogado() {
		return socioLogado;
	}

	public static void setSocioLogado(Socio socioLogado) {
		Socio.socioLogado = socioLogado;
	}

	public void setConfSenha(String confSenha) {
		this.confSenha = confSenha;
	}

	
	public Socio(String nome, String email, String senha, String confSenha,
			String cpf, String telefone, String tipoSocio, String sexo, int pontos, int ranking) {
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.confSenha = confSenha;
		this.cpf = cpf;
		this.telefone = telefone;
		this.tipoSocio = tipoSocio;
		this.sexo = sexo;
		this.pontos=pontos;
		this.ranking=ranking;
		
	}
	
	public int getPontos() {
		return pontos;
	}

	public void setPontos(int pontos) {
		this.pontos = pontos;
	}

	public int getRanking() {
		return ranking;
	}

	public void setRanking(int ranking) {
		this.ranking = ranking;
	}

	public static Socio socioLogado;
}
