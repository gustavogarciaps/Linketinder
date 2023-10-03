package br.com.linketinder.model

import br.com.linketinder.services.GerenciamentoCompetencia
import groovy.transform.ToString

import java.time.LocalDate

@ToString
class Candidato extends Pessoa{

    private String linkedin;
    private LocalDate dataNascimento = null;
    private String formacao;
    private GerenciamentoCompetencia competencias;

    Candidato(){}

    Candidato(
            String nome,
            String email,
            String inscricao,
            String CEP,
            String estado,
            String pais,
            String descricao,
            String linkedin,
            LocalDate dataNascimento,
            String formacao
    ) {
        super(nome, email, inscricao, CEP, estado, pais, descricao)
        this.linkedin = linkedin;
        this.dataNascimento = dataNascimento;
        this.formacao = formacao;
        competencias = new GerenciamentoCompetencia();
    }

    def atualizarDataNascimento(){

        def dataAtual = LocalDate.now()

        int anos = dataAtual.year - dataNascimento.year
        int meses = dataAtual.month.value - dataNascimento.month.value
        int dias = dataAtual.dayOfMonth - dataNascimento.dayOfMonth

        if (meses < 0 || (meses == 0 && dias < 0)) {
            anos --
        }

        return anos
    }

    def getIdade(){
        return this.dataNascimento == null ? 0 : atualizarDataNascimento()
    }

    @Override
    String toString() {
        return "Candidato ${super.toString()} idade=${getIdade()} }"
    }

}
