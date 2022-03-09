package fr.ftparis.avaj.launcher.aircraft.aircrafts;

import fr.ftparis.avaj.launcher.Flyable;
import fr.ftparis.avaj.launcher.aircraft.Aircraft;
import fr.ftparis.avaj.launcher.aircraft.Coordinates;
import fr.ftparis.avaj.launcher.WeatherTower;

public class Baloon extends Aircraft implements Flyable {

    public Baloon(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    @Override
    public void updateConditions() {
        System.out.println(this.name + " Wow the weather has changed! " + weatherTower.getWeather(this.coordinates));
    }
}
