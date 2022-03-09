package fr.ftparis.avaj.launcher.aircraft.aircrafts;

import fr.ftparis.avaj.launcher.Flyable;
import fr.ftparis.avaj.launcher.WeatherProvider;
import fr.ftparis.avaj.launcher.WeatherTower;
import fr.ftparis.avaj.launcher.aircraft.Aircraft;
import fr.ftparis.avaj.launcher.aircraft.Coordinates;

public class JetPlane extends Aircraft implements Flyable {
    private WeatherTower weatherTower;

    public JetPlane(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    @Override
    public void updateConditions() {
        switch (WeatherProvider.getProvider().getCurrentWeather(this.coordinates)) {
            case "SUN" -> {
                this.coordinates.increaseLatitudeBy(10);
                this.coordinates.increaseHeightBy(2);
            }
            case "RAIN" -> {
                this.coordinates.increaseLatitudeBy(5);
            }
            case "FOG" -> {
                this.coordinates.increaseLatitudeBy(1);
            }
            case "SNOW" -> {
                this.coordinates.decreaseHeightBy(7);
            }
            default -> {}
        }
    }
}
