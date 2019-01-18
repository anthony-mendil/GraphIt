package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import log_management.dao.PersonalEntityManager;
import lombok.Getter;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static javafx.application.Application.launch;

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
        controller.setStage(primaryStage);
        loader.setController(controller);
        Values.getInstance().setNamespace(loader.getNamespace());
        BorderPane borderPane = loader.load();
        primaryStage.setTitle("Syndromansatz");
        primaryStage.setScene(new Scene(borderPane));
        primaryStage.setMaximized(true);
        //primaryStage.getIcons().add(new Image("/logo.png"));
        primaryStage.show();
    }

    /**
     * Starts the application.
     * @param args The java command line arguments that is needed to start the application.
     */
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("NewPersistenceUnit");
        EntityManager initialEntityManager = entityManagerFactory.createEntityManager();
        PersonalEntityManager.setEntityManager(initialEntityManager);

        launch(args);

        EntityManager entityManager = PersonalEntityManager.getInstance();
        entityManager.getEntityManagerFactory().close();
        entityManager.close();
    }
}
