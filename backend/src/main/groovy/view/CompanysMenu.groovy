package view

import entities.CandidateDTO
import entities.CompanyDTO
import exceptions.QuitException
import persistencies.CandidateDAO
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

            String userInput = InputHelper.getInputString("Opção (ou 'q' para sair)")

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
            String id = InputHelper.getInputString("id")
            String name = InputHelper.getInputString("nome");
            String description = InputHelper.getInputString("descrição");
            String city = InputHelper.getInputString("cidade (número)");
            LocalDate creationDate = DateTimeHelper.getInputDate("data de fundação (dd/mm/aaaa)");
            String cnpj = InputHelper.getInputString("cnpj");

            CompanyDTO company = new CompanyDTO(
                    id: id.toInteger(), name: name, description: description, city: city, creationDate: creationDate, cnpj: cnpj)

            companyDAO.save(company) ? println("Empresa registrada com sucesso") : println("Falha ao registrar empresa")

        } catch (QuitException e) {
            e.getMessage()
        }
    }

    static void loadCompany(CompanyDAO companyDAO) {

        println("Empresas Cadastradas:")
        InputHelper.divider(80)

        def columns = ["id", "nome", "linkedin"]
        InputHelper.creatingTable(columns)

        companyDAO.findAll().forEach { it ->
            InputHelper.creatingTable([it.getId().toString(), it.getName(), it.getDescription()])
        }

        InputHelper.divider(80)
    }

    static void loadCompanyById(CompanyDAO companyDAO) {

        println("Empresa:")
        String id = InputHelper.getInputString("id")

        InputHelper.divider(80)

        def columns = ["id", "nome", "linkedin"]
        InputHelper.creatingTable(columns)

        CompanyDTO company = companyDAO.findById(id.toInteger())
        InputHelper.creatingTable([company.getId().toString(), company.getName(), company.getDescription()])
        InputHelper.divider(80)
    }

    static void deleteCompanyById(CompanyDAO companyDAO) {
        println("Excluir Empresa")

        try {
            String id = InputHelper.getInputString("id")
            companyDAO.deleteById(id.toInteger()) ? println("Excluído com sucesso. Código ${id}") : println("Falha ao Excluir código ${id}")

        } catch (Exception e) {
            e.getMessage()
        }

    }

    static void updateCompanyById(CompanyDAO companyDAO) {
        println("Atualizar Empresa")

        try {
            String id = InputHelper.getInputString("id")
            CompanyDTO company = companyDAO.findById(id.toInteger())

            company.setName(InputHelper.getInputString("nome", company.getName()))
            company.setDescription(InputHelper.getInputString("descrição:", company.getDescription()))
            company.setCity(InputHelper.getInputString("cidade:", company.getCity()))

            companyDAO.updateById(company) ? println("Atualizado com sucesso. Código ${id}") : println("Falha ao atualizar o código ${id}")

        } catch (QuitException e) {
            e.getMessage()
        }

    }
}
