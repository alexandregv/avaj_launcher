package fr.ftparis.avaj.launcher;

import fr.ftparis.avaj.launcher.tower.WeatherTower;

public interface Flyable {
    public void updateConditions();
    public void registerTower(WeatherTower weatherTower);
}
