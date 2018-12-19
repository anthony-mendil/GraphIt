package gui;

import javafx.stage.Stage;
import lombok.Getter;

/**
 * Starts the whole application.
 */
public class Main {

    /**
     * The window of the application.
     */
    @Getter
    private Stage primary;

    /**
     * Loads the gui with a fxmlloader, sets the title of the application
     * and the window size.
     *
     * @param primaryStage  The window of the application.
     * @throws Exception If the loading of the fxml file fails.
     */
    public void start(Stage primaryStage) throws Exception {
        throw new UnsupportedOperationException();
    }

    /**
     * Starts the application.
     * @param args The java command line arguments that is needed to start the application.
     */
    public static void main(String[] args) {
       throw new UnsupportedOperationException();
    }
}
