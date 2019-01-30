package actions.remove;

import actions.LogAction;
import actions.LogEntryName;
import edu.uci.ics.jung.visualization.picking.PickedState;
import graph.graph.Edge;
import graph.graph.Vertex;
import graph.visualization.SyndromVisualisationViewer;
import log_management.parameters.add_remove.AddRemoveAnchorPointsParam;

/**
 * Removes anchor-points from the syndrom graph.
 */
public class RemoveAnchorPointsLogAction extends LogAction {
    /**
     * Removes all anchor-points of every (picked) edge.
     * Gets the picked edges through pick support.
     */
    public RemoveAnchorPointsLogAction() {
        super(LogEntryName.REMOVE_ANCHOR_POINTS);
    }

    /**
     * Removes all anchor-points that are defined in pParam. Also used to implement the undo-method of
     * AddAnchorPointsLogAction.
     *
     * @param pParam The AddAnchorPointsParam, containing all edges (with anchor points) to remove.
     */
    public RemoveAnchorPointsLogAction(AddRemoveAnchorPointsParam pParam) {
        super(LogEntryName.REMOVE_ANCHOR_POINTS);
        throw new UnsupportedOperationException();
    }


    public void createParameter() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void action() {
        SyndromVisualisationViewer<Vertex, Edge> vv = syndrom.getVv();
        PickedState<Edge> pickedState = vv.getPickedEdgeState();

        for (Edge e: pickedState.getPicked()) {
            e.setHasAnchor(false);
            e.setAnchorPoints(null);
        }
        vv.repaint();
        syndrom.getVv2().repaint();
    }

    @Override
    public void undo() {
        throw new UnsupportedOperationException();
    }
}
