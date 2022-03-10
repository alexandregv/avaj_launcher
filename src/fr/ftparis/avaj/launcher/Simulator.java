package fr.ftparis.avaj.launcher;

import fr.ftparis.avaj.launcher.exceptions.ScenarioFileNotFoundException;

import javax.swing.text.AbstractDocument;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.logging.*;

import static fr.ftparis.avaj.launcher.ScenarioFile.initFromScenarioFile;

public class Simulator {
    private static final Logger LOGGER = Logger.getLogger(Simulator.class.getPackage().getName());

    static {
        try {
            SimpleFormatter formatter = new SimpleFormatter() {
                private static final String format = "[%1$tT] [%2$-7s] %3$s %n";
                public synchronized String format(LogRecord logRecord) {
                    return String.format(format,
                            new Date(logRecord.getMillis()),
                            logRecord.getLevel().getLocalizedName(),
                            logRecord.getMessage()
                    );

                }

//                private static final String format = "%1$s %n";
//                public synchronized String format(LogRecord logRecord) {
//                    return String.format(format, logRecord.getMessage());
//                }
            };

            ConsoleHandler consoleHandler = new ConsoleHandler();
            consoleHandler.setFormatter(formatter);
            consoleHandler.setLevel(Level.OFF);

            FileHandler fileHandler = new FileHandler("simulation.txt");
            fileHandler.setFormatter(formatter);
            fileHandler.setLevel(Level.ALL);

            LOGGER.addHandler(consoleHandler);
            LOGGER.addHandler(fileHandler);
            LOGGER.setUseParentHandlers(false);

            LOGGER.setLevel(Level.FINE);
        } catch(Exception exception) {
            quit(1, "Error while initializing logger", exception);
        }
    }

    public static void main(String[] args) {
        if (args.length != 1)
            quit(1, "Please provide a scenario file as argument.", new ScenarioFileNotFoundException("No scenario file provided."));

        try {
            initFromScenarioFile(args[0]);
        } catch (IOException exception) {
            quit(1, "Error while reading scenario file (" + args[0] + ").", exception);
        }
    }

    public static void quit(int exitStatus, String message) {
        quit(exitStatus, message, null);
    }

    public static void quit(int exitStatus, Throwable throwable) {
        quit(exitStatus, throwable.getLocalizedMessage(), throwable);
    }

    public static void quit(int exitStatus, String message, Throwable throwable) {
        LOGGER.log(Level.SEVERE, message, throwable);
        System.out.println(message);
        System.exit(exitStatus);
    }
}
