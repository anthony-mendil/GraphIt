package gui;

import actions.ActionHistory;
import actions.edit.annotation.EditSphereAnnotationLogAction;
import actions.edit.color.EditSphereColorLogAction;
import actions.edit.font.EditFontSizeSphereLogAction;
import actions.edit.font.EditFontSphereLogAction;
import actions.remove.RemoveSphereLogAction;
import graph.graph.*;
import graph.visualization.control.HelperFunctions;
import gui.properties.Language;
import gui.properties.LoadLanguage;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Dialog;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.util.*;

@Data
public class SphereContextMenu {
    @Setter(AccessLevel.NONE)
    private final ContextMenu contextMenu;
    private final ActionHistory history;
    private final Values values;
    private final Sphere sphere;
    private LoadLanguage language = LoadLanguage.getInstance();
    private HelperFunctions helperFunctions = new HelperFunctions();
    private Syndrom syndrom = Syndrom.getInstance();

    public SphereContextMenu(Sphere sphere) {
        contextMenu = new ContextMenu();
        history = ActionHistory.getInstance();
        values = Values.getInstance();
        this.sphere = sphere;
        setup();
    }


    private void setup() {
        // REMOVE
        MenuItem remove = new MenuItem(language.loadLanguagesKey("CONTEXT_DIALOG_REMOVE"));
        HelperGui.setImage("/icons2/008-rubbish-bin.png", remove);

        remove.setOnAction(event -> {
            RemoveSphereLogAction removeSphereLogAction = new RemoveSphereLogAction();
            history.execute(removeSphereLogAction);
        });

        // ANNOTATION
        MenuItem annotation = new MenuItem(language.loadLanguagesKey("CONTEXT_DIALOG_TITLE"));
        HelperGui.setImage("/icons2/fancy.png", annotation);
        annotation.setOnAction(event -> {
            Dialog<EnumMap<Language, String>> dialog = helperFunctions.getDialog(sphere.getAnnotation());

            dialog.setHeaderText(language.loadLanguagesKey("CONTEXT_DIALOG_HEADER"));
            dialog.setContentText(language.loadLanguagesKey("CONTEXT_DIALOG_CONTENT"));
            dialog.setTitle(language.loadLanguagesKey("CONTEXT_DIALOG_SPHERE_TITLE"));

            Optional<EnumMap<Language, String>> result = dialog.showAndWait();
            Map<String, String> oldAnno = sphere.getAnnotation();

            result.ifPresent(map -> {
                checkAnnotation(map, oldAnno, Language.GERMAN);
                checkAnnotation(map, oldAnno, Language.ENGLISH);
            });
        });

        // COLOR
        MenuItem color = new MenuItem(language.loadLanguagesKey("CONTEXT_DIALOG_COLOR"));
        HelperGui.setImage("/icons2/fill.png", color);
        color.setOnAction(event -> {

            EditSphereColorLogAction editSphereColorLogAction = new EditSphereColorLogAction(values.getFillPaintSphere());
            history.execute(editSphereColorLogAction);
        });

        // Schriftart
        MenuItem text = new MenuItem(language.loadLanguagesKey("CONTEXT_DIALOG_FONT"));
        HelperGui.setImage("/icons2/font.png", text);
        text.setOnAction(event -> {
            EditFontSphereLogAction editFontSphereLogAction = new EditFontSphereLogAction(values.getFontSphere());
            history.execute(editFontSphereLogAction);
        });

        // Schriftgröße
        MenuItem size = new MenuItem(language.loadLanguagesKey("CONTEXT_DIALOG_FONT_SIZE"));
        HelperGui.setImage("/icons2/height.png", size);

        size.setOnAction(event -> {
            EditFontSizeSphereLogAction editFontSizeSphereLogAction = new EditFontSizeSphereLogAction(values.getFontSizeSphere());
            history.execute(editFontSizeSphereLogAction);
        });

        boolean lockedAnnotation = sphere.isLockedAnnotation();
        boolean lockedStyle = sphere.isLockedStyle();
        if (!lockedAnnotation || values.getMode() == FunctionMode.TEMPLATE) {
            contextMenu.getItems().addAll(annotation, text);
        }
        if (!lockedStyle || values.getMode() == FunctionMode.TEMPLATE) {
            contextMenu.getItems().addAll(color, size);
        }
        if (!lockedStyle && !lockedAnnotation || values.getMode() == FunctionMode.TEMPLATE) {
            contextMenu.getItems().add(remove);
        }

        contextMenu.setAutoHide(true);
        contextMenu.setHideOnEscape(true);

        contextMenu.addEventHandler(MouseEvent.MOUSE_RELEASED, e -> {
            contextMenu.hide();
        });
    }

    private void checkAnnotation(Map<Language, String> map, Map<String, String> oldAnno, Language lang) {
        if (map != null && map.containsKey(lang)) {
            String text = map.get(lang);
            if (!oldAnno.get(lang.name()).equals(text) && text.length() > 0) {
                EditSphereAnnotationLogAction editSphereAnnotationLogAction = new EditSphereAnnotationLogAction(map.get(lang), lang);
                history.execute(editSphereAnnotationLogAction);
            }
        }
    }


}
