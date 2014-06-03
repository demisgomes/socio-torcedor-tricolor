package dominio;

public class Socio {
	
	private String nome,email,senha,confSenha,cpf,telefone,tipoSocio,sexo;

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
			String cpf, String telefone, String tipoSocio, String sexo) {
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.confSenha = confSenha;
		this.cpf = cpf;
		this.telefone = telefone;
		this.tipoSocio = tipoSocio;
		this.sexo = sexo;
	}
	
	public static Socio socioLogado;
}
