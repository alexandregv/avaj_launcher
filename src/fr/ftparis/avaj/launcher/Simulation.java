package fr.ftparis.avaj.launcher;

import fr.ftparis.avaj.launcher.aircraft.AircraftFactory;
import fr.ftparis.avaj.launcher.aircraft.AircraftInfos;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.IntStream;

public class Simulation {
    public Simulation(int weatherChangesCount, List<AircraftInfos> aircraftInfosList) {
        WeatherTower tower = new WeatherTower();
        AircraftFactory factory = new AircraftFactory();
        Logger logger = Logger.getLogger(Simulator.class.getPackage().getName());

        aircraftInfosList.forEach(i -> logger.finest(i.toString()));

        aircraftInfosList.forEach(infos -> factory.newAircraft(infos).registerTower(tower));
        IntStream.range(0, weatherChangesCount).forEach(i -> tower.changeWeather());
    }
}
