package shell;

import shell.command.Command;
import shell.cycler.REPL;
import shell.cycler.REPLOperator;
import shell.tool.ShellState;

public class Shell {
    private static final REPL REPL = new REPLOperator();

    private Shell() {
        /* This utility class should not be instantiated */
    }

    public static void run(String[] args) {
        while(ShellState.isLooping()) {
            REPL.displayPrompt();
            Command command = REPL.parseCommand();
            REPL.interpretCommand(command);
        }
    }
}
