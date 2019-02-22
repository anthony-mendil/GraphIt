package gui;

import gui.properties.Language;
import gui.properties.LoadLanguage;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Data
public class TitlesDialogPaneController extends DialogPane{

    @FXML private DialogPane titleDialog;
    @FXML private TextField german;
    @FXML private TextField english;
    @FXML private Button cancel;
    @FXML private Button save;
    @FXML private Text germanText;
    @FXML private Text englishText;

    public static final ButtonType SAVE_TYPE = new ButtonType("Speichern");
    public static final ButtonType CANCEL_TYPE = new ButtonType("Abbrechen");

    private ButtonType buttonType = ButtonType.CANCEL;
    private Values values = Values.getInstance();
    private LoadLanguage language = new LoadLanguage();

    public void initialize(){
        language.changeLanguage(values.getGuiLanguage());
        cancel = (Button) titleDialog.lookupButton(CANCEL_TYPE);
        cancel.setText(language.loadLanguagesKey("CANCEL_DIALOG"));
        save = (Button) titleDialog.lookupButton(SAVE_TYPE);
        save.setText(language.loadLanguagesKey("SAVE_DIALOG"));
        cancel.setOnMouseClicked(event -> close());

        EventHandler<KeyEvent> fireOnEnter = event -> {
            if (KeyCode.ENTER.equals(event.getCode()) && (event.getTarget() instanceof Button)) {
                ((Button) event.getTarget()).fire();
            }
        };

        titleDialog.getButtonTypes().add(ButtonType.CLOSE);
        Node closeButton = titleDialog.lookupButton(ButtonType.CLOSE);
        closeButton.managedProperty().bind(closeButton.visibleProperty());
        closeButton.setVisible(false);

        titleDialog.getButtonTypes().stream().map(titleDialog::lookupButton).forEach(button -> button.addEventHandler(KeyEvent.KEY_PRESSED, fireOnEnter));
    }

    public void setPrompt(Map<String, String> old){
        german.setText(old.get(Language.GERMAN.name()));
        english.setText(old.get(Language.ENGLISH.name()));
    }

    @FXML
    private void close() {
        cancel.getScene().getWindow().hide();
    }
}
