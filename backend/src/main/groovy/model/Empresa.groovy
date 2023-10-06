package model

import services.GerenciamentoVaga
import groovy.transform.ToString

import java.time.LocalDate

@ToString
class Empresa extends Pessoa {

    private Integer id;
    private String razaoSocial;
    private LocalDate dataFundacao;
    private GerenciamentoVaga vagas;

    Empresa() {}

    Empresa(Integer id) {
        super(id)
    }

    Empresa(Integer id, String razaoSocial,  String inscricao, String CEP, String cidade, String pais, String descricao,String dataFundacao) {
        super(inscricao, CEP, cidade, pais, descricao)
        this.id = id;
        this.razaoSocial = razaoSocial;
        this.dataFundacao = dataFundacao;
        vagas = new GerenciamentoVaga();
    }

    Integer getId() {
        return id
    }

    void setId(Integer id) {
        this.id = id
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

    GerenciamentoVaga getVagas() {
        return vagas
    }

    void setVagas(GerenciamentoVaga vagas) {
        this.vagas = vagas
    }

}
