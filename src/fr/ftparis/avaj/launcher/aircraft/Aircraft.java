package fr.ftparis.avaj.launcher.aircraft;

import fr.ftparis.avaj.launcher.Flyable;
import fr.ftparis.avaj.launcher.WeatherTower;

public class Aircraft {
    protected long id;
    protected String name;
    protected Coordinates coordinates;
    private static long idCounter = 0;
    protected WeatherTower weatherTower;

    public Aircraft(String name, Coordinates coordinates) {
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
        System.out.println("[LOG] " + this.getClass().getSimpleName() + "#" + this.getName() + "(" + this.getId() + ") registered to weather tower.");
    }

    public void unregisterTower(WeatherTower weatherTower) {
        this.weatherTower.unregister((Flyable) this);
        System.out.println("[LOG] " + this.getClass().getSimpleName() + "#" + this.getName() + "(" + this.getId() + ") unregistered from weather tower.");
    }

    public void updateConditions(String message) {
        if (this.getCoordinates().getHeight() > 100)
            this.getCoordinates().setHeight(100);
        else if (this.getCoordinates().getHeight() < 0) {
            this.getCoordinates().setHeight(0);
            // TODO : unregister and land
        }
        System.out.println(this.getClass().getSimpleName() + "#" + this.getName() + "(" + this.getId() + "): " + message);
    }

    public String toString() {
        return this.name + "#" + this.id + "(" + this.coordinates.getLongitude() + "," + this.coordinates.getLatitude() + "," + this.coordinates.getHeight() + ")";
    }
}