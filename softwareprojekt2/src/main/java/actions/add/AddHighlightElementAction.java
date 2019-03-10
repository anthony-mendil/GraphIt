package actions.add;

import actions.GraphAction;
import edu.uci.ics.jung.visualization.picking.PickedState;
import graph.graph.Edge;
import graph.graph.Vertex;
import graph.visualization.SyndromVisualisationViewer;

/**
 * Highlights an element of the graph. This action will not be logged so
 * therefore there would not be an implementation of the undo and the createParameters()
 * method.
 * @author Clement Phung, Nina Unterberg
 */
public class AddHighlightElementAction extends GraphAction {

    @Override
    public void action() {
        SyndromVisualisationViewer<Vertex, Edge> vv = syndrom.getVv();
        PickedState<Edge> pickedState = vv.getPickedEdgeState();
        PickedState<Vertex> vertexPickedState = vv.getPickedVertexState();

        for (Edge e : pickedState.getPicked()) {
            e.setHighlighted(true);
        }
        for (Vertex v : vertexPickedState.getPicked()) {
            v.setHighlighted(true);
        }

        vv.repaint();
        syndrom.getVv2().repaint();
    }

    @Override
    public void undo() {
        //no undo operation for this action
    }
}
