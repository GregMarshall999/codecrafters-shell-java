package shell.tool;

public class ShellState {
    private static boolean executionLoop = true;

    private ShellState() {
        /* This utility class should not be instantiated */
    }

    public static boolean isLooping() {
        return executionLoop;
    }

    public static void endProcess() {
        executionLoop = false;
    }
}
