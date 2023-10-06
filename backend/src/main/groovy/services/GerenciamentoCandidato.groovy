package services

import model.Candidato

class GerenciamentoCandidato {

    ArrayList<Candidato> candidato = new ArrayList()

    def setCandidato(Candidato c){
        this.candidato.add(c)
    }

    def getCandidato(){
        return this.candidato.each { candidato ->
            println candidato
        }
    }

}
