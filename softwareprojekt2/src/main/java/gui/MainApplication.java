package gui;

import javafx.application.Application;
import javafx.application.Preloader;
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

    private Stage primStage;
    private Scene scene;
    private Controller controller;

    @Override
    public void init() throws Exception {
        notifyPreloader(new Preloader.ProgressNotification(0.1));
        BasicConfigurator.configure();
        notifyPreloader(new Preloader.ProgressNotification(0.2));
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("NewPersistenceUnit");
        notifyPreloader(new Preloader.ProgressNotification(0.3));
        PersonalEntityManagerFactory.setEntityManagerFactory(entityManagerFactory);
        notifyPreloader(new Preloader.ProgressNotification(0.4));

        ResourceBundle bundle = ResourceBundle.getBundle("UIResources", new Locale("de"));
        notifyPreloader(new Preloader.ProgressNotification(0.5));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample.fxml"), bundle);
        notifyPreloader(new Preloader.ProgressNotification(0.6));
        BorderPane borderPane = loader.load();
        notifyPreloader(new Preloader.ProgressNotification(0.7));
        scene = new Scene(borderPane);
        notifyPreloader(new Preloader.ProgressNotification(0.8));

        controller = loader.getController();
        notifyPreloader(new Preloader.ProgressNotification(0.9));
        super.init();
        notifyPreloader(new Preloader.ProgressNotification(1));
    }

    /**
     * Loads the gui with a fxml loader, sets the title of the application
     * and the window size.
     *
     * @param primaryStage The window of the application.
     * @throws Exception If the loading of the fxml fileMenu fails.
     */
    public void start(Stage primaryStage) throws IOException {
        primStage=primaryStage;
        primStage.setTitle("Syndromansatz");
        primStage.setScene(scene);
        primStage.getIcons().add(new Image("/GraphItLogo.png"));
        primStage.setMaximized(true);
        controller.setStage(primStage);
        controller.initButtonShortcuts();
        controller.getRoot().requestFocus();

        primaryStage.show();
    }
}
