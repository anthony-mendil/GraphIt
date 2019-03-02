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

    private Scene scene;
    private Controller controller;

    @Override
    public void init() throws Exception {
        notifyPreloader(new Preloader.ProgressNotification(0));
        BasicConfigurator.configure();
        notifyPreloader(new Preloader.ProgressNotification(0.14));
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("NewPersistenceUnit");
        notifyPreloader(new Preloader.ProgressNotification(0.28));
        PersonalEntityManagerFactory.setEntityManagerFactory(entityManagerFactory);
        notifyPreloader(new Preloader.ProgressNotification(0.42));
        ResourceBundle bundle = ResourceBundle.getBundle("UIResources", new Locale("de"));
        notifyPreloader(new Preloader.ProgressNotification(0.56));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample.fxml"), bundle);
        notifyPreloader(new Preloader.ProgressNotification(0.70));
        BorderPane borderPane = loader.load();
        notifyPreloader(new Preloader.ProgressNotification(0.84));
        scene = new Scene(borderPane);
        notifyPreloader(new Preloader.ProgressNotification(0.92));
        controller = loader.getController();
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
        primaryStage.setTitle("Syndromansatz");
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream(Values.getInstance().getLOGO_MAIN())));
        primaryStage.setMaximized(true);
        controller.setStage(primaryStage);
        controller.initButtonShortcuts();
        controller.getRoot().requestFocus();

        primaryStage.show();
    }
}
