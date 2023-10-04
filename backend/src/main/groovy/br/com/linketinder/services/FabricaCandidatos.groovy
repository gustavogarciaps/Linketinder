package br.com.linketinder.services

import br.com.linketinder.database.CompetenciaDAO
import br.com.linketinder.model.Competencia

class FabricaCandidatos {

	static String criar(String nome) {

		try {
			CompetenciaDAO.create(new Competencia(null, nome));
			return "Concluído";
		} catch (Exception e) {
			e.printStackTrace();
			return "Erro na requisição: " + e.getMessage();
		}
	}

	static String deletar(Integer id) {
		try {
			CompetenciaDAO.delete(new Competencia(id, null));
			return "Concluído";
		} catch (Exception e) {
			e.printStackTrace();
			return "Erro na requisição: " + e.getMessage();
		}
	}

	static String atualizar(Integer id, String nome){
		try {
			CompetenciaDAO.update(new Competencia(id, nome));
			return "Concluído";
		} catch (Exception e) {
			e.printStackTrace();
			return "Erro na requisição: " + e.getMessage;
		}
	}

}
