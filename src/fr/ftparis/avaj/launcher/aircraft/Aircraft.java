package fr.ftparis.avaj.launcher.aircraft;

public class Aircraft {
    protected long id;
    protected String name;
    protected Coordinates coordinates;
    private static long idCounter = 0;

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
        this.coordinates = coordinates;
    }

    public void setCoordinates(int longitude, int latitude, int height) {
        this.coordinates.setCoordinates(longitude, latitude, height);
    }

    public String toString() {
        return this.name + "#" + this.id + "(" + this.coordinates.getLongitude() + "," + this.coordinates.getLatitude() + "," + this.coordinates.getHeight() + ")";
    }
}