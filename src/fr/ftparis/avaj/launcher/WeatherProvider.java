package fr.ftparis.avaj.launcher;

import fr.ftparis.avaj.launcher.aircraft.Coordinates;

public class WeatherProvider {
    private static final WeatherProvider weatherProvider = new WeatherProvider();
    private final String[] weather = {"RAIN", "FOG", "SUN", "SNOW"};

    private WeatherProvider() {

    }

    public static WeatherProvider getProvider() {
        return weatherProvider;
    }

    public String getCurrentWeather(Coordinates coordinates) {
        long sum = (long) coordinates.getLongitude() + coordinates.getLongitude() + coordinates.getHeight();

        if (sum % 11 == 0)
            return weather[0];
        else if (sum % 3 == 0 || sum % 5 == 0)
            return weather[1];
        else if (sum % 7 == 0 || sum % 9 == 0)
            return weather[3];
        else
            return weather[2];
    }
}
