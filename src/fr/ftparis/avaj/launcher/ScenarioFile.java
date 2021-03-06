package fr.ftparis.avaj.launcher;

import fr.ftparis.avaj.launcher.aircraft.AircraftInfos;
import fr.ftparis.avaj.launcher.exceptions.InvalidScenarioFileException;
import fr.ftparis.avaj.launcher.exceptions.ScenarioFileNotFoundException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.List;

import static fr.ftparis.avaj.launcher.Simulator.error;

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
            Simulator.error(new ScenarioFileNotFoundException("Could not find scenario file: " + filename));
        }
        StreamTokenizer tokenizer = new StreamTokenizer(fileReader);

        tokenizer.eolIsSignificant(true);
        return tokenizer;
    }

    private static int readSimulationCount(StreamTokenizer tokenizer) throws IOException {
        int simulationCount;

        if (tokenizer.nextToken() != StreamTokenizer.TT_NUMBER)
            Simulator.error(new InvalidScenarioFileException("Invalid scenario file, first line should contain the simulation count."));
        simulationCount = (int) tokenizer.nval;
        if (simulationCount <= 0)
            Simulator.error(new InvalidScenarioFileException("Invalid simulationCount, must be > 0 (line 1)."));

        if (tokenizer.nextToken() != StreamTokenizer.TT_EOL)
            Simulator.error(new InvalidScenarioFileException("Invalid scenario file, unexpected values after simulation count (line 1)"));

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
            Simulator.error(new InvalidScenarioFileException("Error on 'type' token (line " + tokenizer.lineno() + ")."));
        }
        type = tokenizer.sval;

        if (tokenizer.nextToken() != StreamTokenizer.TT_WORD) {
            Simulator.error(new InvalidScenarioFileException("Error on 'name' token (line " + tokenizer.lineno() + ")."));
        }
        name = tokenizer.sval;

        if (tokenizer.nextToken() != StreamTokenizer.TT_NUMBER) {
            Simulator.error(new InvalidScenarioFileException("Error on 'longitude' token (line " + tokenizer.lineno() + ")."));
        }
        longitude = (int) tokenizer.nval;
        if (longitude < 0) {
            Simulator.error(new InvalidScenarioFileException("Error, 'longitude' must be > 0 (line " + tokenizer.lineno() + ")."));
        }

        if (tokenizer.nextToken() != StreamTokenizer.TT_NUMBER) {
            Simulator.error(new InvalidScenarioFileException("Error on 'latitude' token (line " + tokenizer.lineno() + ")."));
        }
        latitude = (int) tokenizer.nval;
        if (latitude < 0) {
            Simulator.error(new InvalidScenarioFileException("Error, 'latitude' must be > 0 (line " + tokenizer.lineno() + ")."));
        }

        if (tokenizer.nextToken() != StreamTokenizer.TT_NUMBER) {
            Simulator.error(new InvalidScenarioFileException("Error on height token (line " + tokenizer.lineno() + ")."));
        }
        height = (int) tokenizer.nval;
        if (height > 100)
            height = 100;
        if (height <= 0) {
            Simulator.error(new InvalidScenarioFileException("Error, 'height' must be > 0 (line " + tokenizer.lineno() + ")."));
        }

        if (tokenizer.nextToken() != StreamTokenizer.TT_EOL) {
            Simulator.error(new InvalidScenarioFileException("Error, unexpected tokens at end of line (line " + tokenizer.lineno() + ")."));
        }

        return new AircraftInfos(type, name, longitude, latitude, height);
    }
}