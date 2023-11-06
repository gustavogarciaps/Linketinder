package view

import repository.CandidateDAO
import repository.CompanyDAO
import repository.connection.DatabaseSingleton
import domain.Person
import services.CandidateService
import services.CompanyService
import services.PersonService
import utils.InputHelper
import utils.OperationStatus

class MainMenu {

    private static DatabaseSingleton database = DatabaseSingleton.getInstance()

    private final PersonService personService

    private final CandidateDAO candidateDAO = new CandidateDAO(database.getDatabaseConnection())
    private final CandidateService candidateService = new CandidateService(candidateDAO)
    private final CandidatesMenu candidatesMenu = new CandidatesMenu(candidateService)

    private final CompanyDAO companyDAO = new CompanyDAO(database.getDatabaseConnection())
    private final CompanyService companyService = new CompanyService(companyDAO)
    private final CompanysMenu companyMenu = new CompanysMenu(companyService)

    MainMenu(PersonService personService) {
        this.personService = personService
    }

    void showOptions() {

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

    void createPerson() {

        println("****** CADASTRAR NOVO USUÁRIO ******");

        String email = InputHelper.getInputStringWithDefault("email");
        String password = InputHelper.getInputStringWithDefault("senha");

        Person person = new Person(email: email, password: password)

        OperationStatus status = personService.save(person)
        println(status.getMessage())
    }

    void accessPlatform() {

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

                switch (choice) {
                    case 1:
                        companyMenu.showOptions()
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

    void loadPerson() {

        println("Usuários Cadastrados:")
        InputHelper.printDivider(80)

        ArrayList<String> columns = ["id", "email", "password"]
        InputHelper.printColumns(columns)

        personService.findAll().forEach { person ->
            InputHelper.printColumns([person.getId(), person.getEmail(), person.getPassword()] as ArrayList<String>)
        }

        InputHelper.printDivider(80)
    }
}
