package br.com.linketinder.services

import br.com.linketinder.model.Competencia

class FabricaCompetencias {

    static ArrayList<Competencia> instanciarCompetencias () {
        ArrayList<Competencia> competencias = new ArrayList<>()

        competencias.add(new Competencia('JAVA'))
        competencias.add(new Competencia('PYTHON'))
        competencias.add(new Competencia('JAVASCRIPT'))
        competencias.add(new Competencia('GROOVY'))
        competencias.add(new Competencia('RUBY'))
        competencias.add(new Competencia('NODE'))
        competencias.add(new Competencia('TYPESCRIPT'))
        competencias.add(new Competencia('ANGULAR'))

        return competencias
    }
}
