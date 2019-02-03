package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import log_management.dao.PersonalEntityManagerFactory;
import lombok.Getter;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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
        primaryStage.getIcons().add(new Image("/logo.png"));
        primaryStage.show();
        primaryStage.setMaximized(true);
        controller.createTemplateWindow();
    }

    /**
     * Starts the application.
     *
     * @param args The java command line arguments that is needed to start the application.
     */
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("NewPersistenceUnit");
        PersonalEntityManagerFactory.setEntityManagerFactory(entityManagerFactory);

        launch(args);

        PersonalEntityManagerFactory.getInstance().close();

//        Map<Integer, Integer> test = new HashMap<>();
//        test.put(1,1);
//        test.put(2,2);
//        test.put(1,3);
//        Object[] set = test.entrySet().toArray();
//        for (int i = 0; i < set.length; i++) {
//            System.out.println(((Map.Entry<Integer, Integer>)set[i]).getKey() + " " + ((Map.Entry<Integer, Integer>)set[i]).getValue());
//        }
    }
}
