package br.com.linketinder.model

import groovy.transform.ToString

@ToString
class Competencia {

    private Integer id;
    private String nome;

    Competencia(){}

    Competencia(
            Integer id,
            String nome
    ){
        this.id = id;
        this.nome = nome;
    }

    Integer getId() {
        return id
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


