package br.com.linketinder.services

import br.com.linketinder.database.EmpresaDAO
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
            EmpresaDAO.delete(new Empresa(id: id));
            return "Concluído";
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro na requisição: " + e.getMessage();
        }
    }

    static String atualizar(Integer id, String razaoSocial, String inscricao, String descricao, Integer cidade, String CEP, LocalDate dataFundacao) {
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

            EmpresaDAO.update(empresa);
            return "Concluído";
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro na requisição: " + e.getMessage();
        }
    }
}
