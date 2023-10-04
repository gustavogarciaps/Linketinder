package br.com.linketinder.services

import br.com.linketinder.database.CandidatoDAO
import br.com.linketinder.database.EmpresaDAO
import br.com.linketinder.model.Candidato
import br.com.linketinder.model.Empresa

import java.time.LocalDate

class FabricaEmpresas {

    static String criar(Integer id, String razaoSocial, String inscricao, String descricao, Integer cidade, String CEP, LocalDate dataFundacao) {
        try {
            Empresa empresa = new Empresa(
                    id: id,
                    razaoSocial: razaoSocial,
                    inscricao: inscricao,
                    CEP: CEP,
                    cidade: cidade,
                    pais: null,
                    descricao: descricao,
                    dataFundacao: dataFundacao);

            EmpresaDAO.create(empresa);
            return "Concluído";
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro na requisição: " + e.getMessage();
        }
    }


    static String deletar(Integer id) {
        try {
            CandidatoDAO.delete(new Candidato(id));
            return "Concluído";
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro na requisição: " + e.getMessage();
        }
    }

    static String atualizar(Integer id, String nome, String sobrenome, String inscricao, String descricao, Integer cidade, String CEP, String formacao, LocalDate dataNascimento, String linkedin) {
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
            println(candidato)
            CandidatoDAO.update(candidato);
            return "Concluído";
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro na requisição: " + e.getMessage();
        }
    }
}
