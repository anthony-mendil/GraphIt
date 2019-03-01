package gui;

import actions.ActionHistory;
import actions.add.AddFadeoutElementAction;
import actions.add.AddHighlightElementAction;
import actions.edit.EditEdgesStrokeLogAction;
import actions.edit.EditEdgesTypeLogAction;
import actions.edit.color.EditEdgesColorLogAction;
import actions.remove.RemoveAnchorPointsAction;
import actions.remove.RemoveEdgesLogAction;
import actions.remove.RemoveFadeoutElementAction;
import actions.remove.RemoveHighlightElementAction;
import graph.graph.Edge;
import graph.graph.FunctionMode;
import gui.properties.LoadLanguage;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
public class EdgeContextMenu {
    @Setter(AccessLevel.NONE)
    private final ContextMenu contextMenu;
    private final ActionHistory history;
    private final Values values;
    private final Edge edge;
    private LoadLanguage language = LoadLanguage.getInstance();

    public EdgeContextMenu(Edge edge) {
        contextMenu = new ContextMenu();
        history = ActionHistory.getInstance();
        values = Values.getInstance();
        this.edge = edge;
        setup();
    }

    private void setup() {
        // REMOVE
        MenuItem remove = new MenuItem(language.loadLanguagesKey("CONTEXT_DIALOG_REMOVE"));
        HelperGui.setImage("/icons2/008-rubbish-bin.png", remove);

        remove.setOnAction(event -> {
            RemoveEdgesLogAction removeEdgesLogAction = new RemoveEdgesLogAction();
            history.execute(removeEdgesLogAction);
        });

        // COLOR FILL
        MenuItem color = new MenuItem(language.loadLanguagesKey("CONTEXT_DIALOG_STROKE_EDGE_COLOR"));
        HelperGui.setImage("/icons2/fill.png", color);
        color.setOnAction(event -> {
            EditEdgesColorLogAction editEdgesColorLogAction = new EditEdgesColorLogAction(values.getEdgePaint());
            history.execute(editEdgesColorLogAction);
        });

        // Stroke type
        MenuItem strokeType = new MenuItem(language.loadLanguagesKey("CONTEXT_DIALOG_STROKE_EDGE_TYPE"));
        HelperGui.setImage("/icons2/021-line.png", strokeType);
        strokeType.setOnAction(event -> {
            EditEdgesStrokeLogAction editEdgesStrokeLogAction = new EditEdgesStrokeLogAction(values.getStrokeEdge());
            history.execute(editEdgesStrokeLogAction);
        });

        // Pfeilspitze
        MenuItem arrowType = new MenuItem(language.loadLanguagesKey("CONTEXT_DIALOG_EDGE_ARROW"));
        HelperGui.setImage("/icons2/017-arrow.png", arrowType);
        arrowType.setOnAction(event -> {
            EditEdgesTypeLogAction editEdgesTypeLogAction = new EditEdgesTypeLogAction(values.getEdgeArrowType());
            history.execute(editEdgesTypeLogAction);
        });

        // unlink
        MenuItem unlink = new MenuItem(language.loadLanguagesKey("CONTEXT_DIALOG_EDGE_ARROW_REMOVE"));
        HelperGui.setImage("/icons2/unlink.png", unlink);
        unlink.setOnAction(event -> {
            RemoveAnchorPointsAction removeAnchorPointsAction = new RemoveAnchorPointsAction();
            history.execute(removeAnchorPointsAction);
        });

        // Highlight
        MenuItem highlight = new MenuItem(language.loadLanguagesKey("CONTEXT_DIALOG_Highlight"));
        HelperGui.setImage("/icons2/002-marker.png", highlight);

        if (edge.isHighlighted()) {
            highlight.setOnAction(event -> {
                RemoveHighlightElementAction removeHighlightElementAction = new RemoveHighlightElementAction();
                history.execute(removeHighlightElementAction);
            });
            highlight.setStyle("-fx-text-fill: #395cab;");

        } else {
            highlight.setOnAction(event -> {
                AddHighlightElementAction addHighlightElementAction = new AddHighlightElementAction();
                history.execute(addHighlightElementAction);
            });
        }

        // isVisible
        MenuItem visible = new MenuItem(language.loadLanguagesKey("CONTEXT_DIALOG_VISIBLE"));
        HelperGui.setImage("/icons2/005-hide-2.png", visible);

        if (!edge.isVisible()) {
            visible.setOnAction(event -> {
                RemoveFadeoutElementAction removeFadeoutElementAction = new RemoveFadeoutElementAction();
                history.execute(removeFadeoutElementAction);
            });
            visible.setStyle("-fx-text-fill: #395cab;");

        } else {
            visible.setOnAction(event -> {
                AddFadeoutElementAction addFadeoutElementAction = new AddFadeoutElementAction();
                history.execute(addFadeoutElementAction);
            });
        }

        boolean lockedEdgeType = edge.isLockedEdgeType();
        boolean lockedStyle = edge.isLockedStyle();
        if (!lockedEdgeType || values.getMode() == FunctionMode.TEMPLATE) {
            contextMenu.getItems().add(arrowType);
        }
        if (!lockedStyle || values.getMode() == FunctionMode.TEMPLATE) {
            contextMenu.getItems().addAll(color, strokeType);
        }
        if (!lockedStyle && !lockedEdgeType || values.getMode() == FunctionMode.TEMPLATE) {
            contextMenu.getItems().add(remove);
        }
        contextMenu.getItems().addAll(unlink, highlight, visible);
        contextMenu.setAutoHide(true);
        contextMenu.addEventHandler(MouseEvent.MOUSE_RELEASED, e -> contextMenu.hide());
    }
}
