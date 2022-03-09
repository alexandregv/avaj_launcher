package fr.ftparis.avaj.launcher.aircraft.aircrafts;

import fr.ftparis.avaj.launcher.Flyable;
import fr.ftparis.avaj.launcher.WeatherProvider;
import fr.ftparis.avaj.launcher.aircraft.Aircraft;
import fr.ftparis.avaj.launcher.aircraft.Coordinates;

public class Baloon extends Aircraft implements Flyable {

    public Baloon(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    @Override
    public void updateConditions() {
        switch (WeatherProvider.getProvider().getCurrentWeather(this.coordinates)) {
            case "SUN" -> {
                this.coordinates.increaseLongitudeBy(2);
                this.coordinates.increaseHeightBy(4);
            }
            case "RAIN" -> {
                this.coordinates.decreaseHeightBy(5);
            }
            case "FOG" -> {
                this.coordinates.decreaseHeightBy(3);
            }
            case "SNOW" -> {
                this.coordinates.decreaseHeightBy(15);
            }
            default -> {}
        }
    }
}
