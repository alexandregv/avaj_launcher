package fr.ftparis.avaj.launcher;

import fr.ftparis.avaj.launcher.aircraft.Coordinates;
import fr.ftparis.avaj.launcher.Tower;

public class WeatherTower extends Tower {
    public String getWeather(Coordinates coordinates) {

        return ("");
    }

    protected void changeWeather() {
        System.out.println("WeatherTower: Weather is changing!");
        this.conditionsChanged();
    }
}
