package fr.ftparis.avaj.launcher.aircraft;

public class Coordinates {
    private int longitude;
    private int latitude;
    private int height;

    Coordinates(int longitude, int latitude, int height) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.height = height;
    }

    public int getLongitude() {
        return this.longitude;
    }

    public int getLatitude() {
        return this.latitude;
    }

    public int getHeight() {
        return this.height;
    }

    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }

    public void setHeight(int height) { this.height = height; }

    public void setCoordinates(Coordinates coordinates) {
       this.setCoordinates(coordinates.getLongitude(), coordinates.getLatitude(), coordinates.getHeight());
    }
    public void setCoordinates(int longitude, int latitude, int height) {
        this.setLongitude(longitude);
        this.setLatitude(latitude);
        this.setHeight(height);
    }

    public void increaseLongitudeBy(int amount) {
        this.setLongitude(this.getLongitude() + amount);
    }

    public void decreaseLongitudeBy(int amount) {
        this.setLongitude(this.getLongitude() - amount);
    }

    public void increaseLatitudeBy(int amount) {
        this.setLatitude(this.getLatitude() + amount);
    }

    public void decreaseLatitudeBy(int amount) {
        this.setLatitude(this.getLatitude() - amount);
    }

    public void increaseHeightBy(int amount) {
        this.setHeight(this.getHeight() + amount);
    }

    public void decreaseHeightBy(int amount) {
        this.setHeight(this.getHeight() - amount);
    }

    public String toString() {
        return "(" + this.getLongitude() + ", " + this.getLatitude() + ",  " + this.getHeight() + ")";
    }
}
