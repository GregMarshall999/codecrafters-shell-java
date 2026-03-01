package shell;

public interface REPL {
    void displayPrompt();
    String parseCommand();
    void errorWarn(String command);
}
