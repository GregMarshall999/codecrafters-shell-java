package shell;

import java.util.Scanner;

public class REPLOperator implements REPL {
    private static final Scanner SCANNER = new Scanner(System.in);

    @Override
    public void displayPrompt() {
        System.out.print("$ ");
    }

    @Override
    public String parseCommand() {
        return SCANNER.nextLine();

    }

    @Override
    public void errorWarn(String command) {
        System.out.println(command + ": command not found");
    }
}
