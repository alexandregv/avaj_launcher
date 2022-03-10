package fr.ftparis.avaj.launcher.exceptions;

import java.io.FileNotFoundException;

public class ScenarioFileNotFoundException extends FileNotFoundException {
    public ScenarioFileNotFoundException(String message) {
        super(message);
    }
}