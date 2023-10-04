package br.com.linketinder.model


import groovy.transform.ToString

@ToString
class Pessoa implements PessoaHabilidades {

	private Integer id;
	private String inscricao;
	private String CEP;
	private Integer cidade;
	private String pais;
	private String descricao;

	Pessoa() {}

	Pessoa(Integer id){
		this.id = id;
	}

	Pessoa(Integer id, String inscricao, String CEP, Integer cidade, String pais, String descricao) {
		this.id = id
		this.inscricao = inscricao
		this.CEP = CEP
		this.cidade = cidade
		this.pais = pais
		this.descricao = descricao
	}

	Integer getId() {
		return id
	}

	void setId(Integer id) {
		this.id = id
	}

	String getInscricao() {
		return inscricao
	}

	void setInscricao(String inscricao) {
		this.inscricao = inscricao
	}

	String getCEP() {
		return CEP
	}

	void setCEP(String CEP) {
		this.CEP = CEP
	}

	Integer getCidade() {
		return cidade
	}

	void setCidade(Integer estado) {
		this.cidade = estado
	}

	String getPais() {
		return pais
	}

	void setPais(String pais) {
		this.pais = pais
	}

	String getDescricao() {
		return descricao
	}

	void setDescricao(String descricao) {
		this.descricao = descricao
	}

	@Override
	def reagir(boolean reacao) {
		return null
	}

	@Override
	String toString() {
		return [this.inscricao, this.CEP, this.cidade, this.pais, this.descricao]
	}

}
