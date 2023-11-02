package view

import model.Company
import repository.DatabaseSingleton
import repository.JobsDAO
import services.CompanyService
import services.JobsService
import utils.DateTimeHelper
import utils.InputHelper
import utils.OperationStatus

import java.time.LocalDate

class CompanysMenu {

    private final CompanyService companyService
    private static DatabaseSingleton database = DatabaseSingleton.getInstance()

    CompanysMenu(CompanyService companyService) {
        this.companyService = companyService
    }

    void showOptions() {

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

                JobsDAO jobsDAO = new JobsDAO(database.getDatabaseConnection())
                JobsService jobsService = new JobsService(jobsDAO)
                JobsMenu jobsMenu = new JobsMenu(jobsService)

                switch (choice) {
                    case 1:
                        createCompany()
                        break
                    case 2:
                        loadCompany()
                        break
                    case 3:
                        loadCompanyById()
                        break
                    case 4:
                        deleteCompanyById()
                        break
                    case 5:
                        updateCompanyById()
                        break
                    case 6:
                        jobsMenu.showOptions()
                        break
                    case 7:
                        return
                    default:
                        break
                }

            } catch (NumberFormatException ignored) {
                println(OperationStatus.NOT_NUMBER.getMessage())
            }
        }
    }

    void createCompany() {

        println("****** CADASTRAR NOVA EMPRESA ******");

        String id = InputHelper.getInputStringWithDefault("id")
        String name = InputHelper.getInputStringWithDefault("nome");
        String description = InputHelper.getInputStringWithDefault("descrição");
        String city = InputHelper.getInputStringWithDefault("cidade (número)");
        LocalDate creationDate = DateTimeHelper.getInputDateWithDefault("data de fundação (dd/mm/aaaa)");
        String cnpj = InputHelper.getInputStringWithDefault("cnpj");

        Company company = new Company(
                id: id.toInteger(), name: name, description: description, city: city, creationDate: creationDate, cnpj: cnpj)

        OperationStatus status = companyService.save(company)
        println(status.getMessage())

    }

    void loadCompany() {

        println("Empresas Cadastradas:")
        InputHelper.printDivider(80)

        ArrayList<String> columns = ["id", "nome", "Descrição"]
        InputHelper.printColumns(columns)

        companyService.findAll().forEach { company ->
            InputHelper.printColumns([company.getId().toString(), company.getName(), company.getDescription()])
        }
        InputHelper.printDivider(80)
    }

    void loadCompanyById() {

        println("Empresa:")

        Company companySelected = new Company(id: InputHelper.getInputStringWithDefault("id").toInteger())

        InputHelper.printDivider(80)

        ArrayList<String> columns = ["id", "nome", "linkedin"]
        InputHelper.printColumns(columns)

        Company company = companyService.findById(companySelected.getId())
        InputHelper.printColumns([company.getId().toString(), company.getName(), company.getDescription()])
        InputHelper.printDivider(80)
    }

    void deleteCompanyById() {
        println("Excluir Empresa")

        Company company = new Company(id: InputHelper.getInputStringWithDefault("id").toInteger())
        OperationStatus status = companyService.deleteById(company.getId())
        println(status.getMessage())
    }

    void updateCompanyById() {
        println("Atualizar Empresa")

        Company companySelected = new Company(id:InputHelper.getInputStringWithDefault("id").toInteger())

        Company company = companyService.findById(companySelected.getId())

        company.setName(InputHelper.getInputStringWithDefault("nome", company.getName()))
        company.setDescription(InputHelper.getInputStringWithDefault("descrição:", company.getDescription()))
        company.setCity(InputHelper.getInputStringWithDefault("cidade:", company.getCity()))

        OperationStatus status = companyService.updateById(company)
        println(status.getMessage())
    }
}
