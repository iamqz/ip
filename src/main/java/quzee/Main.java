package quzee;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

// Solution below adapted from https://se-education.org/guides/tutorials/javaFxPart1.html

/**
 * GUI for Quzee using FXML
 */
public class Main extends Application {

    private final Quzee quzee = new Quzee();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();
            Scene scene = new Scene(anchorPane);
            stage.setScene(scene);

            fxmlLoader.<MainWindow>getController().setQuzee(quzee);
            stage.setTitle("Quzee Chatbot");

            stage.setResizable(true);
            stage.setMinWidth(417.0); // Set a minimum width to prevent UI glitching
            stage.setMinHeight(600.0);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
