package actions.edit.color;

import actions.LogAction;
import actions.LogEntryName;
import edu.uci.ics.jung.visualization.picking.PickedState;
import graph.graph.Edge;
import graph.graph.Vertex;
import graph.visualization.SyndromVisualisationViewer;
import log_management.DatabaseManager;
import log_management.parameters.edit.EditVerticesDrawColorParam;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Changes the color of a single/several vertices.
 */
public class EditVerticesDrawColorLogAction extends LogAction {
    private Color color;
    /**
     * Constructor in case the user changes the color of a single/multiple vertices.
     * Gets the vertices though pick support.
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
    public EditVerticesDrawColorLogAction(EditVerticesDrawColorParam pEditVerticesDrawColorParam) {
        super(LogEntryName.EDIT_VERTICES_DRAW_COLOR);
        parameters = pEditVerticesDrawColorParam;
    }
    @Override
    public void action() {
        SyndromVisualisationViewer<Vertex, Edge> vv = syndrom.getVv();
        PickedState<Vertex> pickedStateVertex = vv.getPickedVertexState();
        if(parameters == null){
            Map<Vertex, Paint> paramOldVertices = new HashMap<>();
            Map<Vertex, Paint> paramNewVertices = new HashMap<>();
            for (Vertex vertex: pickedStateVertex.getPicked()) {
                paramOldVertices.put(vertex, vertex.getDrawPaint());
                paramNewVertices.put(vertex, color);
                vertex.setDrawPaint(color);
            }
            createParameter(paramOldVertices,paramNewVertices);
        }else{
            Map<Vertex, Paint> oldVertices = ((EditVerticesDrawColorParam)parameters).getOldVertices();
            Map<Vertex, Paint> newVertices = ((EditVerticesDrawColorParam)parameters).getNewVertices();
            for (Map.Entry<Vertex, Paint> entry : oldVertices.entrySet()){
                Vertex target = entry.getKey();
                target.setDrawPaint(oldVertices.get(target));
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
        Map<Vertex,Paint> oldVertices = ((EditVerticesDrawColorParam)parameters).getOldVertices();
        Map<Vertex,Paint> newVertices = ((EditVerticesDrawColorParam)parameters).getNewVertices();
        EditVerticesDrawColorParam newParam = new EditVerticesDrawColorParam(newVertices,oldVertices);
        EditVerticesDrawColorLogAction editVerticesDrawColorLogAction = new EditVerticesDrawColorLogAction(newParam);
        editVerticesDrawColorLogAction.action();
    }

    public void createParameter(Map<Vertex, Paint> oldVertices, Map<Vertex, Paint> newVertices) {
        parameters = new EditVerticesDrawColorParam(oldVertices, newVertices);
    }
}
