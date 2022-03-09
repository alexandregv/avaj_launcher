package fr.ftparis.avaj.launcher;

public interface Flyable {
    public void updateConditions();

    public void registerTower(WeatherTower weatherTower);

    public void unregisterTower(WeatherTower weatherTower);
}
