package view

class MainMenu {

    static Scanner scanner = new Scanner(System.in)

    static void showOptions() {

        HashMap<Integer, String> menu = [
                1: "CRIAR USUÁRIO",
                2: "ACESSAR PLATAFORMA",
                3: "VISUALIZAR USUÁRIOS CADASTRADOS",
                4: "ENCERRAR SESSÃO"
        ]

        while (true) {

            println("****** MENU DE OPÇÕES ******")

            menu.eachWithIndex { key, value, index ->
                println("[$key] - $value")
            }

            print("Opção (ou 'q' para sair): ")
            String userInput = scanner.nextLine()

            if (userInput.equals('q')) {
                break
            }

            try {

                Integer choice = userInput.toInteger()

                switch (choice) {
                    case 1:
                        println("")
                        break
                    case 2:
                        println(2)
                        break
                    case 3:
                        println(3)
                        break
                    case 4:
                        println("\nENCERRANDO SESSÃO ...3 ...2 ...1\n")
                        return
                    default:
                        break
                }

            } catch (NumberFormatException e) {
                println("\nDIGITE APENAS NÚMEROS QUE CONDIZEM COM AS OPÇÕES OFERECIDAS.\n")
            }
        }

    }
}
