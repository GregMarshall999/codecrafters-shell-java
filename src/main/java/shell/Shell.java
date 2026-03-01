package shell;

public class Shell {
    private static final REPL REPL = new REPLOperator();

    private Shell() {
        /* This utility class should not be instantiated */
    }

    public static void run(String[] args) {
        while(true) {
            REPL.displayPrompt();
            String command = REPL.parseCommand();
            REPL.errorWarn(command);
        }
    }
}
