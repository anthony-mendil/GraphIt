package actions.remove;

import actions.GraphAction;
import edu.uci.ics.jung.visualization.picking.PickedState;
import graph.graph.Edge;
import graph.graph.Vertex;
import graph.visualization.SyndromVisualisationViewer;

/**
 * Removes the highlight of the picked elements.
 * @author Clement Phung, Nina Unterberg
 */
public class RemoveHighlightElementAction extends GraphAction {

    @Override
    public void action() {
        SyndromVisualisationViewer<Vertex, Edge> vv = syndrom.getVv();
        PickedState<Edge> pickedState = vv.getPickedEdgeState();
        PickedState<Vertex> vertexPickedState = vv.getPickedVertexState();

        for (Edge e : pickedState.getPicked()) {
            e.setHighlighted(false);
        }
        for (Vertex v : vertexPickedState.getPicked()) {
            v.setHighlighted(false);
        }

        vv.repaint();
        syndrom.getVv2().repaint();
    }

    @Override
    public void undo() {
        //no Undo for this action.
    }
}
