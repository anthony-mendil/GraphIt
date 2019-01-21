package actions.edit;

import actions.LogAction;
import actions.LogEntryName;
import edu.uci.ics.jung.visualization.picking.PickedState;
import graph.graph.Edge;
import graph.graph.EdgeArrowType;
import graph.graph.Vertex;
import graph.visualization.SyndromVisualisationViewer;
import log_management.parameters.edit.EditEdgesTypeParam;
import lombok.Data;

import java.util.Map;

/**
 * Changes the EdgeType from a collection of edges.
 */
@Data
public class EditEdgesTypeLogAction extends LogAction {
    private EdgeArrowType type;

    /**
     * Changes the edge type from all defined edges in pParam.
     *
     * @param pParam The EditEdgesTypeParam object, containing all edges where to change the edge type.
     */
    public EditEdgesTypeLogAction(EditEdgesTypeParam pParam) {
        super(LogEntryName.EDIT_EDGES_TYPE);
        throw new UnsupportedOperationException();
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
        PickedState<Edge> pickedState = vv.getPickedEdgeState();

        for (Edge e: pickedState.getPicked()) {
           e.setArrowType(type);
        }
        vv.repaint();
        syndrom.getVv2().repaint();
    }

    @Override
    public void undo() {
        throw new UnsupportedOperationException();
    }


    public void createParameter() {
        throw new UnsupportedOperationException();
    }
}
