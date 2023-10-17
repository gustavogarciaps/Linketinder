package model


import groovy.transform.ToString

import java.time.LocalDate

@ToString
class Empresa extends Pessoa {

    private String razaoSocial;
    private LocalDate dataFundacao;
    private ArrayList<Competencia> vagas;

    Empresa() {}

    Empresa(Integer id) {
        super(id)
    }

    Empresa(Integer id,
            String razaoSocial,
            String inscricao,
            String CEP, String cidade,
            String pais,
            String descricao,
            String dataFundacao,
            List<Vaga> vagas) {

        super(id, inscricao, CEP, cidade, pais, descricao)

        this.razaoSocial = razaoSocial;
        this.dataFundacao = dataFundacao;
        this.vagas = vagas;
    }

    String getRazaoSocial() {
        return razaoSocial
    }

    void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial
    }

    LocalDate getDataFundacao() {
        return dataFundacao
    }

    void setDataFundacao(LocalDate dataFundacao) {
        this.dataFundacao = dataFundacao
    }

    ArrayList<Vaga> getVagas() {
        return vagas
    }

    void setVagas(ArrayList<Vaga> vagas) {
        this.vagas = vagas
    }
}
