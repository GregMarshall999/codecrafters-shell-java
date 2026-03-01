package shell.cycler;

import shell.command.Command;

public interface REPL {
    void displayPrompt();
    Command parseCommand();
    void interpretCommand(Command command);
}
