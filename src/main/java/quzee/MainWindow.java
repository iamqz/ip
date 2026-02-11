package quzee;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for the main GUI
 */
public class MainWindow extends AnchorPane {

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox dialogContainer;

    @FXML
    private TextField userInput;

    @FXML
    private Button sendButton;

    private Quzee quzee;

    private Image userImage = new Image(getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    private void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Inject the Quzee instance
     */
    public void setQuzee(Quzee quzee) {
        this.quzee = quzee;
        dialogContainer.getChildren().add(
                DialogBox.getQuzeeDialogBox(quzee.getWelcomeMessage(), dukeImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing the user input and the other containing Quzee's reply and then appends
     * them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText().strip();
        String response = quzee.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialogBox(input, userImage),
                DialogBox.getQuzeeDialogBox(response, dukeImage)
        );
        userInput.clear();
    }
}
