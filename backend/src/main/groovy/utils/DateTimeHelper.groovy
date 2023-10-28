package utils

import exceptions.QuitException
import org.apache.tools.ant.taskdefs.Local

import java.time.LocalDate
import java.time.format.DateTimeFormatter

class DateTimeHelper {

    static final Scanner scanner = new Scanner(System.in);

    static LocalDate getInputDateWithDefault(String prompt) {
        String input = getInput(prompt);
        return convertStringToDate(input);
    }

    static LocalDate getInputDateWithDefault(String prompt, String defaultValue) {
        String input = getInput(prompt + " (" + defaultValue + "): ");
        if (input.isEmpty()) {
            input = defaultValue;
        }
        return convertStringToDate(input);
    }

    private static String getInput(String prompt) {
        print(prompt);
        String input = scanner.nextLine();
        checkForQuit(input);
        return input;
    }

    private static void checkForQuit(String input) {
        if (input.equals("q")) {
            throw new QuitException("Usuário escolheu sair.");
        }
    }

    private static LocalDate convertStringToDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            return LocalDate.parse(date, formatter);
        } catch (Exception ignored) {
            System.err.println("Formato de data inválido. Use o formato dd/MM/yyyy.");
            return null;
        }
    }
}
