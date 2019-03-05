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
     * @param progress the current progress of the loading process
     */
    void setProgressbarProgress(double progress) {
        progressbar.setProgress(progress);
    }
}
