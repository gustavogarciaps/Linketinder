package utils

import exceptions.QuitException
import org.apache.tools.ant.taskdefs.Local

import java.time.LocalDate
import java.time.format.DateTimeFormatter

class DateTimeHelper {

    static final Scanner scanner = new Scanner(System.in);

    static LocalDate getInputDate(String prompt) {

        System.out.print(prompt + ": ");
        String input = scanner.nextLine();

        if (input.equals("q") || input.isEmpty()) {
            throw new QuitException("Usuário escolheu sair.")
        }

        return convertStringToTime(input)

    }

    static LocalDate getInputDate(String prompt, String args) {
        System.out.print(prompt + ": ");
        String input = scanner.nextLine();

        if (input.equals("q") || input.isEmpty()) {
            throw new QuitException("Usuário escolheu sair.")
        }

        if(input.isEmpty()){
            return args
        }

        return convertStringToTime(input);
    }

    static LocalDate convertStringToTime(String date) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        LocalDate dateFormatter = LocalDate.parse(date, formatter)

        return dateFormatter ? dateFormatter : null;
    }

    static void checkDateFormat() {

    }

}
