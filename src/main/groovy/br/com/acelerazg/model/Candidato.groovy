package br.com.acelerazg.model

import br.com.acelerazg.controller.GerenciamentoCandidato
import groovy.transform.ToString

import java.time.LocalDate

@ToString
class Candidato extends Pessoa{

    LocalDate dataNascimento
    Integer idade

    Candidato(){}

    Candidato(String nome, String email, String inscricao, String CEP, String estado, String pais, String descricao, LocalDate dataNascimento) {
        super(nome, email, inscricao, CEP, estado, pais, descricao)
        this.dataNascimento = dataNascimento
    }

    def atualizarDataNascimento(){

        def dataAtual = LocalDate.now()

        int anos = dataAtual.year - dataNascimento.year
        int meses = dataAtual.month.value - dataNascimento.month.value
        int dias = dataAtual.dayOfMonth - dataNascimento.dayOfMonth

        if (meses < 0 || (meses == 0 && dias < 0)) {
            anos - 1
        }

        this.idade = anos
    }

    def getIdade(){
        atualizarDataNascimento()
        return this.idade
    }

    @Override
    String toString() {
        return "Candidato ${super.toString()} idade=${getIdade()} }"
    }

}
