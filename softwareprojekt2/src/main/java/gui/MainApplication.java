package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import log_management.dao.PersonalEntityManagerFactory;
import org.apache.log4j.BasicConfigurator;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Starts the whole application.
 */
public class MainApplication extends Application {


    @Override
    public void init() throws Exception {
        BasicConfigurator.configure();
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("NewPersistenceUnit");
        PersonalEntityManagerFactory.setEntityManagerFactory(entityManagerFactory);
        super.init();
    }

    /**
     * Loads the gui with a fxml loader, sets the title of the application
     * and the window size.
     *
     * @param primaryStage The window of the application.
     * @throws Exception If the loading of the fxml fileMenu fails.
     */
    public void start(Stage primaryStage) throws IOException {
        ResourceBundle bundle = ResourceBundle.getBundle("UIResources", new Locale("de"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample.fxml"), bundle);
        BorderPane borderPane = loader.load();
        primaryStage.setTitle("Syndromansatz");

        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image("/GraphItLogo.png"));
        primaryStage.setMaximized(true);

        Controller controller = loader.getController();
        controller.setStage(primaryStage);
        controller.initButtonShortcuts();
        controller.getRoot().requestFocus();

        primaryStage.show();
    }
}
