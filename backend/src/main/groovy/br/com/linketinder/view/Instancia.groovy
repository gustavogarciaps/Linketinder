package br.com.linketinder.view

import br.com.linketinder.services.GerenciamentoCandidato
import br.com.linketinder.services.GerenciamentoEmpresa
import br.com.linketinder.model.Candidato
import br.com.linketinder.model.Empresa

import java.time.LocalDate

GerenciamentoCandidato candidatos = new GerenciamentoCandidato()
GerenciamentoEmpresa empresas = new GerenciamentoEmpresa()

Candidato C1 = new Candidato("GUSTAVO","gustavo@gmail.com","12345678910","38200000","MG","BRASIL","SOU O GUSTAVO", LocalDate.of(2001,6,9))
C1.getCompetencia().setCompetencia(new ArrayList(["JAVA","PYTHON"]))

Candidato C2 = new Candidato("ANA CASTELA","ana@gmail.com","12345678910","38200000","MG","BRASIL","SOU A ANA", LocalDate.of(2003,6,9))
C2.getCompetencia().setCompetencia(new ArrayList(["JAVA","PYTHON"]))

Candidato C3 = new Candidato("LEONARDO","leonardo@gmail.com","12345678910","38200000","MG","BRASIL","SOU O LEONARDO", LocalDate.of(1960,6,9))
C3.getCompetencia().setCompetencia(new ArrayList(["JAVA","PYTHON"]))

Candidato C4 = new Candidato("GUSTTAVO LIMA","gusttavolima@gmail.com","12345678910","38200000","MG","BRASIL","SOU O GUSTTAVO LIMA", LocalDate.of(1990,6,9))
C4.getCompetencia().setCompetencia(new ArrayList(["JAVA","PYTHON"]))

Candidato C5 = new Candidato("VANESSA DA MATA","vanessadamata@gmail.com","12345678910","38200000","MG","BRASIL","SOU A VANESSA DA MATA", LocalDate.of(1970,6,9))
C5.getCompetencia().setCompetencia(new ArrayList(["JAVA","PYTHON"]))

candidatos.setCandidato(C1)
candidatos.setCandidato(C2)
candidatos.setCandidato(C3)
candidatos.setCandidato(C4)
candidatos.setCandidato(C5)

Empresa E1 = new Empresa("COCA-COLA","contato@cocacola.com","12345678910","38200000","MG","BRASIL","SOU A COCA-COLA")
E1.getCompetencia().setCompetencia(new ArrayList(["JAVA","PYTHON"]))

Empresa E2 = new Empresa("PEPSI","acontato@pepsi.com","12345678910","38200000","MG","BRASIL","SOU A PEPSI")
E2.getCompetencia().setCompetencia(new ArrayList(["JAVA","PYTHON"]))

Empresa E3 = new Empresa("NESTLE","contato@nestle.com","12345678910","38200000","MG","BRASIL","SOU A NESTLE")
E3.getCompetencia().setCompetencia(new ArrayList(["JAVA","PYTHON"]))

Empresa E4 = new Empresa("ELMA CHIPS","contato@elmachips.com","12345678910","38200000","MG","BRASIL","SOU A ELMA CHIPS")
E4.getCompetencia().setCompetencia(new ArrayList(["JAVA","PYTHON"]))

Empresa E5 = new Empresa("VASCONELOS","contato@vasconcelos.com","12345678910","38200000","MG","BRASIL","SOU O VASCONCELOS")
E5.getCompetencia().setCompetencia(new ArrayList(["JAVA","PYTHON"]))

empresas.setEmpresa(E1)
empresas.setEmpresa(E2)
empresas.setEmpresa(E3)
empresas.setEmpresa(E4)
empresas.setEmpresa(E5)

def scanner = new Scanner(System.in)

def opcao

while (opcao != 3) {
    println "Menu de Opções:"
    println "1. Listar Candidatos"
    println "2. Listar Empresas"
    println "3. Sair"
    print "Escolha uma opção: "

    try {
        opcao = scanner.nextInt()
    } catch (NoSuchElementException e) {
        println "Erro ao ler entrada do usuário: ${e.message}"
        opcao = 0
    }

    switch (opcao) {
        case 1:
            println "Listar Candidatos"
            candidatos.getCandidato()
            break
        case 2:
            println "Listar Empresas"
            empresas.getEmpresa()
            break
        case 3:
            println "Saindo..."
            break
        default:
            println "Opção inválida. Escolha novamente."
            break
    }
}
