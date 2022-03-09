package fr.ftparis.avaj.launcher;

import fr.ftparis.avaj.launcher.aircraft.AircraftInfos;

import java.util.List;
import java.util.stream.IntStream;

public class Simulation {
    public Simulation(int weatherChangescount, List<AircraftInfos> aircraftInfosList) {
        WeatherTower tower = new WeatherTower();

//      aircraftInfosList.forEach(System.out::println);
        IntStream.range(0, weatherChangescount).forEach(i -> tower.changeWeather());

    }
}
