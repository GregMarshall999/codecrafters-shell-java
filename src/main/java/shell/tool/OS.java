package shell.tool;

public enum OS {
    LINUX(""),
    MAC_OS_X(""),
    WINDOWS_10(".exe"),
    WINDOWS_11(".exe");

    private final String extension;

    OS(String extension) {
        this.extension = extension;
    }

    public String getExtension() {
        return extension;
    }
}
