package gui;

import com.jfoenix.controls.JFXProgressBar;
import javafx.application.Preloader;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;


public class AppPreloader extends Preloader {

    @FXML JFXProgressBar progressBar;
    Stage stage;

    private Scene createPreloaderScene() throws IOException {
        BorderPane p = new FXMLLoader(getClass().getResource("/preloader.fxml")).load();
        return new Scene(p);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.initStyle(StageStyle.UNDECORATED);
        this.stage = stage;
        stage.setScene(createPreloaderScene());
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
        progressBar.setProgress(pn.getProgress());
        System.out.println("aProgress " + progressBar.getProgress());
    }

    @Override
    public void handleApplicationNotification(PreloaderNotification arg0) {
        if (arg0 instanceof ProgressNotification) {
            ProgressNotification pn= (ProgressNotification) arg0;
            progressBar.setProgress(pn.getProgress());
            System.out.println("bProgress " + progressBar.getProgress());
        }
    }
}
