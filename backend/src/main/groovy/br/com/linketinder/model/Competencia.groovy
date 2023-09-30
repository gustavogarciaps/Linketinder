package br.com.linketinder.model

import groovy.transform.ToString

@ToString
class Competencia {

    String descricao

    @Override
    public String toString() {
        return this.descricao
    }

}


