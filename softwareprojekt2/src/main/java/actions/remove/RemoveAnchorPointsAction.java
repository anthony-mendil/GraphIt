package actions.remove;

import actions.GraphAction;
import edu.uci.ics.jung.visualization.picking.PickedState;
import graph.graph.Edge;
import graph.graph.Vertex;
import graph.visualization.SyndromVisualisationViewer;
import javafx.util.Pair;

/**
 * Removes all anchor-points of every (picked) edge.
 * Gets the picked edges through pick support.
 */
public class RemoveAnchorPointsAction extends GraphAction {

    @Override
    public void action() {
        SyndromVisualisationViewer<Vertex, Edge> vv = syndrom.getVv();
        PickedState<Edge> pickedState = vv.getPickedEdgeState();

        for (Edge e : pickedState.getPicked()) {
            e.setHasAnchorOut(false);
            e.setHasAnchorIn(false);
            e.setAnchorPoints(new Pair<>(null, null));
        }
        vv.repaint();
        syndrom.getVv2().repaint();
    }

    @Override
    public void undo() {
        // no undo operation for this action.
    }
}
