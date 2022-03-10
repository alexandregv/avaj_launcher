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
                super.updateConditions("Ahhh some sun, what a pleasure.");
            }
            case "RAIN" -> {
                this.coordinates.decreaseHeightBy(5);
                super.updateConditions("OMG it's raining! Hope the flame doesn't go down.");
            }
            case "FOG" -> {
                this.coordinates.decreaseHeightBy(3);
                super.updateConditions("Well, fog or not, I can't control this balloon...");
            }
            case "SNOW" -> {
                this.coordinates.decreaseHeightBy(15);
                super.updateConditions("Crap, some snow! It will be hard to keep this balloon warm.");
            }
            default -> super.updateConditions("Well, this place is actually neutral.");
        }
    }
}
