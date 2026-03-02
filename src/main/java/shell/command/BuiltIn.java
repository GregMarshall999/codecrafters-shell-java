package shell.command;

import shell.ShellEnv;
import shell.tool.Operation;
import shell.ShellState;

import java.io.File;

public enum BuiltIn {
    ECHO(arguments -> {
        for (String s : arguments) {
            System.out.print(s + " ");
        }
        System.out.println();
    }),
    EXIT(_ -> ShellState.endProcess()),
    TYPE(arguments -> {
        if(arguments.length >= 1) {
            try {
                BuiltIn.valueOf(arguments[0].toUpperCase());
                System.out.println(arguments[0] + " is a shell builtin");
            } catch (IllegalArgumentException _) {
                boolean commandFound = false;
                File file;
                for (String directory : ShellEnv.getPathDirectories()) {
                    file = new File(directory, arguments[0] + ".exe"); //TODO: might clash with non windows OS
                    if(file.exists() && file.canExecute()) {
                        System.out.println(arguments[0] + " is " + directory + arguments[0]);
                        commandFound = true;
                        break;
                    }
                }

                if(!commandFound) System.out.println(arguments[0] + ": not found");
            }
        }
    });

    private final Operation operation;

    BuiltIn(Operation operation) {
        this.operation = operation;
    }

    public void execute(String... argument) {
        operation.execute(argument);
    }
}
