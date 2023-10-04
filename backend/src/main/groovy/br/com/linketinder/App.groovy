package br.com.linketinder

import br.com.linketinder.database.CompetenciaDAO
import br.com.linketinder.model.Competencia
import br.com.linketinder.services.FabricaCompetencias
import br.com.linketinder.view.MenuCandidatos
import br.com.linketinder.view.MenuCompetencias
import br.com.linketinder.view.MenuInicial
import br.com.linketinder.view.MenuPrincipal

class App {

    static void main(args){

        /*
        competencias.each {competencia ->
            CompetenciaDAO.create(competencia)
        }
*/
        //MenuInicial.exibir()
        MenuCandidatos.exibir()


    }
}
