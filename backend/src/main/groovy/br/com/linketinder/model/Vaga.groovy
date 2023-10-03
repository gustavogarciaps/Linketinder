package br.com.linketinder.model

import br.com.linketinder.services.GerenciamentoCompetencia

import java.time.LocalDate

class Vaga {

    private String titulo;
    private String descricao;
    private LocalDate dataCriacao;
    private GerenciamentoCompetencia competencias;

    public Vaga(){}

    public Vaga(
            titulo,
            descricao
    ){
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataCriacao = LocalDate.now();
        competencias = new Competencia();
    }

    String getTitulo() {
        return titulo
    }

    void setTitulo(String titulo) {
        this.titulo = titulo
    }

    String getDescricao() {
        return descricao
    }

    void setDescricao(String descricao) {
        this.descricao = descricao
    }

    LocalDate getDataCriacao() {
        return dataCriacao
    }

    void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao
    }

    GerenciamentoCompetencia getCompetencias() {
        return competencias
    }

    void setCompetencias(GerenciamentoCompetencia competencias) {
        this.competencias = competencias
    }
}
