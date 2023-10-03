package br.com.linketinder.model

import br.com.linketinder.services.GerenciamentoVaga
import groovy.transform.ToString

@ToString
class Empresa extends Pessoa{

    private GerenciamentoVaga vagas;

    Empresa(){}

    Empresa(String nome, String email, String inscricao, String CEP, String estado, String pais, String descricao) {
        super(nome, email, inscricao, CEP, estado, pais, descricao)
        vagas = new GerenciamentoVaga();
    }

    @Override
    public String toString() {
        return "Empresa ${super.toString()} }";
    }
}
