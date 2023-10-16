package view

import database.CandidatoCompetenciaDAO
import database.EmpresaDAO
import model.Empresa


import java.time.LocalDate

class MenuEmpresas {

    static private ArrayList<Empresa> empresas;
    static private Scanner scanner = new Scanner(System.in)

    static void exibir() {

        def opcaoMenu = 0

        while (opcaoMenu != 6) {
            println "Menu de Opções:";
            println "1. Visualizar Empresas";
            println "2. Cadastrar Nova Empresa";
            println "3. Excluir Empresa";
            println "4. Atualizar Empresa";
            println "5. Vincular Vagas à Empresa";
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

        empresas = EmpresaDAO.read()

        println("Empresas Cadastrados:")
        println("-" * 80);
        println("-" * 80);
        println("|id" + ("\t" * 2) + "|" + "nome" + ("\t" * 4))

        empresas.each { e ->
            println("Empresa:")
            println("|" + e.getId() + ("\t" * 2) + "|" + e.getRazaoSocial() + ("\t" * 4) + "|" + e.getDescricao() + ("\t" * 4))
            println("Vagas:")
            /*
                    e.getVagas().getVagas().each { vaga ->
                        println("|" + vaga.getId() + ("\t" * 2) + "|" + vaga.getTitulo() + ("\t" * 4))
                    }
                    */

        }

        println("-" * 80);
        println("-" * 80);
    }

    static void criar() {
        println("Cadastro de Empresa:  (Escreva EXIT para voltar)")

        print("Id: ")
        int id = scanner.nextInt()
        scanner.nextLine()

        print("Razao Social: ")
        String razaoSocial = scanner.nextLine()
        if (razaoSocial.equals("EXIT")) {
            return
        }

        print("CNPJ: ")
        String cnpj = scanner.nextLine()
        if (cnpj.equals("EXIT")) {
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

        print("Data de Fundação (AAAA-MM-DD): ")
        String dataFundacao = scanner.nextLine()
        if (dataFundacao.equals("EXIT")) {
            return
        }

        int ano = Integer.parseInt(dataFundacao.split("-")[0])
        int mes = Integer.parseInt(dataFundacao.split("-")[1])
        int dia = Integer.parseInt(dataFundacao.split("-")[2])

        LocalDate dataFundacaoConvertida = LocalDate.of(ano,mes,dia)

        try {
            EmpresaDAO.create(new Empresa(
                    id: Integer.parseInt(id),
                    razaoSocial: razaoSocial,
                    inscricao: cnpj,
                    CEP: cep,
                    cidade: Integer.parseInt(cidade),
                    pais: null,
                    descricao: descricao,
                    dataFundacao: dataFundacaoConvertida,
                    vagas: null));

            println("Empresa cadastrada com sucesso!")
        } catch (Exception e) {
            println("Erro ao cadastrar o empresa: " + e.getMessage())
        }
    }

    static void deletar() {
        println("Deletar Empresa: (Escreva EXIT para voltar)")
        carregar();
        println("Qual o código (id) da Empresa?");
        print("Id: ")
        String id = scanner.nextLine();
        if (id.equals("EXIT")) {
            return
        }
        EmpresaDAO.delete(new Empresa(id: Integer.parseInt(id)))
    }

    static void atualizar() {
        println("Alterar Dados da Empresa: (Escreva EXIT para voltar)")
        carregar()
        println("Qual o código (id) da empresa?")
        print("Id: ")
        String id = scanner.nextInt()
        if (id.equals("EXIT")) {
            return
        }
        Empresa empresa = EmpresaDAO.readOne(Integer.parseInt(id))[0]

        print("Novo Nome: (${empresa.getRazaoSocial()}) ")
        String razaoSocial = scanner.nextLine()
        if (razaoSocial.equals("EXIT")) {
            return
        }
        if (razaoSocial.isEmpty()) {
            razaoSocial = empresa.getRazaoSocial()
        }

        print("Novo CNPJ: (${empresa.getInscricao()}) ")
        String cnpj = scanner.nextLine()
        if (cnpj.equals("EXIT")) {
            return
        }
        if (cnpj.isEmpty()) {
            cnpj = empresa.getInscricao()
        }

        print("Nova Descrição: (${empresa.getDescricao()}) ")
        String descricao = scanner.nextLine()
        if (descricao.equals("EXIT")) {
            return
        }
        if (descricao.isEmpty()) {
            descricao = empresa.getDescricao()
        }

        print("Nova Cidade: (${empresa.getCidade()}) ")
        String cidade = scanner.nextLine()
        if (cidade.equals("EXIT")) {
            return
        }
        if (cidade.isEmpty()) {
            cidade = empresa.getCidade()
        }
        print("Novo CEP: (${empresa.getCEP()}) ")
        String cep = scanner.nextLine()
        if (cep.equals("EXIT")) {
            return
        }
        if (cep.isEmpty()) {
            cep = empresa.getCEP()
        }

        print("Nova Data de Fundação (AAAA-MM-DD): (${empresa.getDataFundacao()}) ")
        String dataFundacao = scanner.nextLine()
        if (dataFundacao.equals("EXIT")) {
            return
        }

        LocalDate dataFundacaoConvertida;

        if (dataFundacao.isEmpty()) {
            dataFundacaoConvertida = empresa.getDataFundacao()
        } else {
            int ano = Integer.parseInt(dataFundacao.split("-")[0])
            int mes = Integer.parseInt(dataFundacao.split("-")[1])
            int dia = Integer.parseInt(dataFundacao.split("-")[2])

            dataFundacaoConvertida = LocalDate.of(ano, mes, dia)
        }

        try {

            EmpresaDAO.update(new Empresa(
                    id: Integer.parseInt(id),
                    razaoSocial: razaoSocial,
                    inscricao: cnpj,
                    CEP: cep,
                    cidade: Integer.parseInt(cidade),
                    pais: null,
                    descricao: descricao,
                    dataFundacao: dataFundacaoConvertida,
                    vagas: null));

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

            competencias.forEach {it ->
                CandidatoCompetenciaDAO.create(candidatos_id, it)
            }

        } else if (opcaoMenu == 2) {
            MenuCompetencias.exibir()
        }
    }

}


