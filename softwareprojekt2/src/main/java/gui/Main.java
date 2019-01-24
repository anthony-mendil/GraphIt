package gui;

import actions.activate.ActivateAnchorPointsFadeoutLogAction;
import graph.graph.Edge;
import graph.graph.EdgeArrowType;
import graph.graph.StrokeType;
import gui.properties.Language;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import log_management.DatabaseManager;
import log_management.LogToStringConverter;
import log_management.dao.PersonalEntityManager;
import log_management.parameters.activate_deactivate.ActivateDeactivateAnchorPointsFadeoutParam;
import log_management.tables.Log;
import lombok.Getter;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

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
        EntityManager initialEntityManager = entityManagerFactory.createEntityManager();
        PersonalEntityManager.setEntityManager(initialEntityManager);

        launch(args);

        EntityManager entityManager = PersonalEntityManager.getInstance();
        entityManager.getEntityManagerFactory().close();
        entityManager.close();

//        Values.getInstance().setGuiLanguage(Language.ENGLISH);
//        List<Edge> list = new ArrayList<>();
//        list.add(new Edge(1, new Color(45, 45, 200), StrokeType.BASIC_WEIGHT, EdgeArrowType.EXTENUATING, true, true));
//        list.add(new Edge(2, new Color(45, 45, 200), StrokeType.BASIC_WEIGHT, EdgeArrowType.EXTENUATING, true, true));
//        list.add(new Edge(3, new Color(45, 45, 200), StrokeType.BASIC_WEIGHT, EdgeArrowType.EXTENUATING, true, true));
//        ActivateDeactivateAnchorPointsFadeoutParam test = new ActivateDeactivateAnchorPointsFadeoutParam(list);
//        System.out.println(test);
    }
}
