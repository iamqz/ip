package quzee;

import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;

// Solution below adapted from https://se-education.org/guides/tutorials/javaFxPart2.html

/**
 * DialogBox component for reusability.
 * Represents a dialog box consisting of an ImageView to represent the speaker's face
 * and a label containing text from the speaker.
 */
public class DialogBox extends HBox {

    @FXML
    private Label label;

    @FXML
    private ImageView imageView;

    /**
     * Creates a DialogBox.
     * @param text Text to be shown.
     * @param image Image to be shown.
     */
    public DialogBox(String text, Image image) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }

        label.setText(text);
        imageView.setImage(image);

        Circle imageViewCircle = new Circle(25.0, 25.0, 25.0);
        imageView.setClip(imageViewCircle);
    }

    private void flip() {
        ObservableList<Node> temp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(temp);
        getChildren().setAll(temp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Returns the dialog box of the user.
     * @param text User Input.
     * @param image User Image.
     * @return The created DialogBox.
     */
    public static DialogBox getUserDialogBox(String text, Image image) {
        var temp = new DialogBox(text, image);
        temp.label.getStyleClass().add("user-label");
        return temp;
    }

    /**
     * Returns the dialog box of the quzee.
     * @param text User Input.
     * @param image User Image.
     * @return The created DialogBox.
     */
    public static DialogBox getQuzeeDialogBox(String text, Image image) {
        var temp = new DialogBox(text, image);
        temp.label.getStyleClass().add("quzee-label");
        temp.flip();
        return temp;
    }
}
