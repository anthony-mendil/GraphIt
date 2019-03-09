package gui;

import com.jfoenix.controls.JFXProgressBar;
import javafx.fxml.FXML;
import lombok.Getter;

/**
 * The preloader controller to control the progressbar.
 */
public class PreloaderController {

    /**
     * The Progressbar that show the progress in the initializing process of the application.
     */
    @Getter
    @FXML
    private JFXProgressBar progressbar;

    /**
     * Sets the progress of the progressbar.
     *
     * @param progress the current progress of the loading process
     */
    void setProgressbarProgress(double progress) {
        progressbar.setProgress(progress);
    }
}
