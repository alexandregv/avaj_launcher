package fr.ftparis.avaj.launcher.aircraft;

import fr.ftparis.avaj.launcher.Flyable;
import fr.ftparis.avaj.launcher.exceptions.InvalidAircraftTypeException;
import fr.ftparis.avaj.launcher.exceptions.UnknownAircraftTypeException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class AircraftFactory {
    public Flyable newAircraft(AircraftInfos infos) throws UnknownAircraftTypeException, InvalidAircraftTypeException {
        return newAircraft(infos.type(), infos.name(), infos.longitude(), infos.longitude(), infos.height());
    }

    public Flyable newAircraft(String type, String name, int longitude, int latitude, int height) throws UnknownAircraftTypeException, InvalidAircraftTypeException {
        Coordinates coordinates = new Coordinates(longitude, latitude, height);

        try {
            Class<?> clazz = Class.forName("fr.ftparis.avaj.launcher.aircraft." + type);
            Constructor<?> constructor = clazz.getDeclaredConstructor(String.class, Coordinates.class);
            Object instance = constructor.newInstance(name, coordinates);
            if (instance instanceof Aircraft)
                if (instance instanceof Flyable)
                    return (Flyable) instance;
                else throw new InvalidAircraftTypeException(type + " does not implement Flyable.");
            else throw new InvalidAircraftTypeException(type + " does not extends Aircraft.");
        } catch(ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException exception) {
            if (exception instanceof ClassNotFoundException)
                throw new UnknownAircraftTypeException("Aircraft type " + type + " does not exist.");
            else
                throw new InvalidAircraftTypeException("Class " + type + " is invalid.");
        }
    }
}
