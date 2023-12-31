package actions.edit.color;

import actions.LogAction;
import actions.LogEntryName;
import edu.uci.ics.jung.visualization.picking.PickedState;
import graph.graph.Edge;
import graph.graph.FunctionMode;
import graph.graph.Vertex;
import graph.visualization.SyndromVisualisationViewer;
import log_management.DatabaseManager;
import log_management.parameters.edit.EditVerticesDrawColorParam;

import java.awt.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Changes the color of the edge of a single/several vertices.
 * @author Clement Phung, Nina Unterberg
 */
public class EditVerticesDrawColorLogAction extends LogAction {
    /**
     * The new color of the vertices.
     */
    private Color color;

    /**
     * Constructor in case the user changes the color of a single/multiple vertices.
     * Gets the vertices though pick support.
     *
     * @param pColor The color to draw the vertices.
     */
    public EditVerticesDrawColorLogAction(Color pColor) {
        super(LogEntryName.EDIT_VERTICES_DRAW_COLOR);
        color = pColor;
    }

    /**
     * Constructor which will be used to realize the undo-method of itself.
     *
     * @param pEditVerticesDrawColorParam The parameter object that contains every parameter that is needed.
     */
    private EditVerticesDrawColorLogAction(EditVerticesDrawColorParam pEditVerticesDrawColorParam) {
        super(LogEntryName.EDIT_VERTICES_DRAW_COLOR);
        parameters = pEditVerticesDrawColorParam;
    }

    @Override
    public void action() {
        SyndromVisualisationViewer<Vertex, Edge> vv = syndrom.getVv();
        PickedState<Vertex> pickedStateVertex = vv.getPickedVertexState();
        if (parameters == null) {
            List<Vertex> lockedVertices = new LinkedList<>();
            Map<Vertex, Color> paramOldVertices = new HashMap<>();
            Map<Vertex, Color> paramNewVertices = new HashMap<>();
            for (Vertex vertex : pickedStateVertex.getPicked()) {
                if (!vertex.isLockedStyle() || values.getMode() == FunctionMode.TEMPLATE) {
                    paramOldVertices.put(vertex, vertex.getDrawColor());
                    vertex.setDrawColor(color);
                    paramNewVertices.put(vertex, color);
                } else {
                    helper.setActionText("EDIT_VERTICES_DRAW_COLOR_ALERT", true, true);
                    lockedVertices.add(vertex);
                }
            }
            if (!lockedVertices.isEmpty() && lockedVertices.size() == pickedStateVertex.getPicked().size()) {
                actionHistory.removeLastEntry();
                return;
            }
            createParameter(paramOldVertices, paramNewVertices);
        } else {
            Map<Vertex, Color> oldVertices = ((EditVerticesDrawColorParam) parameters).getOldVertices();
            Map<Vertex, Color> newVertices = ((EditVerticesDrawColorParam) parameters).getNewVertices();
            for (Map.Entry<Vertex, Color> entry : oldVertices.entrySet()) {
                Vertex target = entry.getKey();
                target.setDrawColor(newVertices.get(target));
            }
        }
        vv.repaint();
        syndrom.getVv2().repaint();

        DatabaseManager databaseManager = DatabaseManager.getInstance();
        databaseManager.addEntryDatabase(createLog());
        notifyObserverGraph();
    }

    @Override
    public void undo() {
        Map<Vertex, Color> oldVertices = ((EditVerticesDrawColorParam) parameters).getOldVertices();
        Map<Vertex, Color> newVertices = ((EditVerticesDrawColorParam) parameters).getNewVertices();
        EditVerticesDrawColorParam newParam = new EditVerticesDrawColorParam(newVertices, oldVertices);
        EditVerticesDrawColorLogAction editVerticesDrawColorLogAction = new EditVerticesDrawColorLogAction(newParam);
        editVerticesDrawColorLogAction.action();
    }

    /**
     * Creates the parameter object for this action.
     *
     * @param oldVertices The old vertices and their old color.
     * @param newVertices The new vertices and their new Color.
     */
    public void createParameter(Map<Vertex, Color> oldVertices, Map<Vertex, Color> newVertices) {
        parameters = new EditVerticesDrawColorParam(oldVertices, newVertices);
    }
}
