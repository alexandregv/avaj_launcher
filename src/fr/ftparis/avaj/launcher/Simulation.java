package fr.ftparis.avaj.launcher;

import fr.ftparis.avaj.launcher.aircraft.Aircraft;
import fr.ftparis.avaj.launcher.aircraft.AircraftFactory;
import fr.ftparis.avaj.launcher.aircraft.AircraftInfos;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Simulation {
    public Simulation(int weatherChangesCount, List<AircraftInfos> aircraftInfosList) {
        WeatherTower tower = new WeatherTower();
        AircraftFactory factory = new AircraftFactory();

//      aircraftInfosList.forEach(System.out::println);
        aircraftInfosList.forEach(infos -> factory.newAircraft(infos).registerTower(tower));
        IntStream.range(0, weatherChangesCount).forEach(i -> tower.changeWeather());
    }
}
