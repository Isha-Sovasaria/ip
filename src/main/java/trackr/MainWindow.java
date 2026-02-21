package trackr;

import static trackr.Ui.SEPARATOR_LINE;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.animation.PauseTransition;
import javafx.util.Duration;
import javafx.application.Platform;

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

    private final Image userImage = loadImage("/images/user.jpg");
    private final Image trackrImage = loadImage("/images/Trackr.jpg");

    private Image loadImage(String path) {
        try (var stream = getClass().getResourceAsStream(path)) {
            return stream != null ? new Image(stream) : null;
        } catch (Exception e) {
            return null;
        }
    }

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

        // Close GUI if user typed "bye"
        if (input.trim().equals("bye")) {
            PauseTransition pause = new PauseTransition(Duration.seconds(1));
            pause.setOnFinished(event -> Platform.exit());
            pause.play();
        }
    }
}
