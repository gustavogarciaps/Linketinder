package br.com.linketinder.view

import br.com.linketinder.database.CompetenciaDAO
import br.com.linketinder.database.VagaDAO
import br.com.linketinder.model.Competencia
import br.com.linketinder.model.Empresa
import br.com.linketinder.model.Vaga
import br.com.linketinder.services.FabricaCompetencias

class MenuVagas {

    static private ArrayList<Vaga> vagas;
    static private Scanner scanner = new Scanner(System.in)

    static void exibir() {

        def opcaoMenu = 0

        while (opcaoMenu != 5) {
            println "Menu de Opções:";
            println "1. Visualizar Vagas";
            println "2. Cadastrar Nova Vaga";
            println "3. Excluir Vaga";
            println "4. Atualizar Vaga";
            println "5. Voltar"
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
                        println "Voltando..."
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

        vagas = VagaDAO.read()

        println("Vagas Cadastradas:")
        println("-" * 30);
        println("|id" + ("\t" * 2) + "|" + "Título" + ("\t" * 6) + "Empresa" + ("\t" * 4))

        vagas.each { v ->
            println("|" + v.getId() + ("\t" * 2) + "|" + v.getTitulo() + ("\t" * 6) + "|" + v.getEmpresa().getId() + ("\t" * 4))
        }
    }

    static void criar() {
        println("Cadastrar Nova Vaga:  (Escreva EXIT para voltar)");

        println("Qual o código da Empresa?");
        print("Código Empresa: ");
        def empresas_id = scanner.nextLine();
        if (empresas_id.equals("EXIT")) {
            return
        }

        println("Qual o nome da Vaga?");
        print("Título: ");
        def titulo = scanner.nextLine();
        if (titulo.equals("EXIT")) {
            return
        }

        print("Descrição: ");
        def descricao = scanner.nextLine();
        if (descricao.equals("EXIT")) {
            return
        }

        print("Modalidade: ");
        def modalidade = scanner.nextLine();
        if (modalidade.equals("EXIT")) {
            return
        }

        print("Cidade: ");
        def cidade = scanner.nextLine();
        if (cidade.equals("EXIT")) {
            return
        }

        Empresa empresa = new Empresa(id: Integer.parseInt(empresas_id))
        Vaga vaga = new Vaga(
                empresa: empresa,
                id: null,
                titulo: titulo,
                descricao: descricao,
                modalidade: Integer.parseInt(modalidade),
                cidade: Integer.parseInt(cidade)
        )

        println(VagaDAO.create(vaga));
    }

    static void deletar() {
        println("Deletar Vaga: (Escreva EXIT para voltar)")
        carregar();
        println("Qual o código (id) da vaga?");
        print("Id: ")
        def id = scanner.nextLine();
        if (id.equals("EXIT")) {
            return
        }

        def vaga = new Vaga(id: Integer.parseInt(id))

        VagaDAO.delete(vaga)
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
