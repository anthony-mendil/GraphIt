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
import java.awt.*;
import java.util.ArrayList;

/**
 * Starts the whole application.
 */
public class Main extends Application {

    /**
     * The window of the application.
     */
    @Getter
    private Stage primary;

    public static ArrayList<Font> fonts;

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
    }

   /* private static void initFonts(){

        fonts=new ArrayList<Font>();

        try {
            File file =new File("fonts/bold/Mali-Bold.ttf");
            Font mali_b =Font.createFont(Font.TRUETYPE_FONT, file);
            fonts.add(mali_b.deriveFont(Font.BOLD,20));
            Font Roboto_Regular = Font.loadFont("file:fonts/regular/Roboto-Regular.ttf",12);
            fonts.add(Roboto_Regular);
            Font Alegreya_Regular = Font.loadFont("file:fonts/regular/Alegreya-Regular.ttf",12);
            fonts.add(Alegreya_Regular);
            Font AveriaSansLibre_Regular = Font.loadFont("file:fonts/regular/AveriaSansLibre-Regular.ttf",12);
            fonts.add(AveriaSansLibre_Regular);
            Font Cousine_Regular = Font.loadFont("file:fonts/regular/Cousine-Regular.ttf",12);
            fonts.add(Cousine_Regular);
            Font Mali_Regular = Font.loadFont("file:fonts/regular/Mali-Regular.ttf",12);
            fonts.add(Mali_Regular);

            Font Roboto_Bold = Font.loadFont("file:fonts/bold/Roboto-Bold.ttf", 12);
            fonts.add(Roboto_Bold);
            Font Alegreya_Bold = Font.loadFont("file:fonts/bold/Alegreya-Bold.ttf", 12);
            fonts.add(Alegreya_Bold);
            Font AveriaSansLibre_Bold = Font.loadFont("file:fonts/bold/AveriaSansLibre_Bold.ttf", 12);
            fonts.add(AveriaSansLibre_Bold);
            Font Cousine_Bold = Font.loadFont("file:fonts/bold/Cousine-Bold.ttf", 12);
            fonts.add(Cousine_Bold);
            Font Mali_Bold = Font.loadFont("file:fonts/bold/Mali-Bold.ttf", 12);
            fonts.add(Mali_Bold);

            Font Roboto_Italic = Font.loadFont("file:fonts/italic/Roboto-Italic.ttf", 12);
            fonts.add(Roboto_Italic);
            Font Alegreya_Italic = Font.loadFont("file:fonts/italic/Alegreya-Italic.ttf", 12);
            fonts.add(Alegreya_Italic);
            Font AveriaSansLibre_Italic = Font.loadFont("file:fonts/italic/AveriaSansLibre-Italic.ttf", 12);
            fonts.add(AveriaSansLibre_Italic);
            Font Cousine_Italic = Font.loadFont("file:fonts/italic/Cousine-Italic.ttf", 12);
            fonts.add(Cousine_Italic);
            Font Mali_Italic = Font.loadFont("file:fonts/italic/Mali-Italic.ttf", 12);
            fonts.add(Mali_Italic);

            Font Roboto_BoldItalic = Font.loadFont("file:fonts/bolditalic/Roboto-BoldItalic.ttf", 12);
            fonts.add(Roboto_BoldItalic);
            Font Alegreya_BoldItalic = Font.loadFont("file:fonts/bolditalic/Alegreya-BoldItalic.ttf", 12);
            fonts.add(Alegreya_BoldItalic);
            Font AveriaSansLibre_BoldItalic = Font.loadFont("file:fonts/bolditalic/AveriaSansLibre-BoldItalic.ttf", 12);
            fonts.add(AveriaSansLibre_BoldItalic);
            Font Cousine_BoldItalic = Font.loadFont("file:fonts/bolditalic/Cousine-BoldItalic.ttf", 12);
            fonts.add(Cousine_BoldItalic);
            Font Mali_BoldItalic = Font.loadFont("file:fonts/bolditalic/Mali-BoldItalic.ttf", 12);
            fonts.add(Mali_BoldItalic);





        } catch (Exception e) {
            e.printStackTrace();
        }
        JFrame jFrame=new JFrame();
        JPanel jPanel=new JPanel();
        JLabel jLabel=new JLabel("abcABC123");
        jPanel.add(jLabel);
        jFrame.add(jPanel);
        jFrame.setBounds(200,200,200,200);
        jFrame.setVisible(true);
        jLabel.setFont(fonts.get(0));
    }*/

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

        //initFonts();

    }
}
