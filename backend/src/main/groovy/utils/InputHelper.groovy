package utils

class InputHelper {

    static final Scanner scanner = new Scanner(System.in);

    static String getInput(String prompt) {
        System.out.print(prompt + ": ");
        String input = scanner.nextLine();

        return input;
    }

    static void divider(Integer number){
        println("*" * number);
    }

    static void creatingTable(ArrayList<String> columns) {

        def fields = [0,10,70]
        def spaces = (0..100).collect { " " }

        columns.each { it ->
            spaces[fields[0]] = it
            fields.remove(0)
        }

        spaces.each {it ->
            print(it)
        }
        println()
    }
}
