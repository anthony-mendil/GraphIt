package gui;

import com.jfoenix.controls.JFXProgressBar;
import javafx.fxml.FXML;
import lombok.Getter;

public class PreloaderController {

    @Getter
    @FXML
    private JFXProgressBar progressbar;

    public void setProgressbarProgress(double progress){
        progressbar.setProgress(progress);
    }
}
