package actions.edit.color;

import actions.LogAction;
import actions.LogEntryName;
import edu.uci.ics.jung.visualization.picking.PickedState;
import graph.graph.Edge;
import graph.graph.FunctionMode;
import graph.graph.Vertex;
import graph.visualization.SyndromVisualisationViewer;
import log_management.DatabaseManager;
import log_management.parameters.edit.EditVerticesFillColorParam;

import java.awt.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Changes the fill-color of a single/several vertices.
 */
public class EditVerticesFillColorLogAction extends LogAction {
    /**
     * The new color.
     */
    private Color color;

    /**
     * Constructor in case the user changes the color of a single/multiple vertices.
     * Gets the vertices though pick support.
     *
     * @param pColor The color to paint the edges.
     */
    public EditVerticesFillColorLogAction(Color pColor) {
        super(LogEntryName.EDIT_VERTICES_FILL_COLOR);
        color = pColor;
    }

    /**
     * Constructor which will be used to realize the undo-method of itself.
     *
     * @param pEditVerticesFillColorParam The EditVertexAnnotationParam object containing the new vertex annotation.
     */
    private EditVerticesFillColorLogAction(EditVerticesFillColorParam pEditVerticesFillColorParam) {
        super(LogEntryName.EDIT_VERTICES_FILL_COLOR);
        parameters = pEditVerticesFillColorParam;
    }

    @Override
    public void action() {
        SyndromVisualisationViewer<Vertex, Edge> vv = syndrom.getVv();
        PickedState<Vertex> pickedState = vv.getPickedVertexState();
        if (parameters == null) {
            List<Vertex> lockedVertices = new LinkedList<>();
            Map<Vertex, Color> oldVerticesParam = new HashMap<>();
            Map<Vertex, Color> newVerticesParam = new HashMap<>();
            for (Vertex vertex : pickedState.getPicked()) {
                if (!vertex.isLockedStyle() || values.getMode() == FunctionMode.TEMPLATE) {
                    oldVerticesParam.put(vertex, vertex.getFillColor());
                    vertex.setFillColor(color);
                    newVerticesParam.put(vertex, color);
                } else {
                    helper.setActionText("EDIT_VERTICES_FILL_COLOR_ALERT", true, true);
                    lockedVertices.add(vertex);
                }
            }
            if(lockedVertices.size() > 0 && lockedVertices.size() == pickedState.getPicked().size()){
                actionHistory.removeLastEntry();
                return;
            }
            createParameter(oldVerticesParam, newVerticesParam);
        } else {
            Map<Vertex, Color> oldVertices = ((EditVerticesFillColorParam) parameters).getOldVertices();
            Map<Vertex, Color> newVertices = ((EditVerticesFillColorParam) parameters).getNewVertices();
            for (Map.Entry<Vertex, Color> entry : oldVertices.entrySet()) {
                Vertex vertex = entry.getKey();
                vertex.setFillColor(newVertices.get(vertex));
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
        Map<Vertex, Color> oldVertices = ((EditVerticesFillColorParam) parameters).getOldVertices();
        Map<Vertex, Color> newVertices = ((EditVerticesFillColorParam) parameters).getNewVertices();
        EditVerticesFillColorParam editVerticesFillColorParam = new EditVerticesFillColorParam(newVertices, oldVertices);
        EditVerticesFillColorLogAction editVerticesFillColorLogAction = new EditVerticesFillColorLogAction(editVerticesFillColorParam);
        editVerticesFillColorLogAction.action();
    }

    /**
     * Creates a new parameter-object for this action.
     *
     * @param oldVertices The old vertices and their old color.
     * @param newVertices The new vertices and thier new color.
     */
    public void createParameter(Map<Vertex, Color> oldVertices, Map<Vertex, Color> newVertices) {
        parameters = new EditVerticesFillColorParam(oldVertices, newVertices);
    }
}
