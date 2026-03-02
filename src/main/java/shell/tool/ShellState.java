package shell.tool;

public class ShellState {
    private static boolean executionLoop = true;

    private static String userDirectory = System.getProperty("user.dir");

    private ShellState() {
        /* This utility class should not be instantiated */
    }

    public static boolean isLooping() {
        return executionLoop;
    }

    public static void endProcess() {
        executionLoop = false;
    }

    public static String getCurrentDirectory() {
        return userDirectory;
    }

    public static void changeCurrentDirectory(String directory) {
        userDirectory = directory;
    }
}
