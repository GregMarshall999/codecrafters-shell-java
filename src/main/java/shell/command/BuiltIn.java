package shell.command;

import shell.tool.ShellEnv;
import shell.tool.Operation;
import shell.tool.ShellState;

import java.io.File;

public enum BuiltIn {
    ECHO(arguments -> {
        for (String s : arguments) {
            System.out.print(s + " ");
        }
        System.out.println();
    }),
    EXIT(_ -> ShellState.endProcess()),
    PWD(_ -> System.out.println(ShellState.getCurrentDirectory())),
    TYPE(arguments -> {
        if(arguments.length >= 1) {
            try {
                BuiltIn.valueOf(arguments[0].toUpperCase());
                System.out.println(arguments[0] + " is a shell builtin");
            } catch (IllegalArgumentException _) {
                boolean commandFound = false;
                File file;
                for (String directory : ShellEnv.getPathDirectories()) {
                    file = new File(directory, arguments[0] + ShellEnv.getSystemOS().getExtension());
                    if(file.exists() && file.canExecute()) {
                        System.out.println(arguments[0] + " is " + file.getAbsolutePath());
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
