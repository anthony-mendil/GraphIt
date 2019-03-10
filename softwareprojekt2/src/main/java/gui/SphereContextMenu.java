package gui;

import actions.ActionHistory;
import actions.edit.annotation.EditSphereAnnotationLogAction;
import actions.edit.color.EditSphereColorLogAction;
import actions.edit.font.EditFontSizeSphereLogAction;
import actions.edit.font.EditFontSphereLogAction;
import actions.remove.RemoveSphereLogAction;
import graph.graph.FunctionMode;
import graph.graph.Sphere;
import graph.graph.Syndrom;
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

import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;

/**
 * Creates the context-menu, if the user right-clicks a sphere.
 *
 * @author Jacky Philipp Mach
 */
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

    /**
     * The sphere context menu.
     *
     * @param sphere The sphere to create the context menu to.
     */
    public SphereContextMenu(Sphere sphere) {
        contextMenu = new ContextMenu();
        history = ActionHistory.getInstance();
        values = Values.getInstance();
        this.sphere = sphere;
        setup();
    }

    /**
     * Creates the sphere context menu.
     */
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

            result.ifPresent(this::checkAnnotation);
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
        contextMenu.addEventHandler(MouseEvent.MOUSE_RELEASED, e -> contextMenu.hide());
    }

    /**
     * Checks the annotation and creates a new edit sphere annotation action.
     *
     * @param map the map, containing the annotations
     */
    private void checkAnnotation(Map<Language, String> map) {
        if (map != null) {
            String textGerman = map.get(Language.GERMAN);
            String textEnglish = map.get(Language.ENGLISH);
            if (textGerman.length() > 0 && textEnglish.length() > 0) {
                EditSphereAnnotationLogAction editSphereAnnotationLogAction = new EditSphereAnnotationLogAction(map);
                history.execute(editSphereAnnotationLogAction);
            }
        }
    }
}
