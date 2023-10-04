package br.com.linketinder.services

import br.com.linketinder.database.CandidatoDAO
import br.com.linketinder.database.CompetenciaDAO
import br.com.linketinder.model.Candidato
import br.com.linketinder.model.Competencia

import java.time.LocalDate

class FabricaCandidatos {

    static String criar(String id, String nome, String sobrenome, String inscricao, String descricao, Integer cidade, String CEP, String formacao, LocalDate dataNascimento, String linkedin) {
        try {
            Candidato candidato = new Candidato(
                    id,
                    nome,
                    sobrenome,
                    inscricao,
                    descricao,
                    cidade,
                    CEP,
                    formacao,
                    dataNascimento,
                    linkedin,
                    null);
            CandidatoDAO.create(candidato);
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

    static String atualizar(Integer id, String nome) {
        try {
            CompetenciaDAO.update(new Competencia(id, nome));
            return "Concluído";
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro na requisição: " + e.getMessage;
        }
    }

}
