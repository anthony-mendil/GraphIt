package gui;

import graph.graph.*;
import graph.visualization.control.ErrorMessagesTransition;
import gui.properties.Language;
import gui.properties.LoadLanguage;
import javafx.animation.Animation;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

/**
 * The titlesdialogpane controller to control the title dialog for changing titles for spheres and vertices.
 *
 * @author Jacky Philipp Mach
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TitlesDialogPaneController extends DialogPane {

    /**
     * The button type of the save button.
     */
    public static final ButtonType SAVE_TYPE = new ButtonType("Speichern");
    /**
     * The button type of the cancel button.
     */
    public static final ButtonType CANCEL_TYPE = new ButtonType("Abbrechen");
    /**
     * The DialogPane containing gui nodes.
     */
    @FXML
    private DialogPane titleDialog;
    /**
     * The TextField for the german input.
     */
    @FXML
    private TextField german;
    /**
     * The TextField for the english input.
     */
    @FXML
    private TextField english;
    /**
     * The Button cancelling the dialog.
     */
    @FXML
    private Button cancel;
    /**
     * The Button saving the input.
     */
    @FXML
    private Button save;
    /**
     * The german description.
     */
    @FXML
    private Text germanText;
    /**
     * The english description.
     */
    @FXML
    private Text englishText;
    /**
     * The HBox for error messages.
     */
    @FXML
    private HBox textBox;
    /**
     * The Text of error messages.
     */
    @FXML
    private Text currentActionText;

    /**
     * The values instance.
     */
    private Values values = Values.getInstance();
    /**
     * The load language instance.
     */
    private LoadLanguage language = LoadLanguage.getInstance();
    /**
     * The syndrom instance.
     */
    private Syndrom syndrom = Syndrom.getInstance();
    /**
     * The old title of the object.
     */
    private Map<String, String> oldTitle;

    /**
     * Initializes the TitlesDialog gui and sets multiple buttons and handlers.
     */
    public void initialize() {
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

        german.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                save.fire();
            }
        });

        english.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                save.fire();
            }
        });

        titleDialog.getButtonTypes().add(ButtonType.CLOSE);
        Node closeButton = titleDialog.lookupButton(ButtonType.CLOSE);
        closeButton.managedProperty().bind(closeButton.visibleProperty());
        closeButton.setVisible(false);
        titleDialog.getButtonTypes().stream().map(titleDialog::lookupButton).forEach(button -> button.addEventHandler(KeyEvent.KEY_PRESSED, fireOnEnter));
    }

    /**
     * Sets the prompt texts to the titles.
     *
     * @param old the old titles
     */
    public void setPrompt(Map<String, String> old) {
        german.setText(old.get(Language.GERMAN.name()));
        english.setText(old.get(Language.ENGLISH.name()));
        oldTitle = old;
        german.textProperty().addListener(event -> save.setDisable(setDisable()));
        english.textProperty().addListener(event -> save.setDisable(setDisable()));
    }

    /**
     * Detects if the save button should be disabled.
     *
     * @return true if yes, false if no
     */
    private boolean setDisable() {
        return avoidSameAnnotationTwice(german.getText().trim()) || avoidSameAnnotationTwice(english.getText().trim());
    }

    /**
     * Checks if the new title already exists.
     *
     * @param com the string to check
     * @return true if there are double titles
     */
    private boolean avoidSameAnnotationTwice(String com) {
        SyndromGraph<Vertex, Edge> graph = (SyndromGraph<Vertex, Edge>) syndrom.getVv().getGraphLayout().getGraph();
        for (Sphere s : graph.getSpheres()) {
            for (Map.Entry<String, String> entry : s.getAnnotation().entrySet()) {
                if (entry.getValue().equals(com) && !entry.getValue().equals(oldTitle.get(entry.getKey()))) {
                    alertSameText();
                    return true;
                }
            }
        }

        for (Vertex v : graph.getVertices()) {
            for (Map.Entry<String, String> entry : v.getAnnotation().entrySet()) {
                if (entry.getValue().equals(com) && !entry.getValue().equals(oldTitle.get(entry.getKey()))) {
                    alertSameText();
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Creates the alert, when to titles are the same.
     */
    private void alertSameText() {
        currentActionText.setText(language.loadLanguagesKey("alertSameTitle"));
        currentActionText.setFill(javafx.scene.paint.Color.WHITE);
        currentActionText.setFont(Font.font("System Regular", FontWeight
                .EXTRA_BOLD, 12));
        String style = "-fx-background-color: rgba(160, 12, 12, 1);";
        textBox.setStyle(style);
        final Animation animation = new ErrorMessagesTransition(textBox);
        animation.play();
    }

    /**
     * Closes the title dialog pane.
     */
    @FXML
    private void close() {
        cancel.getScene().getWindow().hide();
    }
}
