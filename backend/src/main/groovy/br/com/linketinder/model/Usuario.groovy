package br.com.linketinder.model

import java.time.LocalDate

class Usuario {

	private Integer id;
	private String email;
	private String senha;
	private LocalDate data_criacao;

	Usuario(Integer id, String email, String senha, LocalDate data_criacao){
		this.id = id;
		this.email = email;
		this.senha = senha;
		this.data_criacao = data_criacao
	}

	Integer getId() {
		return id
	}

	void setId(Integer id) {
		this.id = id
	}

	String getEmail() {
		return email
	}

	void setEmail(String email) {
		this.email = email
	}

	String getSenha() {
		return senha
	}

	void setSenha(String senha) {
		this.senha = senha
	}

	LocalDate getData_criacao() {
		return data_criacao
	}

	void setData_criacao(LocalDate data_criacao) {
		this.data_criacao = data_criacao
	}
}
