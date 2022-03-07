package fr.ftparis.avaj.launcher.aircraft;

import fr.ftparis.avaj.launcher.Flyable;
import fr.ftparis.avaj.launcher.aircraft.aircrafts.Baloon;
import fr.ftparis.avaj.launcher.aircraft.aircrafts.Helicopter;
import fr.ftparis.avaj.launcher.aircraft.aircrafts.JetPlane;

public class AircraftFactory {
    public Flyable newAircraft(String type, String name, int longitude, int latitude, int height) {
        Coordinates coordinates = new Coordinates(longitude, latitude, height);

        return switch (type) {
            case "Baloon" -> new Baloon(name, coordinates);
            case "Helicopter" -> new Helicopter(name, coordinates);
            case "JetPlane" -> new JetPlane(name, coordinates);
            default -> null;
        };
    }
}
