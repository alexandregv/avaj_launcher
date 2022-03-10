package fr.ftparis.avaj.launcher;

import fr.ftparis.avaj.launcher.aircraft.Coordinates;

import java.util.logging.Level;
import java.util.logging.Logger;

public class WeatherTower extends Tower {
    public String getWeather(Coordinates coordinates) {
        return WeatherProvider.getProvider().getCurrentWeather(coordinates);
    }

    protected void changeWeather() {
        Logger.getLogger(Simulator.class.getPackage().getName()).log(Level.FINER, "WeatherTower: Weather is changing!");
        this.conditionsChanged();
    }
}
