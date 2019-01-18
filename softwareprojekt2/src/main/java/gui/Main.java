package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import log_management.dao.PersonalEntityManager;
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

    public static Font font_Roboto;

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
        //primaryStage.getIcons().add(new Image("/logo.png"));
        primaryStage.show();
        primaryStage.setMaximized(true);


        //Font initialization

        /*initFonts();


        Pane root = new Pane();
        TextArea t = new TextArea("Roboto");
        t.setFont(font_Roboto);
        root.getChildren().addAll(t);

        Scene scene = new Scene(root, 250, 220, Color.WHITESMOKE);
        primaryStage.setTitle("Absolute layout");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private static void initFonts(){
        try {
            font_Roboto = Font.loadFont("file:fonts/Roboto-Bold.ttf", 25);
            System.out.println(font_Roboto.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/}
    /**
     * Starts the application.
     *
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
