package view

import entities.Person
import DAO.Connection
import DAO.PersonDAO
import utils.InputHelper

class MainMenu {

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
            } catch (NumberFormatException e) {
                println("\nDigite apenas o número das opções informadas no menu.\n")
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
            PersonDAO personDAO = new PersonDAO(sql: Connection.newInstance());

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

                switch (choice) {
                    case 1:
                        CompanysMenu.showOptions()
                        break
                    case 2:
                        CandidatesMenu.showOptions()
                        break
                    case 3:
                        return
                    default:
                        break
                }
            } catch (NumberFormatException e) {
                println("\nDigite apenas o número das opções informadas no menu.\n")
            } catch (Exception e) {
                e.getMessage()
            }
        }
    }

    static void loadPerson() {

        PersonDAO personDAO = new PersonDAO(sql: Connection.newInstance())

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
