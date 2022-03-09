package fr.ftparis.avaj.launcher.aircraft;

public record AircraftInfos(String type, String name, int longitude, int latitude, int height) {
    public String toString() {
        return "TYPE: " + this.type + "\nNAME: " + this.name + "\nLONGITUDE: " + this.longitude + "\nLATITUDE: " + this.latitude + "\nHEIGHT: " + height + "\n";
    }
}
