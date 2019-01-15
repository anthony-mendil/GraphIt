package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import lombok.Getter;

/**
 * Starts the whole application.
 */
public class Main extends Application{

    /**
     * The window of the application.
     */
    @Getter
    private Stage primary;

    /**
     * Loads the gui with a fxml loader, sets the title of the application
     * and the window size.
     *
     * @param primaryStage  The window of the application.
     * @throws Exception If the loading of the fxml file fails.
     */
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample.fxml"));
        Controller controller = new Controller();
        loader.setController(controller);
        BorderPane borderPane = loader.load();
        primaryStage.setTitle("Syndromansatz");
        primaryStage.setScene(new Scene(borderPane, 1280, 720));
        //primaryStage.getIcons().add(new Image("/logo.png"));
        primaryStage.show();
    }

    /**
     * Starts the application.
     * @param args The java command line arguments that is needed to start the application.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
