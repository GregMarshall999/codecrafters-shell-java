package shell.command;

import shell.tool.Operation;
import shell.ShellState;

public enum BuiltIn {
    ECHO(argument -> {
        for (String s : argument) {
            System.out.print(s + " ");
        }
        System.out.println();
    }),
    EXIT(_ -> ShellState.endProcess());

    private final Operation operation;

    BuiltIn(Operation operation) {
        this.operation = operation;
    }

    public void execute(String... argument) {
        operation.execute(argument);
    }
}
