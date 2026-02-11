package quzee;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

// Solution below adapted from https://se-education.org/guides/tutorials/javaFxPart2.html

/**
 * DialogBox component for reusability
 */
public class DialogBox extends HBox {

    private Label label;
    private ImageView imageView;

    public DialogBox(String text, Image image) {
        label = new Label(text);
        imageView = new ImageView(image);

        //Styling the dialog box
        label.setWrapText(true);
        imageView.setFitWidth(100.0);
        imageView.setFitHeight(100.0);
        this.setAlignment(Pos.TOP_RIGHT);

        this.getChildren().addAll(label, imageView);
    }
}
