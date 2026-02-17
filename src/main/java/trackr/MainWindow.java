package trackr;

import static trackr.Ui.SEPARATOR_LINE;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for the main GUI.
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

    private Trackr trackr;

    private final Image userImage =
            new Image(this.getClass().getResourceAsStream("/images/user.jpg"));

    private final Image trackrImage =
            new Image(this.getClass().getResourceAsStream("/images/trackr.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects Trackr backend */
    public void setTrackr(Trackr t) {
        trackr = t;
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (input == null || input.isBlank()) {
            return;
        }

        String response = trackr.getResponse(input);
        response = response.replace(SEPARATOR_LINE, "").trim();

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getTrackrDialog(response, trackrImage)
        );

        userInput.clear();
    }
}
