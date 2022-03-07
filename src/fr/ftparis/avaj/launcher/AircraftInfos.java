package fr.ftparis.avaj.launcher;

import fr.ftparis.avaj.launcher.aircraft.Aircraft;

public final class AircraftInfos {
    private final String type, name;
    private final int longitude, latitude, height;

    public AircraftInfos(String type, String name, int longitude, int latitude, int height) {
        this.type = type;
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
        this.height = height;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public int getLongitude() {
        return longitude;
    }

    public int getLatitude() {
        return latitude;
    }

    public int getHeight() {
        return height;
    }

    public String toString() {
        return "TYPE: " + this.type + "\nNAME: " + this.name + "\nLONGITUDE: " + this.longitude + "\nLATITUDE: " + this.latitude + "\nHEIGHT: " + height + "\n";
    }
}
