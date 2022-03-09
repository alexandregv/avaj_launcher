package fr.ftparis.avaj.launcher;

import fr.ftparis.avaj.launcher.aircraft.AircraftInfos;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static fr.ftparis.avaj.launcher.Utils.quit;

public class ScenarioFile {
    public static void initFromScenarioFile(String filename) throws IOException {
        StreamTokenizer tokenizer = initTokenizer(filename);
        int simulationCount;
        List<AircraftInfos> aircraftInfosList;

        simulationCount = readSimulationCount(tokenizer);
        aircraftInfosList = readAllAircraftInfos(tokenizer);

        new Simulation(simulationCount, aircraftInfosList);
    }

    private static StreamTokenizer initTokenizer(String filename) {
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(filename);
        } catch (FileNotFoundException e) {
            quit(1, "Could not find scenario file: " + filename);
        }
        StreamTokenizer tokenizer = new StreamTokenizer(fileReader);

        tokenizer.eolIsSignificant(true);
        return tokenizer;
    }

    private static int readSimulationCount(StreamTokenizer tokenizer) throws IOException {
        int simulationCount;

        if (tokenizer.nextToken() != StreamTokenizer.TT_NUMBER)
            quit(1, "Invalid scenario file, first line should contain the simulation count.");
        simulationCount = (int) tokenizer.nval;

        if (tokenizer.nextToken() != StreamTokenizer.TT_EOL)
            quit(1, "Invalid scenario file, unexpected values after simulation count (line 1)");

        return simulationCount;
    }

    private static List<AircraftInfos> readAllAircraftInfos(StreamTokenizer tokenizer) throws IOException {
        List<AircraftInfos> aircraftInfosList = new ArrayList<>();

        tokenizer.nextToken();
        do {
            AircraftInfos aircraftInfos = readOneAircraftInfos(tokenizer);
            aircraftInfosList.add(aircraftInfos);
        } while (tokenizer.nextToken() != StreamTokenizer.TT_EOF);
        return aircraftInfosList;
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
}