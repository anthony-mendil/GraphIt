package gui;

import com.jfoenix.controls.JFXProgressBar;
import javafx.fxml.FXML;
import lombok.Getter;

/**
 * TODO
 */
public class PreloaderController {

    @Getter
    @FXML
    private JFXProgressBar progressbar;

    /**
     * TODO
     * @param progress
     */
    void setProgressbarProgress(double progress) {
        progressbar.setProgress(progress);
    }
}
