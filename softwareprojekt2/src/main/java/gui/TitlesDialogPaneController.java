package gui;

import graph.graph.*;
import graph.visualization.control.ErrorMessagesTransition;
import gui.properties.Language;
import gui.properties.LoadLanguage;
import javafx.animation.Animation;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
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
    @FXML private HBox textBox;
    @FXML private Text currentActionText;

    public static final ButtonType SAVE_TYPE = new ButtonType("Speichern");
    public static final ButtonType CANCEL_TYPE = new ButtonType("Abbrechen");

    private ButtonType buttonType = ButtonType.CANCEL;
    private Values values = Values.getInstance();
    private LoadLanguage language = new LoadLanguage();
    private Syndrom syndrom = Syndrom.getInstance();
    private Map<String, String> oldTitle;
    private boolean isNotSame = false;

    public void initialize(){
        language.changeLanguage(values.getGuiLanguage());
        cancel = (Button) titleDialog.lookupButton(CANCEL_TYPE);
        cancel.setText(language.loadLanguagesKey("CANCEL_DIALOG"));
        save = (Button) titleDialog.lookupButton(SAVE_TYPE);
        save.setText(language.loadLanguagesKey("SAVE_DIALOG"));
        cancel.setOnMouseClicked(event -> close());

        german.setOnMouseExited(event -> {
            if (!avoidSameAnnotationTwice(german.getText()) && !avoidSameAnnotationTwice(english.getText())){
                save.setDisable(false);
            } else {
                save.setDisable(true);
            }

        });

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
        oldTitle = old;
    }

    private boolean avoidSameAnnotationTwice(String com){
        SyndromGraph<Vertex, Edge> graph = ( SyndromGraph<Vertex, Edge>) syndrom.getVv().getGraphLayout().getGraph();
        for (Sphere s : graph.getSpheres()){
            for (Map.Entry<String, String> entry : s.getAnnotation().entrySet()){
                if (entry.getValue().equals(com) && !entry.getValue().equals(oldTitle.get(entry.getKey()))){
                    alertSameText();
                    return true;
                }
            }
        }

        for (Vertex v : graph.getVertices()){
            for (Map.Entry<String, String> entry : v.getAnnotation().entrySet()){
                if (entry.getValue().equals(com) && !entry.getValue().equals(oldTitle.get(entry.getKey()))){
                    alertSameText();
                    return true;
                }
            }
        }
        return false;
    }

    private void alertSameText(){
            currentActionText.setText(language.loadLanguagesKey("alertSameTitle"));
            currentActionText.setFill(javafx.scene.paint.Color.WHITE);
            currentActionText.setFont(Font.font("System Regular", FontWeight
                    .EXTRA_BOLD, 12));
            String style = "-fx-background-color: rgba(160, 12, 12, 1);";
            textBox.setStyle(style);
            final Animation animation = new ErrorMessagesTransition(textBox);
            animation.play();
    }

    @FXML
    private void close() {
        cancel.getScene().getWindow().hide();
    }
}
