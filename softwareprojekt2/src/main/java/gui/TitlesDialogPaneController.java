package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import lombok.Data;

@Data
public class TitlesDialogPaneController {

    @FXML private DialogPane titleDialog;
    @FXML private TextField german;
    @FXML private TextField english;
    @FXML private ButtonType buttonType = ButtonType.OK;
    @FXML private Button cancel;
    @FXML private Button save;

    public void initialize(){

    }

    @FXML
    private void close() {
        cancel.getScene().getWindow().hide();
    }

    @FXML private void save(){

    }
}
