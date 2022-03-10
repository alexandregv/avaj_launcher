package fr.ftparis.avaj.launcher.aircraft;

import fr.ftparis.avaj.launcher.Flyable;
import fr.ftparis.avaj.launcher.Simulator;
import fr.ftparis.avaj.launcher.aircraft.aircrafts.Baloon;
import fr.ftparis.avaj.launcher.aircraft.aircrafts.Helicopter;
import fr.ftparis.avaj.launcher.aircraft.aircrafts.JetPlane;
import fr.ftparis.avaj.launcher.exceptions.InvalidAircraftType;
import fr.ftparis.avaj.launcher.exceptions.UnknownAircraftType;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class AircraftFactory {
    public Flyable newAircraft(AircraftInfos infos) throws UnknownAircraftType, InvalidAircraftType {
        return newAircraft(infos.type(), infos.name(), infos.longitude(), infos.longitude(), infos.height());
    }

    public Flyable newAircraft(String type, String name, int longitude, int latitude, int height) throws UnknownAircraftType, InvalidAircraftType {
        Coordinates coordinates = new Coordinates(longitude, latitude, height);

        try {
            Class<?> clazz = Class.forName("fr.ftparis.avaj.launcher.aircraft.aircrafts." + type);
            Constructor<?> constructor = clazz.getConstructor(String.class, Coordinates.class);
            Object instance = constructor.newInstance(name, coordinates);
            if (instance instanceof Aircraft)
                if (instance instanceof Flyable)
                    return (Flyable) instance;
                else throw new InvalidAircraftType(type + " does not implement Flyable.");
            else throw new InvalidAircraftType(type + " does not extends Aircraft.");
        } catch(ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException exception) {
            if (exception instanceof ClassNotFoundException)
                throw new UnknownAircraftType("Aircraft type " + type + " does not exist.");
            else
                throw new InvalidAircraftType("Class " + type + " is invalid.");
        }
    }
}
