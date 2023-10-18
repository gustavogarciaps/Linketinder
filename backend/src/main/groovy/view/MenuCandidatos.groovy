package view

import dao.CandidatoCompetenciaDAO
import dao.CandidatoDAO
import model.Candidato

import java.time.LocalDate

class MenuCandidatos {

    static private ArrayList<Candidato> candidatos;
    static private Scanner scanner = new Scanner(System.in)

    static void exibir() {

        Integer opcaoMenu = 0

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
            c.getCompetencias().each { competencia ->
                println("|" + competencia.getId() + ("\t" * 2) + "|" + competencia.getNome() + ("\t" * 4))
            }
        }

        println("-" * 80);
        println("-" * 80);
    }

    static void criar() {
        println("Cadastro de Candidato:  (Escreva EXIT para voltar)")

        print("Id: ")
        int id = scanner.nextInt()
        scanner.nextLine()

        print("Nome: ")
        String nome = scanner.nextLine()
        if (nome.equals("EXIT")) {
            return
        }

        print("Sobrenome: ")
        String sobrenome = scanner.nextLine()
        if (sobrenome.equals("EXIT")) {
            return
        }

        print("CPF: ")
        String cpf = scanner.nextLine()
        if (cpf.equals("EXIT")) {
            return
        }

        print("Descrição: ")
        String descricao = scanner.nextLine()
        if (descricao.equals("EXIT")) {
            return
        }

        print("Cidade: ")
        int cidade = scanner.nextInt()
        scanner.nextLine()
        if (cidade.equals("EXIT")) {
            return
        }

        print("CEP: ")
        String cep = scanner.nextLine()
        if (cep.equals("EXIT")) {
            return
        }

        print("Formação: ")
        String formacao = scanner.nextLine()
        if (formacao.equals("EXIT")) {
            return
        }

        print("Data de Nascimento (AAAA-MM-DD): ")
        String dataNascimento = scanner.nextLine()
        if (dataNascimento.equals("EXIT")) {
            return
        }

        print("LinkedIn: ")
        String linkedin = scanner.nextLine()
        if (linkedin.equals("EXIT")) {
            return
        }

        int ano = Integer.parseInt(dataNascimento.split("-")[0])
        int mes = Integer.parseInt(dataNascimento.split("-")[1])
        int dia = Integer.parseInt(dataNascimento.split("-")[2])

        try {

            Candidato candidato = new Candidato(
                    id: id,
                    nome: nome,
                    sobrenome: sobrenome,
                    inscricao: cpf,
                    descricao: descricao,
                    cidade: cidade,
                    CEP: cep,
                    formacao:formacao,
                    dataNascimento: LocalDate.of(ano, mes, dia),
                    linkedin: linkedin,
                    pais: null,
                    competencias: null)

            CandidatoDAO.create(candidato);

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
        String id = scanner.nextLine();
        if (id.equals("EXIT")) {
            return
        }

        CandidatoDAO.delete(new Candidato(id: Integer.parseInt(id)))

    }

    static void atualizar() {
        println("Alterar Dados do Candidato: (Escreva EXIT para voltar)")
        carregar()
        println("Qual o código (id) do candidato?")
        print("Id: ")
        int id = scanner.nextInt()
        scanner.nextLine()
        if (id.equals("EXIT")) {
            return
        }
        Candidato candidato = CandidatoDAO.readOne(id)[0]

        print("Novo Nome: (${candidato.getNome()}) ")
        String nome = scanner.nextLine()
        if (nome.equals("EXIT")) {
            return
        }
        if (nome.isEmpty()) {
            nome = candidato.getNome()
        }

        print("Novo Sobrenome: (${candidato.getSobrenome()}) ")
        String sobrenome = scanner.nextLine()
        if (sobrenome.equals("EXIT")) {
            return
        }
        if (sobrenome.isEmpty()) {
            sobrenome = candidato.getSobrenome()
        }

        print("Novo CPF: (${candidato.getInscricao()}) ")
        String cpf = scanner.nextLine()
        if (cpf.equals("EXIT")) {
            return
        }
        if (cpf.isEmpty()) {
            cpf = candidato.getInscricao()
        }

        print("Nova Descrição: (${candidato.getDescricao()}) ")
        String descricao = scanner.nextLine()
        if (descricao.equals("EXIT")) {
            return
        }
        if (descricao.isEmpty()) {
            descricao = candidato.getDescricao()
        }

        print("Nova Cidade: (${candidato.getCidade()}) ")
        String cidade = scanner.nextLine()
        if (cidade.equals("EXIT")) {
            return
        }
        if (cidade.isEmpty()) {
            cidade = candidato.getCidade()
        }
        print("Novo CEP: (${candidato.getCEP()}) ")
        String cep = scanner.nextLine()
        if (cep.equals("EXIT")) {
            return
        }
        if (cep.isEmpty()) {
            cep = candidato.getCEP()
        }

        print("Nova Formação: (${candidato.getFormacao()}) ")
        String formacao = scanner.nextLine()
        if (formacao.equals("EXIT")) {
            return
        }
        if (formacao.isEmpty()) {
            formacao = candidato.getFormacao()
        }

        print("Nova Data de Nascimento (AAAA-MM-DD): (${candidato.getDataNascimento()}) ")
        String dataNascimento = scanner.nextLine()
        if (dataNascimento.equals("EXIT")) {
            return
        }

        def dataNascimentoConvertida;

        if (dataNascimento.isEmpty()) {
            dataNascimentoConvertida = candidato.getDataNascimento()
        } else {
            int ano = Integer.parseInt(dataNascimento.split("-")[0])
            int mes = Integer.parseInt(dataNascimento.split("-")[1])
            int dia = Integer.parseInt(dataNascimento.split("-")[2])

            dataNascimentoConvertida = LocalDate.of(ano, mes, dia)
        }

        print("Novo LinkedIn: (${candidato.getLinkedin()}) ")
        String linkedin = scanner.nextLine()
        if (linkedin.equals("EXIT")) {
            return
        }
        if (linkedin.isEmpty()) {
            linkedin = candidato.getLinkedin()
        }

        try {
            CandidatoDAO.update(new Candidato(
                    id: id,
                    nome: nome,
                    sobrenome: sobrenome,
                    inscricao: cpf,
                    descricao: descricao,
                    cidade: Integer.parseInt(cidade),
                    CEP: cep,
                    formacao:formacao,
                    dataNascimento: dataNascimentoConvertida,
                    linkedin: linkedin,
                    pais: null,
                    competencias: null
            ))

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
        int opcaoMenu = scanner.nextInt()
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

            competencias.each { competencia ->
                CandidatoCompetenciaDAO.create(candidatos_id, competencia)
            }

        } else if (opcaoMenu == 2) {
            MenuCompetencias.exibir()
        }
    }

}
