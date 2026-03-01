package shell;

public interface REPL {
    void displayPrompt();
    String parseCommand();
    Operation interpretCommand(String command);
    void executeOperation(Operation operation);
}
