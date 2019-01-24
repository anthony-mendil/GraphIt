package actions.edit.color;

import actions.LogAction;
import actions.LogEntryName;
import edu.uci.ics.jung.visualization.picking.PickedState;
import graph.graph.Edge;
import graph.graph.Vertex;
import graph.visualization.SyndromVisualisationViewer;
import log_management.DatabaseManager;
import log_management.parameters.edit.EditVerticesFillColorParam;
import log_management.tables.Log;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Changes the color of a single/several vertices.
 */
public class EditVerticesFillColorLogAction extends LogAction {
    /**
     * Temporary parameter for the color;
     */
    private Color color;
    /**
     * Constructor in case the user changes the color of a single/multiple vertices.
     * Gets the vertices though pick support.
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
    public EditVerticesFillColorLogAction(EditVerticesFillColorParam pEditVerticesFillColorParam) {
        super(LogEntryName.EDIT_VERTICES_FILL_COLOR);
        parameters = pEditVerticesFillColorParam;
    }

    @Override
    public void action() {
        SyndromVisualisationViewer<Vertex, Edge> vv = syndrom.getVv();
        PickedState<Vertex> pickedState = vv.getPickedVertexState();
        if(parameters == null){
            Map<Vertex,Paint> oldVerticesParam = new HashMap<>();
            Map<Vertex,Paint> newVerticesParam = new HashMap<>();
            for (Vertex vertex: pickedState.getPicked()) {
                oldVerticesParam.put(vertex, vertex.getFillColor());
                newVerticesParam.put(vertex, color);
                vertex.setFillColor(color);
            }
            createParameter(oldVerticesParam, newVerticesParam);
        }else{
            Map<Vertex, Paint> oldVertices = ((EditVerticesFillColorParam)parameters).getOldVertices();
            Map<Vertex, Paint> newVertices = ((EditVerticesFillColorParam)parameters).getNewVertices();
            for(Map.Entry<Vertex,Paint> entry : oldVertices.entrySet()){
                Vertex vertex = entry.getKey();
                vertex.setFillColor((Color) newVertices.get(vertex));
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
        Map<Vertex, Paint> oldVertices = ((EditVerticesFillColorParam)parameters).getOldVertices();
        Map<Vertex, Paint> newVertices = ((EditVerticesFillColorParam)parameters).getNewVertices();
        EditVerticesFillColorParam editVerticesFillColorParam = new EditVerticesFillColorParam(newVertices, oldVertices);
        EditVerticesFillColorLogAction editVerticesFillColorLogAction = new EditVerticesFillColorLogAction(editVerticesFillColorParam);
        editVerticesFillColorLogAction.action();
    }

    public void createParameter(Map<Vertex,Paint> oldVertices, Map<Vertex,Paint> newVertices) {
        parameters = new EditVerticesFillColorParam(oldVertices, newVertices);
    }
}
