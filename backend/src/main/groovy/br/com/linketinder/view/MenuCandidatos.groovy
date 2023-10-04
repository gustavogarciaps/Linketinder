package br.com.linketinder.view

import br.com.linketinder.database.CandidatoDAO
import br.com.linketinder.database.CompetenciaDAO
import br.com.linketinder.model.Candidato
import br.com.linketinder.services.FabricaCompetencias


import java.time.LocalDate

class MenuCandidatos {

    static private ArrayList<Candidato> candidatos;
    static private Scanner scanner = new Scanner(System.in)

    static void exibir() {

        def opcaoMenu = 0


        while (opcaoMenu != 5) {
            println "Menu de Opções:";
            println "1. Visualizar Candidatos";
            println "2. Cadastrar Novo Candidatos";
            println "3. Excluir Candidatos";
            println "4. Atualizar Candidatos";
            println "5. Sair"
            print "Escolha uma opção: "

            try {
                opcaoMenu = scanner.nextInt()
                scanner.nextLine()

                switch (opcaoMenu) {
                    case 1:
                        carregar();
                        break;
                    case 2:
                        criar();
                        break;
                    case 3:
                        deletar();
                        break
                    case 4:
                        atualizar()
                        break
                    case 5:
                        CompetenciaDAO.close();
                        println "Saindo..."
                        break
                    default:
                        println "Opção inválida. Escolha novamente."
                        break
                }
            } catch (Exception e) {
                println "Opção inválida. Escolha novamente."
            }

        }

    }

    static void carregar() {

        candidatos = CandidatoDAO.read()

        println("Candidatos Cadastrados:")
        println("-" * 30);
        println("|id" + ("\t" * 2) + "|" + "nome" + ("\t" * 4))

        candidatos.each { c ->
            println("|" + c.getId() + ("\t" * 2) + "|" + c.getNome() + ("\t" * 4))
        }
    }

    static void criar() {
        println("Cadastro de Candidato:  (Escreva EXIT para voltar)")

        print("Id: ")
        def id = scanner.nextInt()
        scanner.nextLine()

        print("Nome: ")
        def nome = scanner.nextLine()
        if (nome.equals("EXIT")) {
            return
        }

        print("Sobrenome: ")
        def sobrenome = scanner.nextLine()
        if (sobrenome.equals("EXIT")) {
            return
        }

        print("CPF: ")
        def cpf = scanner.nextLine()
        if (cpf.equals("EXIT")) {
            return
        }

        print("Descrição: ")
        def descricao = scanner.nextLine()
        if (descricao.equals("EXIT")) {
            return
        }

        print("Cidade: ")

        def cidade = scanner.nextInt()
        scanner.nextLine()
        if (cidade.equals("EXIT")) {
            return
        }

        print("CEP: ")
        def cep = scanner.nextLine()
        if (cep.equals("EXIT")) {
            return
        }

        print("Formação: ")
        def formacao = scanner.nextLine()
        if (formacao.equals("EXIT")) {
            return
        }

        print("Data de Nascimento (AAAA-MM-DD): ")
        def dataNascimento = scanner.nextLine()
        if (dataNascimento.equals("EXIT")) {
            return
        }

        print("LinkedIn: ")
        def linkedin = scanner.nextLine()
        if (linkedin.equals("EXIT")) {
            return
        }

        def ano = Integer.parseInt(dataNascimento.split("-")[0])
        def mes = Integer.parseInt(dataNascimento.split("-")[1])
        def dia = Integer.parseInt(dataNascimento.split("-")[2])

        def candidato = new Candidato(
                id: id,
                nome: nome,
                sobrenome: sobrenome,
                inscricao: cpf,
                descricao: descricao,
                cidade: cidade,
                CEP: cep,
                formacao: formacao,
                dataNascimento: LocalDate.of(ano, mes, dia),
                linkedin: linkedin
        )

        try {
            CandidatoDAO.create(candidato)
            println("Candidato cadastrado com sucesso!")
        } catch (Exception e) {
            println("Erro ao cadastrar o candidato: " + e.getMessage())
        }
    }


    static void deletar() {
        println("Deletar Competência: (Escreva EXIT para voltar)")
        carregar();
        println("Qual o código (id) da competência?");
        print("Id: ")
        def id = scanner.nextLine();
        if (id.equals("EXIT")) {
            return
        }
        println(FabricaCompetencias.deletar(Integer.parseInt(id)));
    }

    static void atualizar() {
        println("Alterar Nome da Competência: (Escreva EXIT para voltar)")
        carregar();
        println("Qual o código (id) da competência?");
        print("Id: ")
        def id = scanner.nextLine();
        if (id.equals("EXIT")) {
            return
        }
        println("Qual o novo Nome?")
        print("Nome: ")
        def nome = scanner.nextLine();
        if (nome.equals("EXIT")) {
            return
        }
        println(FabricaCompetencias.atualizar(Integer.parseInt(id), nome));
    }
}
