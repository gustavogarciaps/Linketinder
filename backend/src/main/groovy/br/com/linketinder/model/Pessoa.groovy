package br.com.linketinder.model

import br.com.linketinder.controller.GerenciamentoCompetencia
import groovy.transform.ToString

@ToString
class Pessoa implements PessoaHabilidades{

    String nome
    String email
    String inscricao
    String CEP
    String estado
    String pais
    String descricao
    GerenciamentoCompetencia competencia = new GerenciamentoCompetencia()

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
    def match(Object o) {
        return null
    }

    @Override
    def like() {
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
                "competencia=$competencia, " +
                "descricao='$descricao'" +
                " }"
    }

}
