package shell.command;

import shell.tool.ShellEnv;
import shell.tool.Operation;
import shell.tool.ShellState;

import java.io.File;

public enum BuiltIn {
    CD(arguments -> {
        if(arguments.length >= 1) {
            boolean canChangeDirectory = true;
            String directory;
            if(arguments[0].startsWith("../")) {
                int iterations = arguments[0].split("/").length;

                String[] dirs = ShellState.getCurrentDirectory().split(ShellEnv.getDirectorySplitter());
                if(dirs.length <= iterations) {
                    System.out.println("Home has no parent directory");
                    canChangeDirectory = false;
                    directory = "";
                }
                else {
                    StringBuilder builder = new StringBuilder();
                    for (int i = 0; i < dirs.length - iterations; i++) {
                        builder.append(dirs[i]);
                        if(i < dirs.length - 2) builder.append(ShellEnv.getDirectorySplitter());
                    }
                    directory = builder.toString();
                }
            }
            else if(arguments[0].startsWith(ShellEnv.getAbsoluteStarter())) {
                directory = arguments[0];
            }
            else {
                if(arguments[0].startsWith("./")) arguments[0] = arguments[0].substring(2);
                directory = ShellState.getCurrentDirectory() + ShellEnv.getDirectorySplitter() + arguments[0];
            }

            if(canChangeDirectory) {
                File d = new File(directory);
                if(d.isDirectory()) ShellState.changeCurrentDirectory(d.getAbsolutePath());
                else System.out.println("cd: " + directory + ": No such file or directory");
            }
        }
    }),
    ECHO(arguments -> {
        for (String s : arguments) {
            System.out.print(s + " ");
        }
        System.out.println();
    }),
    EXIT(_ -> ShellState.endProcess()),
    PWD(_ -> System.out.println(ShellState.getCurrentDirectory())),
    TYPE(arguments -> {
        if(arguments.length >= 1) {
            try {
                BuiltIn.valueOf(arguments[0].toUpperCase());
                System.out.println(arguments[0] + " is a shell builtin");
            } catch (IllegalArgumentException _) {
                boolean commandFound = false;
                File file;
                for (String directory : ShellEnv.getPathDirectories()) {
                    file = new File(directory, arguments[0] + ShellEnv.getSystemOS().getExtension());
                    if(file.exists() && file.canExecute()) {
                        System.out.println(arguments[0] + " is " + file.getAbsolutePath());
                        commandFound = true;
                        break;
                    }
                }

                if(!commandFound) System.out.println(arguments[0] + ": not found");
            }
        }
    });

    private final Operation operation;

    BuiltIn(Operation operation) {
        this.operation = operation;
    }

    public void execute(String... argument) {
        operation.execute(argument);
    }
}
