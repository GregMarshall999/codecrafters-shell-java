package shell;

public class ShellEnv {
    private ShellEnv() {
        /* This utility class should not be instantiated */
    }

    private static final String PATH = System.getenv("PATH");

    public static String[] getPathDirectories() {
        return PATH.split(";");
    }
}
