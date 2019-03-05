package gui;

import com.jfoenix.controls.JFXProgressBar;
import javafx.fxml.FXML;
import lombok.Getter;

/**
 * javadocTODO
 */
public class PreloaderController {

    @Getter
    @FXML
    private JFXProgressBar progressbar;

    /**
     * javadocTODO
     * @param progress
     */
    void setProgressbarProgress(double progress) {
        progressbar.setProgress(progress);
    }
}
