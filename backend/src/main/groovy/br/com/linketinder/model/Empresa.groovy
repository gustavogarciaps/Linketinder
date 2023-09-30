package br.com.linketinder.model

import groovy.transform.ToString

@ToString
class Empresa extends Pessoa{

    Empresa(){}

    Empresa(String nome, String email, String inscricao, String CEP, String estado, String pais, String descricao) {
        super(nome, email, inscricao, CEP, estado, pais, descricao)
    }

    @Override
    public String toString() {
        return "Empresa ${super.toString()} }";
    }
}
