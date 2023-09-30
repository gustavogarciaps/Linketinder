package br.com.linketinder.controller

import br.com.linketinder.model.Competencia
import groovy.transform.ToString

@ToString
class GerenciamentoCompetencia {

    ArrayList<Competencia> competencia = new ArrayList<>()

    def setCompetencia(ArrayList competencias) {
        this.competencia = competencias
    }

    def setCompetencia(String s){
        def c = new Competencia()
        c.setDescricao(s.toUpperCase())
        this.competencia.add(c)
    }

    @Override
    public String toString() {
        return this.competencia.each { competencia ->
            competencia
        }
    }

}
