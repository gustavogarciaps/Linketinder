package br.com.linketinder.view

import br.com.linketinder.database.CompetenciaDAO

class MenuPrincipal {

    static Scanner scanner = new Scanner(System.in)

    static void exibir(){

        def opcaoMenu = 0

        while (opcaoMenu != 5) {

            println "Menu de Opções:"
            println "1. Listar Candidatos"
            println "2. Listar Empresas"
            println "3. Adicionar Candidatos"
            println "4. Adicionar Empresas"
            println "5. Sair"
            print "Escolha uma opção: "

            try {
                opcaoMenu = scanner.nextInt()
                scanner.nextLine()

                switch (opcaoMenu) {
                    case 1:
                        println "Cadastro de Competências"
                        println(CompetenciaDAO.read());
                        break
                    case 2:
                        println "Listar Empresas"
                        // Chame a função para listar empresas aqui
                        break
                    case 3:
                        println "Adicionar Candidato"
                        // Chame a função para adicionar candidatos aqui
                        break
                    case 4:
                        println "Adicionar Empresa"
                        // Chame a função para adicionar empresas aqui
                        break
                    case 5:
                        println "Saindo..."
                        break
                    default:
                        println "Opção inválida. Escolha novamente."
                        break
                }
            } catch (java.util.InputMismatchException e) {
                println "Entrada inválida. Por favor, insira um número válido."
                scanner.nextLine()
            }
        }
    }
}

