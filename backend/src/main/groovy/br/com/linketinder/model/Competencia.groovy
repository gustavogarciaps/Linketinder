package br.com.linketinder.model

import groovy.transform.ToString

@ToString
class Competencia {

    private String nome

    Competencia(
            String nome
    ){
        this.nome = nome;
    }

    String getNome() {
        return nome
    }

    void setNome(String nome) {
        this.nome = nome
    }

    @Override
    public String toString() {
        return this.nome
    }

}


