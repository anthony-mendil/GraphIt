package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import log_management.DatabaseManager;
import log_management.dao.PersonalEntityManagerFactory;
import log_management.tables.Log;
import lombok.Getter;
import org.apache.log4j.BasicConfigurator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Starts the whole application.
 */
public class Main extends Application {

    /**
     * The window of the application.
     */
    @Getter
    private Stage primary;


    /**
     * Loads the gui with a fxml loader, sets the title of the application
     * and the window size.
     *
     * @param primaryStage The window of the application.
     * @throws Exception If the loading of the fxml fileMenu fails.
     */
    public void start(Stage primaryStage) throws Exception {
        ResourceBundle bundle = ResourceBundle.getBundle("UIResources", new Locale("de"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample.fxml"), bundle);

        BorderPane borderPane = loader.load();
        primaryStage.setTitle("Syndromansatz");

        Scene scene = new Scene(borderPane);

        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image("/GraphItLogo.png"));
        primaryStage.show();
        primaryStage.setMaximized(true);

    }


    /**
     * Starts the application.
     *
     * @param args The java command line arguments that is needed to start the application.
     */
    public static void main(String[] args) {
        BasicConfigurator.configure();
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("NewPersistenceUnit");
        PersonalEntityManagerFactory.setEntityManagerFactory(entityManagerFactory);

        launch(args);

        List<Log> list = DatabaseManager.getInstance().getLogDao().getAll();

        for (Log l : list) {
            System.out.println(l.toString());
        }

        PersonalEntityManagerFactory.getInstance().close();
    }
}
