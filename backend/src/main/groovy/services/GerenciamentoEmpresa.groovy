package services

import model.Empresa

class GerenciamentoEmpresa {

    ArrayList<Empresa> empresa = new ArrayList()

    def getEmpresa(){
        return this.empresa.each { empresa ->
            println empresa
        }
    }

}

