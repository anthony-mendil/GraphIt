package actions.edit.form;

import actions.ActionHistory;
import actions.LogAction;
import actions.LogEntryName;
import edu.uci.ics.jung.visualization.picking.PickedState;
import graph.graph.Edge;
import graph.graph.Vertex;
import graph.graph.VertexShapeType;
import graph.visualization.SyndromVisualisationViewer;
import log_management.DatabaseManager;
import log_management.parameters.edit.EditVerticesFormParam;

import java.util.HashMap;
import java.util.Map;

/**
 * Changes the form of the selected vertices.
 */
public class EditVerticesFormLogAction extends LogAction {
    private VertexShapeType type;
    /**
     * Constructor in case the user changes the form of the selected vertices.
     * Gets the picked vertices though pick support.
     * @param pVertexShapeType  The vertices shape type.
     */
    public EditVerticesFormLogAction(VertexShapeType pVertexShapeType) {
        super(LogEntryName.EDIT_VERTICES_FORM);
        type = pVertexShapeType;
    }

    /**
     * Constructor which will be used to realize the undo-method of itself.
     *
     * @param pEditVerticesFormParam The parameter object that contains every parameter that is needed.
     */
    public EditVerticesFormLogAction(EditVerticesFormParam pEditVerticesFormParam) {
        super(LogEntryName.EDIT_VERTICES_FORM);
        parameters = pEditVerticesFormParam;
    }

    @Override
    public void action() {
        SyndromVisualisationViewer<Vertex, Edge> vv = syndrom.getVv();
        PickedState<Vertex> pickedState = vv.getPickedVertexState();
        if(parameters == null) {
            Map<Vertex,VertexShapeType> oldVertices = new HashMap<>();
            Map<Vertex,VertexShapeType> newVertices = new HashMap<>();
            for (Vertex vertex : pickedState.getPicked()) {
                oldVertices.put(vertex, vertex.getShape());
                vertex.setShape(type);
                newVertices.put(vertex, type);
            }
            createParameter(oldVertices, newVertices);
        }else{
            Map<Vertex,VertexShapeType> oldVertices = ((EditVerticesFormParam)parameters).getOldVertices();
            Map<Vertex,VertexShapeType> newVertices = ((EditVerticesFormParam)parameters).getNewVertices();
                for (Map.Entry<Vertex,VertexShapeType> entry : oldVertices.entrySet()){
                    entry.getKey().setShape(newVertices.get(entry.getKey()));
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
        Map<Vertex,VertexShapeType> oldVertices = ((EditVerticesFormParam)parameters).getOldVertices();
        Map<Vertex,VertexShapeType> newVertices = ((EditVerticesFormParam)parameters).getNewVertices();
        EditVerticesFormParam editVerticesFormParam = new EditVerticesFormParam(newVertices, oldVertices);
        EditVerticesFormLogAction editVerticesFormLogAction = new EditVerticesFormLogAction(editVerticesFormParam);
        editVerticesFormLogAction.action();
    }

    public void createParameter(Map<Vertex,VertexShapeType> oldVertices, Map<Vertex,VertexShapeType> newVertices) {
        parameters = new EditVerticesFormParam(oldVertices, newVertices);
    }
}
