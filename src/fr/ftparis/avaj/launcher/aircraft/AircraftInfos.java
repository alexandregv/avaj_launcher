package fr.ftparis.avaj.launcher.aircraft;

public record AircraftInfos(String type, String name, int longitude, int latitude, int height) {

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
