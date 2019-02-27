package gui;

import javafx.application.Preloader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class AppPreloader extends Preloader {

    private Stage stage;
    private PreloaderController pc;

    @Override
    public void start(Stage pStage) throws Exception {
        stage = pStage;
        stage.initStyle(StageStyle.UNDECORATED);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/preloader.fxml"));

        BorderPane p = fxmlLoader.load();
        pc = fxmlLoader.getController();
        Scene sz =new Scene(p);
        sz.getStylesheets().add("/gui_style.css");
        stage.getIcons().add(new Image("/GraphItLogo.png"));
        stage.setScene(sz);
        stage.show();
    }

    @Override
    public void handleStateChangeNotification(StateChangeNotification scn) {
        if (scn.getType() == StateChangeNotification.Type.BEFORE_START) {
            stage.hide();
        }
    }

    @Override
    public void handleProgressNotification(ProgressNotification pn) {
        pc.setProgressbarProgress(pn.getProgress());
        System.out.println("aProgress " + pc.getProgressbar().getProgress());
    }

    @Override
    public void handleApplicationNotification(PreloaderNotification pln) {
        if (pln instanceof ProgressNotification) {
            ProgressNotification pn = (ProgressNotification) pln;
            pc.setProgressbarProgress(pn.getProgress());
            System.out.println("bProgress " + pc.getProgressbar().getProgress());
        }
    }
}
