package shell.tool;

public class ShellEnv {
    private ShellEnv() {
        /* This utility class should not be instantiated */
    }

    private static final String PATH = System.getenv("PATH");
    private static final String OPERATING_SYSTEM = System.getProperty("os.name");
    private static final String OS_HOME = System.getenv("HOME");

    public static String[] getPathDirectories() {
        return switch (getSystemOS()) {
            case LINUX -> PATH.split(":");
            case MAC_OS_X -> PATH.split("-");
            case WINDOWS_10, WINDOWS_11 -> PATH.split(";");
        };
    }

    public static String getAbsoluteStarter() {
        return switch (getSystemOS()) {
            case LINUX, MAC_OS_X -> "/";
            case WINDOWS_10, WINDOWS_11 -> "C:\\";
        };
    }

    public static String getDirectorySplitter() {
        return switch (getSystemOS()) {
            case LINUX, MAC_OS_X -> "/";
            case WINDOWS_10, WINDOWS_11 -> "\\\\";
        };
    }

    public static OS getSystemOS() {
        return OS.valueOf(OPERATING_SYSTEM.toUpperCase().replace(" ", "_"));
    }

    public static String getSystemHomeDir() {
        if(OS_HOME == null) {
            return switch (getSystemOS()) {
                case LINUX, MAC_OS_X -> "/home/user";
                case WINDOWS_10, WINDOWS_11 -> "C:\\";
            };
        }
        return OS_HOME;
    }
}
