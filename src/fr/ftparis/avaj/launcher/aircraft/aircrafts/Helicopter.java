package fr.ftparis.avaj.launcher.aircraft.aircrafts;

import fr.ftparis.avaj.launcher.Flyable;
import fr.ftparis.avaj.launcher.WeatherProvider;
import fr.ftparis.avaj.launcher.WeatherTower;
import fr.ftparis.avaj.launcher.aircraft.Aircraft;
import fr.ftparis.avaj.launcher.aircraft.Coordinates;

public class Helicopter extends Aircraft implements Flyable {
    private WeatherTower weatherTower;

    public Helicopter(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    @Override
    public void updateConditions() {
        switch (WeatherProvider.getProvider().getCurrentWeather(this.coordinates)) {
            case "SUN" -> {
                this.coordinates.increaseLongitudeBy(10);
                this.coordinates.increaseHeightBy(2);
                super.updateConditions("What a beautiful sunny day.");
            }
            case "RAIN" -> {
                this.coordinates.increaseLongitudeBy(5);
                super.updateConditions("Rain, again! Are we in March already?!");
            }
            case "FOG" -> {
                this.coordinates.increaseLongitudeBy(1);
                super.updateConditions("And now here comes the fog -_-");
            }
            case "SNOW" -> {
                this.coordinates.decreaseHeightBy(12);
                super.updateConditions("It's snowing! Not good for my rotor.");
            }
            default -> { super.updateConditions("Well, this place is actually neutral."); }
        }
    }
}
