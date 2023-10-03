package br.com.linketinder.services

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
        c.setNome(s.toUpperCase())
        this.competencia.add(c)
    }

    @Override
    public String toString() {
        return this.competencia.each { competencia ->
            competencia
        }
    }

}
