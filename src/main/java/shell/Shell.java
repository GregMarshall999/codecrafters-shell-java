package shell;

import java.util.Scanner;

public class Shell {
    private Shell() {
        /* This utility class should not be instantiated */
    }

    private static final Scanner SCANNER = new Scanner(System.in);

    public static void run(String[] args) {
        while(true) {
            System.out.print("$ ");
            String command = SCANNER.nextLine();
            System.out.println(command + ": command not found");
        }
    }
}
