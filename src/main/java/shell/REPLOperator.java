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
    public Operation interpretCommand(String command) {
        return switch (command) {
            case "exit" -> ShellState::endProcess;
            default -> () -> System.out.println(command + ": command not found");
        };
    }

    @Override
    public void executeOperation(Operation operation) {
        operation.execute();
    }
}
