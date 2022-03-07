package fr.ftparis.avaj.launcher;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;
import java.util.stream.IntStream;

import static fr.ftparis.avaj.launcher.ScenarioFile.initFromScenarioFile;
import static fr.ftparis.avaj.launcher.Utils.quit;

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
}
