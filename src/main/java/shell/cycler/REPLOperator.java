package shell.cycler;

import shell.command.Command;
import shell.command.BuiltIn;
import shell.tool.ShellEnv;

import java.io.File;
import java.io.IOException;
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
            File file;
            Process process = null;
            for (String directory : ShellEnv.getPathDirectories()) {
                file = new File(directory, command.operator() + ShellEnv.getSystemOS().getExtension());
                if(file.exists() && file.canExecute()) {
                    try {
                        process = new ProcessBuilder(command.toStringArray()).inheritIO().start();
                        process.waitFor();
                        break;
                    } catch (IOException | InterruptedException e) {
                        System.out.println("Error during execution");
                        process = null;
                    }
                }
            }

            if(process == null) System.out.println(command.operator() + ": command not found");
        }
    }
}
