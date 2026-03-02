package shell;

import shell.tool.OS;

public class ShellEnv {
    private ShellEnv() {
        /* This utility class should not be instantiated */
    }

    private static final String PATH = System.getenv("PATH");
    private static final String OPERATING_SYSTEM = System.getProperty("os.name");

    public static String[] getPathDirectories() {
        return switch (getSystemOS()) {
            case LINUX -> PATH.split(":");
            case MAC_OS_X -> PATH.split("-");
            case WINDOWS_10, WINDOWS_11 -> PATH.split(";");
        };
    }

    public static OS getSystemOS() {
        return OS.valueOf(OPERATING_SYSTEM.toUpperCase().replace(" ", "_"));
    }
}
