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

//    private Quzee quzee = new Quzee();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();
            Scene scene = new Scene(anchorPane);
            stage.setScene(scene);

            // DEBUG: Add these two lines to force a visible size
            stage.setMinWidth(400);
            stage.setMinHeight(600);

            fxmlLoader.<MainWindow>getController().setQuzee(new Quzee());
            stage.setTitle("Quzee Chatbot");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
