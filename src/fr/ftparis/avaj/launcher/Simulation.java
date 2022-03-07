package fr.ftparis.avaj.launcher;

import fr.ftparis.avaj.launcher.aircraft.AircraftInfos;

import java.util.List;

public class Simulation {
    public Simulation(List<AircraftInfos> aircraftInfosList) {
        System.out.println("I'm a Simulation!");
        aircraftInfosList.forEach(System.out::println);
    }
}
