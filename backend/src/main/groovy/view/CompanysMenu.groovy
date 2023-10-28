package view


import entities.CompanyDTO
import exceptions.QuitException
import persistencies.CompanyDAO
import persistencies.ConnectionFactory
import utils.DateTimeHelper
import utils.InputHelper

import java.time.LocalDate

class CompanysMenu {

    static void showOptions() {

        HashMap<Integer, String> menu = [
                1: "Cadastrar NOVA empresa",
                2: "Visualizar Empresas",
                3: "Visualizar Empresa pelo ID",
                4: "Excluir Empresa",
                5: "Atualizar Empresa",
                6: "Vincular Vagas à Empresas",
                7: "Voltar ao menu anterior"
        ]

        while (true) {

            println("****** MENU DE OPÇÕES ******")

            menu.eachWithIndex { key, value, index ->
                println("[$key] - $value")
            }

            String userInput = InputHelper.getInputStringWithDefault("Opção (ou 'q' para sair)")

            try {

                Integer choice = userInput.toInteger()

                switch (choice) {
                    case 1:
                        createCompany(new CompanyDAO(sql: ConnectionFactory.newInstance()))
                        break
                    case 2:
                        loadCompany(new CompanyDAO(sql: ConnectionFactory.newInstance()))
                        break
                    case 3:
                        loadCompanyById(new CompanyDAO(sql: ConnectionFactory.newInstance()))
                        break
                    case 4:
                        deleteCompanyById(new CompanyDAO(sql: ConnectionFactory.newInstance()))
                        break
                    case 5:
                        updateCompanyById(new CompanyDAO(sql: ConnectionFactory.newInstance()))
                        break
                    case 6:
                        JobsMenu.showOptions()
                        break
                    case 7:
                        return
                    default:
                        break
                }

            } catch (NumberFormatException e) {
                println("\nDigite apenas o número das opções informadas no menu.\n")
            }
        }
    }

    static void createCompany(CompanyDAO companyDAO) {

        println("****** CADASTRAR NOVA EMPRESA ******");
        try {
            String id = InputHelper.getInputStringWithDefault("id")
            String name = InputHelper.getInputStringWithDefault("nome");
            String description = InputHelper.getInputStringWithDefault("descrição");
            String city = InputHelper.getInputStringWithDefault("cidade (número)");
            LocalDate creationDate = DateTimeHelper.getInputDateWithDefault("data de fundação (dd/mm/aaaa)");
            String cnpj = InputHelper.getInputStringWithDefault("cnpj");

            CompanyDTO company = new CompanyDTO(
                    id: id.toInteger(), name: name, description: description, city: city, creationDate: creationDate, cnpj: cnpj)

            companyDAO.save(company) ? println("Empresa registrada com sucesso") : println("Falha ao registrar empresa")

        } catch (QuitException e) {
            e.getMessage()
        }
    }

    static void loadCompany(CompanyDAO companyDAO) {

        println("Empresas Cadastradas:")
        InputHelper.printDivider(80)

        def columns = ["id", "nome", "linkedin"]
        InputHelper.printColumns(columns)

        companyDAO.findAll().forEach { it ->
            InputHelper.printColumns([it.getId().toString(), it.getName(), it.getDescription()])
        }
        InputHelper.printDivider(80)
    }

    static void loadCompanyById(CompanyDAO companyDAO) {

        println("Empresa:")
        String id = InputHelper.getInputStringWithDefault("id")

        InputHelper.printDivider(80)

        def columns = ["id", "nome", "linkedin"]
        InputHelper.printColumns(columns)

        CompanyDTO company = companyDAO.findById(id.toInteger())
        InputHelper.printColumns([company.getId().toString(), company.getName(), company.getDescription()])
        InputHelper.printDivider(80)
    }

    static void deleteCompanyById(CompanyDAO companyDAO) {
        println("Excluir Empresa")

        try {
            String id = InputHelper.getInputStringWithDefault("id")
            companyDAO.deleteById(id.toInteger()) ? println("Excluído com sucesso. Código ${id}") : println("Falha ao Excluir código ${id}")
        } catch (Exception e) {
            e.getMessage()
        }
    }

    static void updateCompanyById(CompanyDAO companyDAO) {
        println("Atualizar Empresa")

        try {
            String id = InputHelper.getInputStringWithDefault("id")
            CompanyDTO company = companyDAO.findById(id.toInteger())

            company.setName(InputHelper.getInputStringWithDefault("nome", company.getName()))
            company.setDescription(InputHelper.getInputStringWithDefault("descrição:", company.getDescription()))
            company.setCity(InputHelper.getInputStringWithDefault("cidade:", company.getCity()))

            companyDAO.updateById(company) ? println("Atualizado com sucesso. Código ${id}") : println("Falha ao atualizar o código ${id}")

        } catch (QuitException e) {
            e.getMessage()
        }
    }
}
