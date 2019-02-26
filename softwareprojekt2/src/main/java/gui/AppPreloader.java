package gui;

import com.jfoenix.controls.JFXProgressBar;
import javafx.application.Preloader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class AppPreloader extends Preloader {

    JFXProgressBar bar;
    Stage stage;
    BorderPane borderPane;

    @Override
    public void init() throws Exception {
        bar = new JFXProgressBar(0);
        borderPane = new BorderPane();
        borderPane.setPrefSize(500, 350);
        super.init();
    }

    public void start(Stage stage) throws Exception {
        stage.initStyle(StageStyle.UNDECORATED);
        this.stage = stage;
        Scene sz = new Scene(borderPane);
        stage.setScene(sz);
        stage.setAlwaysOnTop(true);
        stage.show();
    }

    @Override
    public void handleProgressNotification(ProgressNotification pn) {
        bar.setProgress(pn.getProgress());
    }

    @Override
    public void handleStateChangeNotification(StateChangeNotification evt) {
        if (evt.getType() == StateChangeNotification.Type.BEFORE_START) {
            stage.hide();
            stage.close();
        }
    }

}
