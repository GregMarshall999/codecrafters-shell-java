package shell.command;

import shell.tool.Operation;
import shell.ShellState;

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
                System.out.println(arguments[0] + ": not found");
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
