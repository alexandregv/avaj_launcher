package fr.ftparis.avaj.launcher.aircraft;

import fr.ftparis.avaj.launcher.Flyable;
import fr.ftparis.avaj.launcher.aircraft.aircrafts.Baloon;
import fr.ftparis.avaj.launcher.aircraft.aircrafts.Helicopter;
import fr.ftparis.avaj.launcher.aircraft.aircrafts.JetPlane;
import fr.ftparis.avaj.launcher.exceptions.UnknownAircraftType;

public class AircraftFactory {
    public Flyable newAircraft(AircraftInfos infos) throws UnknownAircraftType {
        return newAircraft(infos.type(), infos.name(), infos.longitude(), infos.longitude(), infos.height());
    }

    public Flyable newAircraft(String type, String name, int longitude, int latitude, int height) throws UnknownAircraftType {
        Coordinates coordinates = new Coordinates(longitude, latitude, height);

        return switch (type) {
            case "Baloon" -> new Baloon(name, coordinates);
            case "Helicopter" -> new Helicopter(name, coordinates);
            case "JetPlane" -> new JetPlane(name, coordinates);
            default -> throw new UnknownAircraftType("Aircraft type " + type + " does not exist.");
        };
    }
}
