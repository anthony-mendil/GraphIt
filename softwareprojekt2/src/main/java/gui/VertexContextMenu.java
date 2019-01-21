package gui;

import actions.ActionHistory;
import actions.edit.annotation.EditSphereAnnotationLogAction;
import actions.edit.annotation.EditVertexAnnotationLogAction;
import actions.edit.color.EditSphereColorLogAction;
import actions.edit.color.EditVerticesDrawColorLogAction;
import actions.edit.color.EditVerticesFillColorLogAction;
import actions.edit.font.EditFontSizeSphereLogAction;
import actions.edit.font.EditFontSizeVerticesLogAction;
import actions.edit.font.EditFontSphereLogAction;
import actions.edit.font.EditFontVerticesLogAction;
import actions.remove.RemoveSphereLogAction;
import actions.remove.RemoveVerticesLogAction;
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
public class VertexContextMenu {
    @Setter(AccessLevel.NONE)
    private final ContextMenu contextMenu;
    private final ActionHistory history;
    private final Values values;


    public VertexContextMenu(){
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
            RemoveVerticesLogAction removeVerticesLogAction = new RemoveVerticesLogAction();
            history.execute(removeVerticesLogAction);
        });

        // ANNOTATION
        MenuItem annotation = new MenuItem("Titel");
        HelperGui.setImage("/icons2/fancy.png", annotation);
        annotation.setOnAction(event -> {
            TextInputDialog dialog = new TextInputDialog("Symptom Titel");

            dialog.setHeaderText("Titel Symptom eingeben:");
            dialog.setContentText("Titel:");
            dialog.setTitle("Symptom Titel");

            Optional<String> result = dialog.showAndWait();

            result.ifPresent(title -> {
                if (title.length() > 100){
                    title = title.substring(0, 99);
                }
                EditVertexAnnotationLogAction editVertexAnnotationLogAction = new EditVertexAnnotationLogAction(title);
                history.execute(editVertexAnnotationLogAction);
            });

        });

        // COLOR FILL
        MenuItem color = new MenuItem("Füllfarbe");
        HelperGui.setImage("/icons2/fill.png", color);
        color.setOnAction(event ->{

            EditVerticesFillColorLogAction editVerticesFillColorLogAction = new EditVerticesFillColorLogAction(values.getFillPaintVertex());
            history.execute(editVerticesFillColorLogAction);
        });

        // COLOR STROKE
        MenuItem colorDraw = new MenuItem("Randfarbe");
        HelperGui.setImage("/icons2/brush.png", colorDraw);
        colorDraw.setOnAction(event ->{
            EditVerticesDrawColorLogAction editVerticesDrawColorLogAction = new EditVerticesDrawColorLogAction(values.getDrawPaintVertex());
            history.execute(editVerticesDrawColorLogAction);
        });

        // Schriftart
        MenuItem text = new MenuItem("Schriftart");
        HelperGui.setImage("/icons2/font.png", text);
        text.setOnAction(event ->{
            EditFontVerticesLogAction editFontVerticesLogAction = new EditFontVerticesLogAction(values.getFontVertex());
            history.execute(editFontVerticesLogAction);
        });

        // Schriftgröße
        MenuItem size = new MenuItem("Schriftgröße");
        HelperGui.setImage("/icons2/height.png", size);

        size.setOnAction(event ->{
            EditFontSizeVerticesLogAction editFontSizeVerticesLogAction = new EditFontSizeVerticesLogAction(values.getFontSizeVertex());
            history.execute(editFontSizeVerticesLogAction);
        });

        contextMenu.getItems().addAll(remove, annotation, color, colorDraw, text, size);
        contextMenu.setAutoHide(true);
    }
}
