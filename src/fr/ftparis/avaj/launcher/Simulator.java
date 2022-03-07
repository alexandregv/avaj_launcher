package fr.ftparis.avaj.launcher;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;
import java.util.stream.IntStream;

public class Simulator {

    private static void quit(int exitStatus, String message) {
        System.out.println(message);
        System.exit(exitStatus);
    }

   private static int readSimulationCount(StreamTokenizer tokenizer) throws IOException {
       int simulationCount = 0;

       if (tokenizer.nextToken() != StreamTokenizer.TT_NUMBER)
           quit(1, "Invalid scenario file (line " + tokenizer.lineno() + ").");
       simulationCount = (int) tokenizer.nval;

       if (tokenizer.nextToken() != StreamTokenizer.TT_EOL)
           quit(1, "Invalid scenario file (line " + tokenizer.lineno() + ").");

       return simulationCount;
   }

    private static AircraftInfos readOneAircraftInfos(StreamTokenizer tokenizer) throws IOException {
       String type, name;
       int longitude, latitude, height;

       tokenizer.pushBack();
        if (tokenizer.nextToken() != StreamTokenizer.TT_WORD) {
            quit(1, "Error on 'type' token (line " + tokenizer.lineno() + ").");
        }
        type = tokenizer.sval;

        if (tokenizer.nextToken() != StreamTokenizer.TT_WORD) {
            quit(1, "Error on 'name' token (line " + tokenizer.lineno() + ").");
        }
        name = tokenizer.sval;

        if (tokenizer.nextToken() != StreamTokenizer.TT_NUMBER) {
            quit(1, "Error on 'longitude' token (line " + tokenizer.lineno() + ").");
        }
        longitude = (int) tokenizer.nval;
        if (longitude < 0) {
            quit(1, "Error, 'longitude' must be > 0 (line " + tokenizer.lineno() + ").");
        }

        if (tokenizer.nextToken() != StreamTokenizer.TT_NUMBER) {
            quit(1, "Error on 'latitude' token (line " + tokenizer.lineno() + ").");
        }
        latitude = (int) tokenizer.nval;
        if (latitude < 0) {
            quit(1, "Error, 'latitude' must be > 0 (line " + tokenizer.lineno() + ").");
        }

        if (tokenizer.nextToken() != StreamTokenizer.TT_NUMBER) {
            quit(1, "Error on height token (line " + tokenizer.lineno() + ").");
        }
        height = (int) tokenizer.nval;
        // TODO: Change to keep the real value and then make it 100 in the simulation
        if (height > 100)
            height = 100;
        if (height < 0 ) {
            quit(1, "Error, 'height' must be > 0 (line " + tokenizer.lineno() + ").");
        }

        if (tokenizer.nextToken() != StreamTokenizer.TT_EOL) {
            quit(1, "Error, unexpected tokens at end of line (line " + tokenizer.lineno() + ").");
        }

        return new AircraftInfos(type, name, longitude, latitude, height);
    }

    private static List<AircraftInfos> readAllAircraftInfos(StreamTokenizer tokenizer) throws IOException {
        List<AircraftInfos> aircraftInfosList = new ArrayList<AircraftInfos>();

        tokenizer.nextToken();
        do {
            AircraftInfos aircraftInfos = readOneAircraftInfos(tokenizer);
            if (aircraftInfos == null)
                quit(1, "Invalid scenario file (line " + tokenizer.lineno() + ").");
            aircraftInfosList.add(aircraftInfos);
        } while (tokenizer.nextToken() != StreamTokenizer.TT_EOF);
        return aircraftInfosList;
    }

    private static StreamTokenizer initTokenizer(String filename) {
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(filename);
        } catch (FileNotFoundException e) {
            quit(1, "Could not find file: " + filename);
        }
        StreamTokenizer tokenizer = new StreamTokenizer(fileReader);

        tokenizer.eolIsSignificant(true);
        return tokenizer;
    }

    private static void initFromScenarioFile(String filename) throws IOException {
        StreamTokenizer tokenizer = initTokenizer(filename);
        int simulationCount;
        List<AircraftInfos> aircraftInfosList;

        simulationCount = readSimulationCount(tokenizer);
        aircraftInfosList = readAllAircraftInfos(tokenizer);

        IntStream.range(0, simulationCount).forEach(i -> new Simulation(aircraftInfosList));
    }

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
