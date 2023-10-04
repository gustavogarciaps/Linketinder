package br.com.linketinder.services

import br.com.linketinder.database.CompetenciaDAO
import br.com.linketinder.database.UsuarioDAO
import br.com.linketinder.model.Competencia
import br.com.linketinder.model.Usuario

class FabricaUsuarios {

	static String criar(String email, String senha) {

		try {
			UsuarioDAO.create(new Usuario(null, email, senha, null));
			return "Concluído";
		} catch (Exception e) {
			e.printStackTrace();
			return "Erro na requisição: " + e.getMessage();
		}
	}

	static String deletar(Integer id) {
		try {
			UsuarioDAO.delete(new Usuario(id, null, null, null));
			return "Concluído";
		} catch (Exception e) {
			e.printStackTrace();
			return "Erro na requisição: " + e.getMessage();
		}
	}

	static String atualizar(Integer id, String senha) {
		try {
			UsuarioDAO.update(new Usuario(id, null, senha, null));
			return "Concluído";
		} catch (Exception e) {
			e.printStackTrace();
			return "Erro na requisição: " + e.getMessage();
		}
	}

	static Integer validar(String email, String senha){
		try {
			UsuarioDAO.validar(new Usuario(null, email, senha, null));
			return "Concluído";
		} catch (Exception e) {
			e.printStackTrace();
			return "Erro na requisição: " + e.getMessage();
		}
	}
}
