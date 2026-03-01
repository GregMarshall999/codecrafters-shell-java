package shell.cycler;

import shell.command.Command;
import shell.command.BuiltIn;

import java.util.Arrays;
import java.util.Scanner;

public class REPLOperator implements REPL {
    private static final Scanner SCANNER = new Scanner(System.in);

    @Override
    public void displayPrompt() {
        System.out.print("$ ");
    }

    @Override
    public Command parseCommand() {
        String prompt = SCANNER.nextLine();

        String[] parsed = prompt.split(" ");
        String[] arguments = new String[0];
        if(parsed.length > 1) {
            arguments = Arrays.stream(parsed).skip(1).toArray(String[]::new);
        }

        return new Command(parsed[0], arguments);
    }

    @Override
    public void interpretCommand(Command command) {
        try {
            BuiltIn builtIn = BuiltIn.valueOf(command.operator().toUpperCase());
            builtIn.execute(command.arguments());
        } catch (IllegalArgumentException _) {
            System.out.println(command.operator() + ": command not found");
        }
    }
}
