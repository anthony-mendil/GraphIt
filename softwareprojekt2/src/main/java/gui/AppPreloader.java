package gui;

import com.jfoenix.controls.JFXProgressBar;
import javafx.application.Preloader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;


public class AppPreloader extends Preloader {

    JFXProgressBar bar;
    Stage stage;

    private Scene createPreloaderScene() throws IOException {
        bar = new JFXProgressBar(0);
        bar.getProgress();
        bar.setPrefHeight(10);
        BorderPane p = new FXMLLoader(getClass().getResource("/preloader.fxml")).load();
        p.setCenter(bar);
        Scene sz = new Scene(p);
        return sz;
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
        bar.setProgress(pn.getProgress());
        System.out.println("aProgress " + bar.getProgress());
    }

    @Override
    public void handleApplicationNotification(PreloaderNotification arg0) {
        if (arg0 instanceof ProgressNotification) {
            ProgressNotification pn= (ProgressNotification) arg0;
            bar.setProgress(pn.getProgress());
            System.out.println("bProgress " + bar.getProgress());
        }
    }
}
