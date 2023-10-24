package utils

import exceptions.QuitException

class InputHelper {

    static final Scanner scanner = new Scanner(System.in);

    static String getInputString(String prompt) {
        System.out.print(prompt + ": ");
        String input = scanner.nextLine();

        if (input.equals("q")) {
            throw new QuitException("Usuário escolheu sair.")
        }

        return input;
    }


    static void divider(Integer number) {
        println("*" * number);
    }

    static void creatingTable(ArrayList<String> columns) {

        columns.each { it ->
            print("${it}\t\t\t")
        }
        println()
    }
}
