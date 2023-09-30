package br.com.linketinder.controller

import br.com.linketinder.model.Empresa

class GerenciamentoEmpresa {

    ArrayList<Empresa> empresa = new ArrayList()

    def setEmpresa(Empresa e){
        this.empresa.add(e)
    }

    def getEmpresa(){
        return this.empresa.each { empresa ->
            println empresa
        }
    }

}

