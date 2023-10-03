package br.com.linketinder

import br.com.linketinder.database.CompetenciaDAO
import br.com.linketinder.model.Competencia
import br.com.linketinder.services.FabricaCompetencias

class App {

    static void main(args){
        ArrayList<Competencia> competencias = FabricaCompetencias.instanciarCompetencias();

        println(CompetenciaDAO.read())

        print("Ola")
    }
}
