package br.com.linketinder.model

import br.com.linketinder.services.GerenciamentoCompetencia
import groovy.transform.ToString

@ToString
class Pessoa implements PessoaHabilidades{

    private String nome
    private String email
    private String inscricao
    private String CEP
    private String estado
    private String pais
    private String descricao

    Pessoa(){}

    Pessoa(String nome, String email, String inscricao, String CEP, String estado, String pais, String descricao) {
        this.nome = nome
        this.email = email
        this.inscricao = inscricao
        this.CEP = CEP
        this.estado = estado
        this.pais = pais
        this.descricao = descricao
    }

    @Override
    def reagir(boolean reacao){
        return null
    }

    @Override
    String toString() {
        return "{ " +
                "nome='$nome', " +
                "email='$email', " +
                "inscricao='$inscricao', " +
                "CEP='$CEP', " +
                "estado='$estado', " +
                "pais='$pais', " +
                "descricao='$descricao'" +
                " }"
    }

}
