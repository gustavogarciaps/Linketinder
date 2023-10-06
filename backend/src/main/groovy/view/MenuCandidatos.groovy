package view

import database.CandidatoDAO
import model.Candidato
import services.FabricaCandidatos
import services.FabricaCandidatosCompetencias

import java.time.LocalDate

class MenuCandidatos {

    static private ArrayList<Candidato> candidatos;
    static private Scanner scanner = new Scanner(System.in)

    static void exibir() {

        def opcaoMenu = 0

        while (opcaoMenu != 6) {
            println "Menu de Opções:";
            println "1. Visualizar Candidatos";
            println "2. Cadastrar Novo Candidato";
            println "3. Excluir Candidatos";
            println "4. Atualizar Candidatos";
            println "5. Vincular Competências à Candidatos";
            println "6. Sair"
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
                        criarCompetencias();
                        break
                    case 6:
                        println "Saindo..."
                        break
                    default:
                        println "Opção inválida. Escolha novamente."
                        break
                }
            } catch (Exception e) {
                println "Erro na execução do programa. " + e.getMessage()
            }
        }
    }

    static void carregar() {

        candidatos = CandidatoDAO.read()

        println("Candidatos Cadastrados:")
        println("-" * 80);
        println("-" * 80);
        println("|id" + ("\t" * 2) + "|" + "nome" + ("\t" * 4))

        candidatos.each { c ->
            println("Candidato:")
            println("|" + c.getId() + ("\t" * 2) + "|" + c.getNome() + ("\t" * 4) + "|" + c.getFormacao() + ("\t" * 4))
            println("Competências:")
            c.getCompetencias().getCompetencia().each { competencia ->
                println("|" + competencia.getId() + ("\t" * 2) + "|" + competencia.getNome() + ("\t" * 4))
            }
        }

        println("-" * 80);
        println("-" * 80);
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

        try {
            FabricaCandidatos.criar(id, nome, sobrenome, cpf, descricao, cidade, cep, formacao, LocalDate.of(ano, mes, dia), linkedin)
            println("Candidato cadastrado com sucesso!")
        } catch (Exception e) {
            println("Erro ao cadastrar o candidato: " + e.getMessage())
        }
    }

    static void deletar() {
        println("Deletar Candidato: (Escreva EXIT para voltar)")
        carregar();
        println("Qual o código (id) do Candidato?");
        print("Id: ")
        def id = scanner.nextLine();
        if (id.equals("EXIT")) {
            return
        }
        println(FabricaCandidatos.deletar(Integer.parseInt(id)));
    }

    static void atualizar() {
        println("Alterar Dados do Candidato: (Escreva EXIT para voltar)")
        carregar()
        println("Qual o código (id) do candidato?")
        print("Id: ")
        def id = scanner.nextInt()
        scanner.nextLine()
        if (id.equals("EXIT")) {
            return
        }
        def candidato = CandidatoDAO.readOne(id)[0]

        print("Novo Nome: (${candidato.getNome()}) ")
        def nome = scanner.nextLine()
        if (nome.equals("EXIT")) {
            return
        }
        if (nome.isEmpty()) {
            nome = candidato.getNome()
        }

        print("Novo Sobrenome: (${candidato.getSobrenome()}) ")
        def sobrenome = scanner.nextLine()
        if (sobrenome.equals("EXIT")) {
            return
        }
        if (sobrenome.isEmpty()) {
            sobrenome = candidato.getSobrenome()
        }

        print("Novo CPF: (${candidato.getInscricao()}) ")
        def cpf = scanner.nextLine()
        if (cpf.equals("EXIT")) {
            return
        }
        if (cpf.isEmpty()) {
            cpf = candidato.getInscricao()
        }

        print("Nova Descrição: (${candidato.getDescricao()}) ")
        def descricao = scanner.nextLine()
        if (descricao.equals("EXIT")) {
            return
        }
        if (descricao.isEmpty()) {
            descricao = candidato.getDescricao()
        }

        print("Nova Cidade: (${candidato.getCidade()}) ")
        def cidade = scanner.nextLine()
        if (cidade.equals("EXIT")) {
            return
        }
        if (cidade.isEmpty()) {
            cidade = candidato.getCidade()
        }
        print("Novo CEP: (${candidato.getCEP()}) ")
        def cep = scanner.nextLine()
        if (cep.equals("EXIT")) {
            return
        }
        if (cep.isEmpty()) {
            cep = candidato.getCEP()
        }

        print("Nova Formação: (${candidato.getFormacao()}) ")
        def formacao = scanner.nextLine()
        if (formacao.equals("EXIT")) {
            return
        }
        if (formacao.isEmpty()) {
            formacao = candidato.getFormacao()
        }

        print("Nova Data de Nascimento (AAAA-MM-DD): (${candidato.getDataNascimento()}) ")
        def dataNascimento = scanner.nextLine()
        if (dataNascimento.equals("EXIT")) {
            return
        }

        def dataNascimentoConvertida;

        if (dataNascimento.isEmpty()) {
            dataNascimentoConvertida = candidato.getDataNascimento()
        } else {
            def ano = Integer.parseInt(dataNascimento.split("-")[0])
            def mes = Integer.parseInt(dataNascimento.split("-")[1])
            def dia = Integer.parseInt(dataNascimento.split("-")[2])

            dataNascimentoConvertida = LocalDate.of(ano, mes, dia)
        }

        print("Novo LinkedIn: (${candidato.getLinkedin()}) ")
        def linkedin = scanner.nextLine()
        if (linkedin.equals("EXIT")) {
            return
        }
        if (linkedin.isEmpty()) {
            linkedin = candidato.getLinkedin()
        }

        try {
            FabricaCandidatos.atualizar(id, nome, sobrenome, cpf, descricao, Integer.parseInt(cidade), cep, formacao, dataNascimentoConvertida, linkedin)
            println("Candidato atualizado com sucesso!")
        } catch (Exception e) {
            println("Erro ao atualizar o candidato: " + e.getMessage())
        }
    }

    static void criarCompetencias() {
        println("Adicionar Competências: (Escreva EXIT para voltar)")
        MenuCompetencias.carregar();
        println("1. Vincular competências")
        println "2. Acessar o Menu de Competências"
        print "Opção: "
        def opcaoMenu = scanner.nextInt()
        scanner.nextLine()

        if (opcaoMenu == 1) {
            "Vincular Competência"
            print "Código do Candidato: "
            Integer candidatos_id = scanner.nextInt()
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

            FabricaCandidatosCompetencias.criar(candidatos_id, competencias)

        } else if (opcaoMenu == 2) {
            MenuCompetencias.exibir()
        }
    }

}
