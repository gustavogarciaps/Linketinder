package view

import repository.CandidateDAO
import repository.CompanyDAO
import repository.DatabaseSingleton
import model.Person
import repository.DatabaseConfig
import repository.PersonDAO
import services.CandidateService
import services.CompanyService
import utils.InputHelper
import utils.OperationStatus

class MainMenu {

    private static DatabaseSingleton database = DatabaseSingleton.getInstance()

    static void showOptions() {

        HashMap<Integer, String> menu = [
                1: "Criar usuário",
                2: "Acessar plataforma",
                3: "Visualizar usuários cadastrados",
                4: "Encerrar aplicação"
        ]

        while (true) {

            println("****** MENU DE OPÇÕES ******")

            menu.eachWithIndex { key, value, index ->
                println("[$key] - $value")
            }

            try {
                String userInput = InputHelper.getInputStringWithDefault("Opção")
                Integer choice = userInput.toInteger()

                switch (choice) {
                    case 1:
                        createPerson()
                        break
                    case 2:
                        accessPlatform()
                        break
                    case 3:
                        loadPerson()
                        break
                    case 4:
                        println("\nEncerrando sessão ...3 ...2 ...1")
                        return
                    default:
                        break
                }
            } catch (NumberFormatException ignored) {
                println(OperationStatus.NOT_NUMBER.getMessage())
            } catch (Exception e) {
                e.getMessage()
            }
        }
    }

    static void createPerson() {

        println("****** CADASTRAR NOVO USUÁRIO ******");
        try {
            String email = InputHelper.getInputStringWithDefault("email");
            String password = InputHelper.getInputStringWithDefault("senha");

            Person personDTO = new Person(email: email, password: password)
            PersonDAO personDAO = new PersonDAO(sql: DatabaseConfig.newInstance());

            personDAO.save(personDTO)

        } catch (Exception e) {
            e.getMessage()
        }
    }

    static void accessPlatform() {

        HashMap<Integer, String> menu = [
                1: "Acessar como Empresa",
                2: "Acessar como Candidato",
                3: "Voltar ao menu anterior"
        ]

        while (true) {

            println("****** MENU DE OPÇÕES ******")

            menu.eachWithIndex { key, value, index ->
                println("[$key] - $value")
            }

            try {
                String userInput = InputHelper.getInputStringWithDefault("Opção")
                Integer choice = userInput.toInteger()

                CandidateDAO candidateDAO = new CandidateDAO(database.getDatabaseConnection())
                CandidateService candidateService = new CandidateService(candidateDAO)
                CandidatesMenu candidatesMenu = new CandidatesMenu(candidateService)

                CompanyDAO companyDAO = new CompanyDAO(database.getDatabaseConnection())
                CompanyService companyService = new CompanyService(companyDAO)
                CompanysMenu companysMenu = new CompanysMenu(companyService)

                switch (choice) {
                    case 1:
                        companysMenu.showOptions()
                        break
                    case 2:
                        candidatesMenu.showOptions()
                        break
                    case 3:
                        return
                    default:
                        break
                }
            } catch (NumberFormatException ignored) {
                println(OperationStatus.NOT_NUMBER.getMessage())
            } catch (Exception e) {
                e.getMessage()
            }
        }
    }

    static void loadPerson() {

        PersonDAO personDAO = new PersonDAO(sql: DatabaseConfig.newInstance())

        println("Usuários Cadastrados:")
        InputHelper.printDivider(80)

        def columns = ["id", "email", "password"]
        InputHelper.printColumns(columns)

        personDAO.findAll().forEach { it ->
            InputHelper.printColumns([it.getId(), it.getEmail(), it.getPassword()])
        }

        InputHelper.printDivider(80)
    }
}
