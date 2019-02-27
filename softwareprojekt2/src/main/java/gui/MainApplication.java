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
    private double loadingProgress=0;

    private void addLoadingProgress(int msBeforeProgress, double addedProgress) throws InterruptedException {
        Thread.sleep(msBeforeProgress);
        loadingProgress += addedProgress;
        notifyPreloader(new Preloader.ProgressNotification(loadingProgress));
    }

    @Override
    public void init() throws Exception {
        addLoadingProgress(20,0.1);
        BasicConfigurator.configure();
        addLoadingProgress(20,0.15);
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("NewPersistenceUnit");
        addLoadingProgress(20,0.1);
        PersonalEntityManagerFactory.setEntityManagerFactory(entityManagerFactory);
        addLoadingProgress(20,0.1);

        ResourceBundle bundle = ResourceBundle.getBundle("UIResources", new Locale("de"));
        addLoadingProgress(20,0.1);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample.fxml"), bundle);
        addLoadingProgress(20,0.15);
        BorderPane borderPane = loader.load();
        addLoadingProgress(20,0.1);
        scene = new Scene(borderPane);
        addLoadingProgress(20,0.1);
        controller = loader.getController();
        addLoadingProgress(20,0.1);
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
        primaryStage.getIcons().add(new Image("/GraphItLogo.png"));
        primaryStage.setMaximized(true);
        controller.setStage(primaryStage);
        controller.initButtonShortcuts();
        controller.getRoot().requestFocus();

        primaryStage.show();
    }
}
