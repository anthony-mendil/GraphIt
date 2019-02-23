package gui;

import actions.ActionHistory;
import actions.edit.annotation.EditSphereAnnotationLogAction;
import actions.edit.annotation.EditVertexAnnotationLogAction;
import actions.edit.color.EditVerticesDrawColorLogAction;
import actions.edit.color.EditVerticesFillColorLogAction;
import actions.edit.font.EditFontSizeVerticesLogAction;
import actions.edit.font.EditFontVerticesLogAction;
import actions.remove.RemoveVerticesLogAction;
import graph.graph.*;
import graph.visualization.control.HelperFunctions;
import gui.properties.Language;
import gui.properties.LoadLanguage;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Dialog;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;

@Data
public class VertexContextMenu {
    @Setter(AccessLevel.NONE)
    private final ContextMenu contextMenu;
    private final ActionHistory history;
    private final Values values;
    private final Vertex vertex;
    private HelperFunctions helperFunctions = new HelperFunctions();
    private LoadLanguage language = new LoadLanguage();
    private Syndrom syndrom = Syndrom.getInstance();


    public VertexContextMenu(Vertex vertex){
        contextMenu = new ContextMenu();
        history = ActionHistory.getInstance();
        values = Values.getInstance();
        this.vertex = vertex;
        language.changeLanguage(values.getGuiLanguage());
        setup();
    }

    private void setup(){
        // REMOVE
        MenuItem remove = new MenuItem(language.loadLanguagesKey("CONTEXT_DIALOG_REMOVE"));
        HelperGui.setImage("/icons2/008-rubbish-bin.png", remove);

        remove.setOnAction(event -> {
            RemoveVerticesLogAction removeVerticesLogAction = new RemoveVerticesLogAction();
            history.execute(removeVerticesLogAction);
        });

        // ANNOTATION
        MenuItem annotation = new MenuItem(language.loadLanguagesKey("CONTEXT_DIALOG_TITLE"));
        HelperGui.setImage("/icons2/fancy.png", annotation);
        annotation.setOnAction(event -> {
            Dialog<EnumMap<Language, String>> dialog = helperFunctions.getDialog(vertex.getAnnotation());
            dialog.setHeaderText(language.loadLanguagesKey("CONTEXT_DIALOG_HEADER_VERTEX"));
            dialog.setContentText(language.loadLanguagesKey("CONTEXT_DIALOG_CONTENT"));
            dialog.setTitle(language.loadLanguagesKey("CONTEXT_DIALOG_VERTEX_TITLE"));

            Optional<EnumMap<Language, String>> result = dialog.showAndWait();
            Map<String, String> oldAnno = vertex.getAnnotation();

            result.ifPresent(map -> {
                checkAnnotation(map, oldAnno, Language.GERMAN);
                checkAnnotation(map, oldAnno, Language.ENGLISH);
            });
        });

        // COLOR FILL
        MenuItem color = new MenuItem(language.loadLanguagesKey("CONTEXT_DIALOG_FILL_COLOR"));
        HelperGui.setImage("/icons2/fill.png", color);
        color.setOnAction(event ->{

            EditVerticesFillColorLogAction editVerticesFillColorLogAction = new EditVerticesFillColorLogAction(values.getFillPaintVertex());
            history.execute(editVerticesFillColorLogAction);
        });

        // COLOR STROKE
        MenuItem colorDraw = new MenuItem(language.loadLanguagesKey("CONTEXT_DIALOG_STROKE_COLOR"));
        HelperGui.setImage("/icons2/brush.png", colorDraw);
        colorDraw.setOnAction(event ->{
            EditVerticesDrawColorLogAction editVerticesDrawColorLogAction = new EditVerticesDrawColorLogAction(values.getDrawPaintVertex());
            history.execute(editVerticesDrawColorLogAction);
        });

        // Schriftart
        MenuItem text = new MenuItem(language.loadLanguagesKey("CONTEXT_DIALOG_FONT"));
        HelperGui.setImage("/icons2/font.png", text);
        text.setOnAction(event ->{
            EditFontVerticesLogAction editFontVerticesLogAction = new EditFontVerticesLogAction(values.getFontVertex());
            history.execute(editFontVerticesLogAction);
        });

        // Schriftgröße
        MenuItem size = new MenuItem(language.loadLanguagesKey("CONTEXT_DIALOG_FONT_SIZE"));
        HelperGui.setImage("/icons2/height.png", size);

        size.setOnAction(event ->{
            EditFontSizeVerticesLogAction editFontSizeVerticesLogAction = new EditFontSizeVerticesLogAction(values.getFontSizeVertex());
            history.execute(editFontSizeVerticesLogAction);
        });

        boolean lockedAnnotation = vertex.isLockedAnnotation();
        boolean lockedStyle = vertex.isLockedStyle();
        if (!lockedAnnotation || values.getMode() == FunctionMode.TEMPLATE){
            contextMenu.getItems().addAll(annotation, text);
        }
        if (!lockedStyle || values.getMode() == FunctionMode.TEMPLATE){
            contextMenu.getItems().addAll(color, colorDraw, size);
        }
        if (!lockedStyle && !lockedAnnotation || values.getMode() == FunctionMode.TEMPLATE){
            contextMenu.getItems().add(remove);
        }
        contextMenu.setAutoHide(true);
        contextMenu.addEventHandler(MouseEvent.MOUSE_RELEASED, e -> {
            contextMenu.hide();
        });
    }

    private void checkAnnotation(Map<Language, String> map, Map<String, String> oldAnno, Language lang){
        if (map != null && map.containsKey(lang)) {
            String text = map.get(lang);
            if (!oldAnno.get(lang.name()).equals(text) && text.length() > 0){
                EditVertexAnnotationLogAction editVertexAnnotationLogAction = new EditVertexAnnotationLogAction(map.get(lang), lang);
                history.execute(editVertexAnnotationLogAction);
            }
        }
    }
}
