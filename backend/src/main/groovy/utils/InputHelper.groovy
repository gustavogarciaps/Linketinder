package utils

import exceptions.QuitException

class InputHelper {

    static final Scanner scanner = new Scanner(System.in);

    static String getInputStringWithDefault(String prompt) {
        printPrompt(prompt);
        String input = scanner.nextLine();
        checkForQuit(input);
        return input;
    }

    static String getInputStringWithDefault(String prompt, String defaultValue) {
        printPrompt(prompt + " (" + defaultValue + "): ");
        String input = scanner.nextLine();
        checkForQuit(input);
        return input.isEmpty() ? defaultValue : input;
    }

    static void printDivider(int number) {
        println("*".repeat(number));
    }

    static void printColumns(ArrayList<String> columns) {
        for (String column : columns) {
            print(column + "\t\t\t");
        }
        println();
    }

    private static void printPrompt(String prompt) {
        System.out.print(prompt + ": ");
    }

    private static void checkForQuit(String input) {
        if (input.equals("q")) {
            throw new QuitException("Usuário escolheu sair.");
        }
    }
}
