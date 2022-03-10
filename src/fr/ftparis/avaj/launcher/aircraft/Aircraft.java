package fr.ftparis.avaj.launcher.aircraft;

import fr.ftparis.avaj.launcher.Flyable;
import fr.ftparis.avaj.launcher.Simulator;
import fr.ftparis.avaj.launcher.WeatherTower;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Aircraft {
    protected long id;
    protected String name;
    protected Coordinates coordinates;
    private static long idCounter = 0;
    protected WeatherTower weatherTower;
    private final Logger LOGGER = Logger.getLogger(Simulator.class.getPackage().getName());


    protected Aircraft(String name, Coordinates coordinates) {
        this.id = nextId();
        this.name = name;
        this.coordinates = coordinates;
    }

    private long nextId() {
        return idCounter++;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates.setCoordinates(coordinates);
    }

    public void setCoordinates(int longitude, int latitude, int height) {
        this.coordinates.setCoordinates(longitude, latitude, height);
    }

    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
        this.weatherTower.register((Flyable) this);
        this.log("registered to weather tower.");
    }

    public void unregisterTower(WeatherTower weatherTower) {
        this.weatherTower.unregister((Flyable) this);
        this.log("unregistered from weather tower.");
    }

    public void updateConditions(String message) {
        if (this.getCoordinates().getHeight() > 100)
            this.getCoordinates().setHeight(100);
        else if (this.getCoordinates().getHeight() <= 0) {
            this.getCoordinates().setHeight(0);
            this.log("Landing at " + this.getCoordinates() + ".");
            this.unregisterTower(this.weatherTower);
            return;
        }
        this.msg(message);
    }

    private void log(String message) {
        LOGGER.log(Level.INFO, this + ": " + message);
    }

    private void msg(String message) {
        LOGGER.log(Level.FINE, this + ": " + message);
    }

    public String toString() {
        return this.getClass().getSimpleName() + "#" + this.getName() + "(" + this.getId() + ")";
    }
}