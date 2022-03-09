package fr.ftparis.avaj.launcher;

import fr.ftparis.avaj.launcher.aircraft.Coordinates;

public class WeatherProvider {
    private static final WeatherProvider SINGLETON = new WeatherProvider();
    private final String[] weather = {"RAIN", "FOG", "SUN", "SNOW"};

    private WeatherProvider() {

    }

    public static WeatherProvider getProvider() {
        return SINGLETON;
    }

    public String getCurrentWeather(Coordinates coordinates) {

        return ("");
    }
}
