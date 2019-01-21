package gui;

import actions.ActionHistory;
import actions.edit.annotation.EditSphereAnnotationLogAction;
import actions.edit.color.EditSphereColorLogAction;
import actions.edit.font.EditFontSizeSphereLogAction;
import actions.edit.font.EditFontSphereLogAction;
import actions.remove.RemoveSphereLogAction;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.util.Optional;

@Data
public class SphereContextMenu {
    @Setter(AccessLevel.NONE)
    private final ContextMenu contextMenu;
    private final ActionHistory history;
    private final Values values;

    public SphereContextMenu(){
        contextMenu = new ContextMenu();
        history = ActionHistory.getInstance();
        values = Values.getInstance();
        setup();
    }



    private void setup(){
        // REMOVE
        MenuItem remove = new MenuItem("Entfernen");
        HelperGui.setImage("/icons2/008-rubbish-bin.png", remove);

        remove.setOnAction(event -> {
            RemoveSphereLogAction removeSphereLogAction = new RemoveSphereLogAction();
            history.execute(removeSphereLogAction);
        });

        // ANNOTATION
        MenuItem annotation = new MenuItem("Titel");
        HelperGui.setImage("/icons2/fancy.png", annotation);
        annotation.setOnAction(event -> {
            TextInputDialog dialog = new TextInputDialog("Sphäre Titel");

            dialog.setHeaderText("Titel Sphäre eingeben:");
            dialog.setContentText("Titel:");
            dialog.setTitle("Sphäre Titel");

            Optional<String> result = dialog.showAndWait();

            result.ifPresent(title -> {
                if (title.length() > 100){
                    title = title.substring(0, 99);
                }
                EditSphereAnnotationLogAction editSphereAnnotationLogAction = new EditSphereAnnotationLogAction(title);
                history.execute(editSphereAnnotationLogAction);
            });

        });

        // COLOR
        MenuItem color = new MenuItem("Farbe");
        HelperGui.setImage("/icons2/fill.png", color);
        color.setOnAction(event ->{

            EditSphereColorLogAction editSphereColorLogAction = new EditSphereColorLogAction(values.getFillPaintSphere());
            history.execute(editSphereColorLogAction);
        });

        // Schriftart
        MenuItem text = new MenuItem("Schriftart");
        HelperGui.setImage("/icons2/font.png", text);
        text.setOnAction(event ->{
            EditFontSphereLogAction editFontSphereLogAction = new EditFontSphereLogAction(values.getFontSphere());
            history.execute(editFontSphereLogAction);
        });

        // Schriftgröße
        MenuItem size = new MenuItem("Schriftgröße");
        HelperGui.setImage("/icons2/height.png", size);

        size.setOnAction(event ->{
            EditFontSizeSphereLogAction editFontSizeSphereLogAction = new EditFontSizeSphereLogAction(values.getFontSizeSphere());
            history.execute(editFontSizeSphereLogAction);
        });

        contextMenu.getItems().addAll(remove, annotation, color, text, size);
        contextMenu.setAutoHide(true);
    }
}
