package fr.ftparis.avaj.launcher;

import java.io.*;

import static fr.ftparis.avaj.launcher.ScenarioFile.initFromScenarioFile;

public class Simulator {

    public static void main(String[] args) {
        if (args.length != 1)
            quit(1, "Please provide a scenario file as argument.");

        try {
            initFromScenarioFile(args[0]);
        } catch (IOException e) {
            quit(1, "Error while reading scenario file (" + args[0] + ").");
        }
    }

    public static void quit(int exitStatus, String message) {
        System.out.println(message);
        System.exit(exitStatus);
    }
}
