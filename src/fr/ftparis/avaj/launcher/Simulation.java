package fr.ftparis.avaj.launcher;

import fr.ftparis.avaj.launcher.aircraft.AircraftFactory;
import fr.ftparis.avaj.launcher.aircraft.AircraftInfos;
import fr.ftparis.avaj.launcher.exceptions.InvalidAircraftTypeException;
import fr.ftparis.avaj.launcher.exceptions.UnknownAircraftTypeException;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.IntStream;

public class Simulation {
    public Simulation(int weatherChangesCount, List<AircraftInfos> aircraftInfosList) {
        WeatherTower tower = new WeatherTower();
        AircraftFactory factory = new AircraftFactory();
        Logger logger = Logger.getLogger(Simulator.class.getPackage().getName());

        aircraftInfosList.forEach(i -> logger.finest(i.toString()));
        aircraftInfosList.forEach(infos -> {
            try {
                factory.newAircraft(infos).registerTower(tower);
            } catch (UnknownAircraftTypeException | InvalidAircraftTypeException exception) {
                Simulator.error(exception);
            }
        });
        IntStream.range(0, weatherChangesCount).forEach(i -> tower.changeWeather());
    }
}
