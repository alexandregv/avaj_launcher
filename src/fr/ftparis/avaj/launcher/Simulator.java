package fr.ftparis.avaj.launcher;

import java.io.*;
import java.util.Scanner;
import java.util.StringJoiner;

public class Simulator {

    private static void quit(int exitStatus, String message) {
        System.out.println(message);
        System.exit(exitStatus);
    }

    private static int readLine(StreamTokenizer tokenizer) throws IOException {
       String type, name;
       int longitude, latitude, height;

        if (tokenizer.nextToken() != StreamTokenizer.TT_WORD)
            return (-1);
        type = tokenizer.sval;

        if (tokenizer.nextToken() != StreamTokenizer.TT_WORD)
            return (-1);
        name = tokenizer.sval;

        if (tokenizer.nextToken() != StreamTokenizer.TT_NUMBER)
            return (-1);
        longitude = (int) tokenizer.nval;

        if (tokenizer.nextToken() != StreamTokenizer.TT_NUMBER)
            return (-1);
        latitude = (int) tokenizer.nval;

        if (tokenizer.nextToken() != StreamTokenizer.TT_NUMBER)
            return (-1);
        height = (int) tokenizer.nval;

        if (tokenizer.nextToken() != StreamTokenizer.TT_EOL)
            return (-1);

        System.out.println("TYPE: " + type);
        System.out.println("NAME: " + name);
        System.out.println("LONG: " + longitude);
        System.out.println("LATI: " + latitude);
        System.out.println("HEIT: " + height + "\n");

        return (0);
    }

    private static int readFile(String filename) throws IOException {
        int simulationCount = 0;

        FileReader fileReader = new FileReader(filename);
        StreamTokenizer tokenizer = new StreamTokenizer(fileReader);

        tokenizer.eolIsSignificant(true);

        tokenizer.nextToken();
        if (tokenizer.ttype != StreamTokenizer.TT_NUMBER)
            return (-1);
        simulationCount = (int) tokenizer.nval;
        while (tokenizer.nextToken() != StreamTokenizer.TT_EOF) {
            if (readLine(tokenizer) == -1)
                quit(1, "Invalid scenario file (line " + tokenizer.lineno() + ").");
        }
        return (0);
    }

    public static void main(String[] args) {
        if (args.length != 1)
            quit(1, "Please provide a scenario file as argument.");

        try {
            int ret = readFile(args[0]);
            if (ret == -1)
                quit(1, "Invalid scenario file.");
        } catch (IOException e) {
            quit(1, "Could not find file: " + args[0]);
        }
    }
}
