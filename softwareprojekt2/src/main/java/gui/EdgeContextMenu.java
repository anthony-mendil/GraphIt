package gui;

import actions.ActionHistory;
import actions.edit.EditEdgesStrokeLogAction;
import actions.edit.EditEdgesTypeLogAction;
import actions.edit.annotation.EditVertexAnnotationLogAction;
import actions.edit.color.EditEdgesColorLogAction;
import actions.edit.color.EditVerticesDrawColorLogAction;
import actions.edit.color.EditVerticesFillColorLogAction;
import actions.edit.font.EditFontSizeVerticesLogAction;
import actions.edit.font.EditFontVerticesLogAction;
import actions.remove.RemoveAnchorPointsLogAction;
import actions.remove.RemoveEdgesLogAction;
import actions.remove.RemoveVerticesLogAction;
import graph.graph.Edge;
import graph.graph.FunctionMode;
import graph.graph.Vertex;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.util.Optional;

@Data
public class EdgeContextMenu {
    @Setter(AccessLevel.NONE)
    private final ContextMenu contextMenu;
    private final ActionHistory history;
    private final Values values;
    private final Edge edge;


    public EdgeContextMenu(Edge edge){
        contextMenu = new ContextMenu();
        history = ActionHistory.getInstance();
        values = Values.getInstance();
        this.edge = edge;
        setup();
    }

    private void setup(){
        // REMOVE
        MenuItem remove = new MenuItem("Entfernen");
        HelperGui.setImage("/icons2/008-rubbish-bin.png", remove);

        remove.setOnAction(event -> {
            RemoveEdgesLogAction removeEdgesLogAction = new RemoveEdgesLogAction();
            history.execute(removeEdgesLogAction);
        });

        // COLOR FILL
        MenuItem color = new MenuItem("Kantenfarbe");
        HelperGui.setImage("/icons2/fill.png", color);
        color.setOnAction(event ->{
            EditEdgesColorLogAction editEdgesColorLogAction = new EditEdgesColorLogAction(values.getEdgePaint());
            history.execute(editEdgesColorLogAction);
        });

        // Stroke type
        MenuItem strokeType = new MenuItem("Kantenart");
        HelperGui.setImage("/icons2/021-line.png", strokeType);
        strokeType.setOnAction(event ->{
            EditEdgesStrokeLogAction editEdgesStrokeLogAction = new EditEdgesStrokeLogAction(values.getStrokeEdge());
            history.execute(editEdgesStrokeLogAction);
        });

        // Pfeilspitze
        MenuItem arrowType = new MenuItem("Pfeilspitze");
        HelperGui.setImage("/icons2/017-arrow.png", arrowType);
        arrowType.setOnAction(event ->{
            EditEdgesTypeLogAction editEdgesTypeLogAction = new EditEdgesTypeLogAction(values.getEdgeArrowType());
            history.execute(editEdgesTypeLogAction);
        });

        // unlink
        MenuItem unlink = new MenuItem("Anker entfernen");
        HelperGui.setImage("/icons2/unlink.png", unlink);
        unlink.setOnAction(event ->{
            RemoveAnchorPointsLogAction removeAnchorPointsLogAction = new RemoveAnchorPointsLogAction();
            history.execute(removeAnchorPointsLogAction);
        });

        boolean lockedEdgeType = edge.isLockedEdgeType();
        boolean lockedStyle = edge.isLockedStyle();
        if (!lockedEdgeType || values.getMode() == FunctionMode.TEMPLATE){
            contextMenu.getItems().add(arrowType);
        }
        if (!lockedStyle || values.getMode() == FunctionMode.TEMPLATE){
            contextMenu.getItems().addAll(color, strokeType);
        }
        if (!lockedStyle && !lockedEdgeType || values.getMode() == FunctionMode.TEMPLATE){
            contextMenu.getItems().add(remove);
        }
        contextMenu.getItems().add(unlink);
        contextMenu.setAutoHide(true);
        contextMenu.addEventHandler(MouseEvent.MOUSE_RELEASED, e -> {
            contextMenu.hide();
        });
    }
}
