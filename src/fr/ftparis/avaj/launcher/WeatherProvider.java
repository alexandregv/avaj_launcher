package fr.ftparis.avaj.launcher;

import fr.ftparis.avaj.launcher.aircraft.Coordinates;
import fr.ftparis.avaj.launcher.tower.WeatherTower;

public class WeatherProvider {
    private static final WeatherProvider SINGLETON = new WeatherProvider();
    private String[] weather = {};

    private WeatherProvider() {

    }

    public static WeatherProvider getProvider() {
        return SINGLETON;
    }

    public String getCurrentWeather(Coordinates coordinates) {

        return ("");
    }
}
