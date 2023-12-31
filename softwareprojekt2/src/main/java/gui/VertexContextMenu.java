package gui;

import actions.ActionHistory;
import actions.add.AddFadeoutElementAction;
import actions.add.AddHighlightElementAction;
import actions.edit.annotation.EditVertexAnnotationLogAction;
import actions.edit.color.EditVerticesDrawColorLogAction;
import actions.edit.color.EditVerticesFillColorLogAction;
import actions.edit.font.EditFontSizeVerticesLogAction;
import actions.edit.font.EditFontVerticesLogAction;
import actions.remove.RemoveFadeoutElementAction;
import actions.remove.RemoveHighlightElementAction;
import actions.remove.RemoveVerticesLogAction;
import graph.graph.FunctionMode;
import graph.graph.Syndrom;
import graph.graph.Vertex;
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
 * Context menu, in case the user right-clicks on vertices.
 *
 * @author Jacky Philipp Mach
 */
@Data
public class VertexContextMenu {
    /**
     * The context menu.
     */
    @Setter(AccessLevel.NONE)
    private final ContextMenu contextMenu;
    /**
     * An instance of action history.
     */
    private final ActionHistory history;
    /**
     * An instance of values.
     */
    private final Values values;
    /**
     * The vertex the context menu belongs to.
     */
    private final Vertex vertex;
    /**
     * An instance of helper function.
     */
    private HelperFunctions helperFunctions = new HelperFunctions();
    /**
     * An instance of load language.
     */
    private LoadLanguage language = LoadLanguage.getInstance();
    /**
     * A syndrom instance.
     */
    private Syndrom syndrom = Syndrom.getInstance();

    /**
     * Creates a vertex context menu.
     *
     * @param vertex the vertex
     */
    public VertexContextMenu(Vertex vertex) {
        contextMenu = new ContextMenu();
        history = ActionHistory.getInstance();
        values = Values.getInstance();
        this.vertex = vertex;
        setup();
    }

    /**
     * Creates the context menu.
     */
    private void setup() {
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

            result.ifPresent(this::checkAnnotation);
        });

        // COLOR FILL
        MenuItem color = new MenuItem(language.loadLanguagesKey("CONTEXT_DIALOG_FILL_COLOR"));
        HelperGui.setImage("/icons2/fill.png", color);
        color.setOnAction(event -> {

            EditVerticesFillColorLogAction editVerticesFillColorLogAction = new EditVerticesFillColorLogAction(values.getFillPaintVertex());
            history.execute(editVerticesFillColorLogAction);
        });

        // COLOR STROKE
        MenuItem colorDraw = new MenuItem(language.loadLanguagesKey("CONTEXT_DIALOG_STROKE_COLOR"));
        HelperGui.setImage("/icons2/brush.png", colorDraw);
        colorDraw.setOnAction(event -> {
            EditVerticesDrawColorLogAction editVerticesDrawColorLogAction = new EditVerticesDrawColorLogAction(values.getDrawPaintVertex());
            history.execute(editVerticesDrawColorLogAction);
        });

        // Schriftart
        MenuItem text = new MenuItem(language.loadLanguagesKey("CONTEXT_DIALOG_FONT"));
        HelperGui.setImage("/icons2/font.png", text);
        text.setOnAction(event -> {
            EditFontVerticesLogAction editFontVerticesLogAction = new EditFontVerticesLogAction(values.getFontVertex());
            history.execute(editFontVerticesLogAction);
        });

        // Schriftgröße
        MenuItem size = new MenuItem(language.loadLanguagesKey("CONTEXT_DIALOG_FONT_SIZE"));
        HelperGui.setImage("/icons2/height.png", size);

        size.setOnAction(event -> {
            EditFontSizeVerticesLogAction editFontSizeVerticesLogAction = new EditFontSizeVerticesLogAction(values.getFontSizeVertex());
            history.execute(editFontSizeVerticesLogAction);
        });

        // Highlight
        MenuItem highlight = new MenuItem(language.loadLanguagesKey("CONTEXT_DIALOG_Highlight"));
        HelperGui.setImage("/icons2/highlight.png", highlight);

        if (vertex.isHighlighted()) {
            highlight.setStyle("-fx-text-fill: #395cab;");
            highlight.setOnAction(event -> {
                RemoveHighlightElementAction removeHighlightElementAction = new RemoveHighlightElementAction();
                history.execute(removeHighlightElementAction);
            });

        } else {
            highlight.setOnAction(event -> {
                AddHighlightElementAction addHighlightElementAction = new AddHighlightElementAction();
                history.execute(addHighlightElementAction);
            });
        }

        // isVisible
        MenuItem visible = new MenuItem(language.loadLanguagesKey("CONTEXT_DIALOG_VISIBLE"));
        HelperGui.setImage("/icons2/005-hide-2.png", visible);

        if (!vertex.isVisible()) {
            visible.setStyle("-fx-text-fill: #395cab;");
            visible.setOnAction(event -> {
                RemoveFadeoutElementAction removeFadeoutElementAction = new RemoveFadeoutElementAction();
                history.execute(removeFadeoutElementAction);
            });

        } else {
            visible.setOnAction(event -> {
                AddFadeoutElementAction addFadeoutElementAction = new AddFadeoutElementAction();
                history.execute(addFadeoutElementAction);
            });
        }
        boolean lockedAnnotation = vertex.isLockedAnnotation();
        boolean lockedStyle = vertex.isLockedStyle();
        if (!lockedAnnotation || values.getMode() == FunctionMode.TEMPLATE) {
            contextMenu.getItems().addAll(annotation, text);
        }
        if (!lockedStyle || values.getMode() == FunctionMode.TEMPLATE) {
            contextMenu.getItems().addAll(color, colorDraw, size);
        }
        if (!lockedStyle && !lockedAnnotation || values.getMode() == FunctionMode.TEMPLATE) {
            contextMenu.getItems().add(remove);
        }
        contextMenu.getItems().addAll(highlight, visible);
        contextMenu.setAutoHide(true);
        contextMenu.addEventHandler(MouseEvent.MOUSE_RELEASED, e -> contextMenu.hide());
    }

    /**
     * Checks the annotation and creates a edit vertex annotation action.
     *
     * @param map the map containing the annotations
     */
    private void checkAnnotation(Map<Language, String> map) {
        if (map != null) {
            String textGerman = map.get(Language.GERMAN);
            String textEnglish = map.get(Language.ENGLISH);
            if (textGerman.length() > 0 && textEnglish.length() > 0) {
                EditVertexAnnotationLogAction editVertexAnnotationLogAction = new EditVertexAnnotationLogAction(map);
                history.execute(editVertexAnnotationLogAction);
            }
        }
    }
}
