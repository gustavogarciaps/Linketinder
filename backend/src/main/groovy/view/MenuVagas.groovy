package view

import dao.VagaCompetenciaDAO
import dao.VagaDAO
import model.Empresa
import model.Vaga

class MenuVagas {

    static private ArrayList<Vaga> vagas;
    static private Scanner scanner = new Scanner(System.in)

    static void exibir() {

        def opcaoMenu = 0

        while (opcaoMenu != 6) {
            println "Menu de Opções:";
            println "1. Visualizar Vagas";
            println "2. Cadastrar Nova Vaga";
            println "3. Excluir Vaga";
            println "4. Atualizar Vaga";
            println "5. Vincular competências à Vaga";
            println "6. Voltar"
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
                        vincularCompetencias()
                        break
                    case 6:
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
            println("Vaga:")
            println("|" + v.getId() + ("\t" * 2) + "|" + v.getTitulo() + ("\t" * 6) + "|" + v.getEmpresa().getId() + ("\t" * 4))
            println("Competências:")

            v.getCompetencias().each { competencia ->
                println("|" + competencia.getId() + ("\t" * 2) + "|" + competencia.getNome() + ("\t" * 4))
            }
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
        println("Alterar Dados da Vaga: (Escreva EXIT para voltar)")
        carregar()
        println("Qual o código (id) da Vaga?")
        print("Id: ")
        def id = scanner.nextInt()
        scanner.nextLine()
        if (id.equals("EXIT")) {
            return
        }
        def vaga = VagaDAO.readOne(id)[0]

        print("Novo Título: (${vaga.getTitulo()}) ")
        def titulo = scanner.nextLine()
        if (titulo.equals("EXIT")) {
            return
        }
        if (titulo.isEmpty()) {
            titulo = vaga.getNome()
        }

        print("Nova Descrição: (${vaga.getDescricao()}) ")
        def descricao = scanner.nextLine()
        if (descricao.equals("EXIT")) {
            return
        }
        if (descricao.isEmpty()) {
            descricao = vaga.getDescricao()
        }

        print("Modalidade: (${vaga.getModalidade()}) ")
        def modalidade = scanner.nextLine()
        if (modalidade.equals("EXIT")) {
            return
        }
        if (modalidade.isEmpty()) {
            modalidade = vaga.getModalidade()
        }

        print("Cidade: (${vaga.getCidade()}) ")
        def cidade = scanner.nextLine()
        if (cidade.equals("EXIT")) {
            return
        }
        if (cidade.isEmpty()) {
            cidade = vaga.getCidade()
        }

        vaga.setTitulo(titulo);
        vaga.setDescricao(descricao);
        vaga.setModalidade(modalidade);
        vaga.setCidade(cidade);

        try {

            VagaDAO.update(vaga)

            println("Candidato atualizado com sucesso!")
        } catch (Exception e) {
            println("Erro ao atualizar o candidato: " + e.getMessage())
        }
    }

    static void vincularCompetencias() {
        println("Adicionar Competências: (Escreva EXIT para voltar)")
        MenuCompetencias.carregar();
        println("1. Vincular competências")
        println "2. Acessar o Menu de Competências"
        print "Opção: "
        def opcaoMenu = scanner.nextInt()
        scanner.nextLine()

        if (opcaoMenu == 1) {
            "Vincular Competência"
            print "Código da Vaga: "
            Integer vagas_id = scanner.nextInt()
            scanner.nextLine()
            println "Digite o código das Competências que serão adicionadas. "
            println "(Digite uma por vez (Para sair Digite 0)"
            ArrayList<Integer> competencias = new ArrayList<>();
            Integer competencias_id

            while (competencias_id != 0) {
                print "Competência: "
                competencias_id = scanner.nextInt()
                scanner.nextLine()
                if (competencias_id != 0) {
                    competencias.add(competencias_id)
                }
            }

            competencias.each { competencia ->
                VagaCompetenciaDAO.create(vagas_id, competencia)
            }

        } else if (opcaoMenu == 2) {
            MenuCompetencias.exibir()
        }
    }

}
