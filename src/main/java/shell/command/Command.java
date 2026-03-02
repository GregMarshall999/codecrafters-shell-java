package shell.command;

public record Command(String operator, String... arguments) {
    public String[] toStringArray() {
        String[] array = new String[arguments.length + 1];
        array[0] = operator;

        System.arraycopy(arguments, 0, array, 1, arguments.length + 1 - 1);

        return array;
    }
}