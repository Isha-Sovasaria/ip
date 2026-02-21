package trackr;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private static final String DEFAULT_FILE_PATH = "data/trackr.txt";
    private Trackr trackr;

    public Main() {
        trackr = new Trackr(DEFAULT_FILE_PATH);
    }

    public Main(String filePath) {
        trackr = new Trackr(filePath);
    }

    @Override
    public void start(Stage stage) throws Exception {
        assert Main.class.getResource("/view/MainWindow.fxml") != null
                : "FXML file not found!";
        FXMLLoader loader = new FXMLLoader(
                Main.class.getResource("/view/MainWindow.fxml")
        );

        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.setTitle("Trackr");
        stage.show();

        MainWindow controller = loader.getController();
        assert controller != null : "Controller should not be null.";
        controller.setTrackr(trackr);
    }
}
