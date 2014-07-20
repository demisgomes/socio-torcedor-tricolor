package dominio;

public class Socio {
	
	private String nome,email,senha,confSenha,cpf,telefone,tipoSocio,sexo;
	private int pontos,ranking, idUnico, situacao;
	private String rua, num, bairro, cidade, estado;
	
	public static float taxaAdesao;
	
	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

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

	public int getIdUnico() {
		return idUnico;
	}

	public void setIdUnico(int idUnico) {
		this.idUnico = idUnico;
	}

	/**
	 * @return the situacao
	 */
	public int getSituacao() {
		return situacao;
	}

	/**
	 * @param situacao the situacao to set
	 */
	public void setSituacao(int situacao) {
		this.situacao = situacao;
	}

	public static Socio socioLogado;
}
