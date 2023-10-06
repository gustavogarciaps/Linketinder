package services

import model.Vaga

class GerenciamentoVaga {

    ArrayList<Vaga> vagas;

    GerenciamentoVaga(){
        this.vagas = new ArrayList<>();
    }

    def setVagas(Vaga v){
        this.vagas.add(v);
    }

    def getVagas(){
        return this.vagas;
    }


}
