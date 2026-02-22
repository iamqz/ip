package quzee;

import javafx.application.Application;

// Solution below adapted from https://se-education.org/guides/tutorials/javaFxPart1.html

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {

    /**
     * Main method of the Launcher.
     * @param args (if any) for the method.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
