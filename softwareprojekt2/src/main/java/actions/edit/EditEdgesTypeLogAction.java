package actions.edit;

import actions.LogAction;
import actions.LogEntryName;
import edu.uci.ics.jung.visualization.picking.PickedState;
import graph.graph.Edge;
import graph.graph.EdgeArrowType;
import graph.graph.FunctionMode;
import graph.graph.Vertex;
import graph.visualization.SyndromVisualisationViewer;
import log_management.DatabaseManager;
import log_management.parameters.edit.EditEdgesTypeParam;
import lombok.Data;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Changes the EdgeType from a collection of edges.
 */
@Data
public class EditEdgesTypeLogAction extends LogAction {
    /**
     * Temporary variable for the new arrow-type.
     */
    private EdgeArrowType type;

    /**
     * Changes the edge type from all defined edges in pParam.
     *
     * @param pParam The EditEdgesTypeParam object, containing all edges where to change the edge type.
     */
    public EditEdgesTypeLogAction(EditEdgesTypeParam pParam) {
        super(LogEntryName.EDIT_EDGES_TYPE);
        parameters = pParam;
    }

    /**
     * Changes the edge type from all passed edges
     *
     * @param pEdgeArrowType The new edge type.
     */
    public EditEdgesTypeLogAction(EdgeArrowType pEdgeArrowType) {
        super(LogEntryName.EDIT_EDGES_TYPE);
        type = pEdgeArrowType;
    }

    @Override
    public void action() {
        SyndromVisualisationViewer<Vertex, Edge> vv = syndrom.getVv();
        if(parameters == null) {
            List<Edge> lockedEdges = new LinkedList<>();
            PickedState<Edge> pickedState = vv.getPickedEdgeState();
            Map<Edge,EdgeArrowType> oldEdges = new HashMap<>();
            Map<Edge,EdgeArrowType> newEdges = new HashMap<>();
            for (Edge e : pickedState.getPicked() ) {
                if(!e.isLockedEdgeType() || values.getMode() == FunctionMode.TEMPLATE) {
                    oldEdges.put(e, e.getArrowType());
                    e.setArrowType(type);
                    newEdges.put(e, type);
                }else{
                    helper.setActionText("Die Art der Pfeilspitze der Kante(n) darf aufgrund der Vorlageregeln nicht geändert werden.", true);
                    lockedEdges.add(e);
                }
            }
            createParameter(oldEdges,newEdges);
        }else{
            Map<Edge,EdgeArrowType> oldEdges = ((EditEdgesTypeParam)parameters).getEdgesOldEdgeType();
            Map<Edge,EdgeArrowType> newEdges = ((EditEdgesTypeParam)parameters).getEdgesNewEdgeType();
            for(Map.Entry<Edge,EdgeArrowType> entry : oldEdges.entrySet()){
                entry.getKey().setArrowType(newEdges.get(entry.getKey()));
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
        Map<Edge,EdgeArrowType> oldEdges = ((EditEdgesTypeParam)parameters).getEdgesOldEdgeType();
        Map<Edge,EdgeArrowType> newEdges = ((EditEdgesTypeParam)parameters).getEdgesNewEdgeType();
        EditEdgesTypeParam editEdgesTypeParam = new EditEdgesTypeParam(oldEdges,newEdges);
        EditEdgesTypeLogAction editEdgesTypeLogAction = new EditEdgesTypeLogAction(editEdgesTypeParam);
        editEdgesTypeLogAction.action();
    }


    public void createParameter(Map<Edge,EdgeArrowType> oldVertices, Map<Edge,EdgeArrowType> newVertices ) {
        parameters = new EditEdgesTypeParam(oldVertices,newVertices);
    }
}
