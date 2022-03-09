package fr.ftparis.avaj.launcher;

import fr.ftparis.avaj.launcher.aircraft.Coordinates;

public class WeatherTower extends Tower {
    public String getWeather(Coordinates coordinates) {
        return WeatherProvider.getProvider().getCurrentWeather(coordinates);
    }

    protected void changeWeather() {
        System.out.println("WeatherTower: Weather is changing!");
        this.conditionsChanged();
    }
}
