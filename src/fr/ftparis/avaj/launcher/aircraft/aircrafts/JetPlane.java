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
                super.updateConditions("Ah, the sun is back!");
            }
            case "RAIN" -> {
                this.coordinates.increaseLatitudeBy(5);
                super.updateConditions("Bruh that's rainy here.");
            }
            case "FOG" -> {
                this.coordinates.increaseLatitudeBy(1);
                super.updateConditions("Can't see anything there, looks like my first days at 42.");
            }
            case "SNOW" -> {
                this.coordinates.decreaseHeightBy(7);
                super.updateConditions("Winter is coming...");
            }
            default -> super.updateConditions("Well, this place is actually neutral.");
        }
    }
}
