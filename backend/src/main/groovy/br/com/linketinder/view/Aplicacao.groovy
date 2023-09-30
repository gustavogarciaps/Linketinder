package br.com.linketinder.view

import br.com.linketinder.controller.GerenciamentoCandidato
import br.com.linketinder.controller.GerenciamentoEmpresa
import br.com.linketinder.model.Candidato
import br.com.linketinder.model.Empresa

import java.time.LocalDate
import java.time.format.DateTimeFormatter

class Aplicacao {

    static GerenciamentoCandidato candidatos = new GerenciamentoCandidato()
    static GerenciamentoEmpresa empresas = new GerenciamentoEmpresa()

    static Scanner scanner = new Scanner(System.in)

    static void main (args) {

        exibirMenu()

    }

    static void exibirMenu(){

        def opcao

        while (opcao != 5) {
            println "Menu de Opções:"
            println "1. Listar Candidatos"
            println "2. Listar Empresas"
            println "3. Adicionar Candidatos"
            println "4. Adicionar Empresas"
            println "5. Sair"
            print "Escolha uma opção: "

            opcao = scanner.nextInt()
            scanner.nextLine()

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
                    println "Adicionar Candidato"
                    formsCandidato(new Candidato())
                    break
                case 4:
                    println "Adicionar Empresa"
                    formsEmpresa(new Empresa())
                    break
                case 5:
                    println "Saindo..."
                    break
                default:
                    println "Opção inválida. Escolha novamente."
                    break
            }
        }
    }

    static void formsCandidato(Candidato candidato){
        print "Nome: "
        candidato.setNome(scanner.nextLine().toUpperCase())
        print "E-mail: "
        candidato.setEmail(scanner.nextLine().toUpperCase())
        print "CPF: "
        candidato.setInscricao(scanner.nextLine().toUpperCase())
        print "CEP: "
        candidato.setCEP(scanner.nextLine().toUpperCase())
        print "Estado (UF): "
        candidato.setEstado(scanner.nextLine().toUpperCase())
        print "País: "
        candidato.setPais(scanner.nextLine().toUpperCase())
        println "Lista de Competências (Digite 0 para finalizar)"

        while(true){

            print "Competência: "
            def competencia = scanner.nextLine()

            if(competencia.equals("0")){
                break
            }
            candidato.getCompetencia().setCompetencia(competencia)
        }

        print "Sua descrição: "
        candidato.setDescricao(scanner.nextLine())

        print "Data de Nascimento (dd/mm/aaaa): "
        LocalDate data = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        candidato.setDataNascimento(data)

        candidatos.setCandidato(candidato)
    }

    static void formsEmpresa(Empresa empresa){

        print "Nome: "
        empresa.setNome(scanner.nextLine().toUpperCase())
        print "E-mail: "
        empresa.setEmail(scanner.nextLine().toUpperCase())
        print "CNPJ: "
        empresa.setInscricao(scanner.nextLine().toUpperCase())
        print "CEP: "
        empresa.setCEP(scanner.nextLine().toUpperCase())
        print "Estado (UF): "
        empresa.setEstado(scanner.nextLine().toUpperCase())
        print "País: "
        empresa.setPais(scanner.nextLine().toUpperCase())
        println "Lista de Competências (Digite 0 para finalizar)"

        while(true){

            print "Competência: "
            def competencia = scanner.nextLine()

            if(competencia.equals("0")){
                break
            }
            empresa.getCompetencia().setCompetencia(competencia)
        }

        print "Sua descrição: "
        empresa.setDescricao(scanner.nextLine())

        empresas.setEmpresa(empresa)
    }
}
