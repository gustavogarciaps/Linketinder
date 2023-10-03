package br.com.linketinder.services

import br.com.linketinder.model.Vaga

class GerenciamentoVaga {

    ArrayList<Vaga> vagas;

    public GerenciamentoVaga(){
        this.vagas = new ArrayList<>();
    }

    def setVagas(Vaga v){
        this.vagas.add(v);
    }

    def getVagas(){
        return this.vagas;
    }


}
