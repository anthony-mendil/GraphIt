package actions.remove;

import actions.GraphAction;
import edu.uci.ics.jung.visualization.picking.PickedState;
import graph.graph.Edge;
import graph.graph.Vertex;
import graph.visualization.SyndromVisualisationViewer;

public class RemoveFadeoutElementAction extends GraphAction {

    @Override
    public void action() {
        SyndromVisualisationViewer<Vertex, Edge> vv = syndrom.getVv();
        PickedState<Edge> pickedState = vv.getPickedEdgeState();
        PickedState<Vertex> vertexPickedState = vv.getPickedVertexState();

        for (Edge e : pickedState.getPicked()) {
            e.setVisible(true);
        }
        for (Vertex v : vertexPickedState.getPicked()) {
            v.setVisible(true);
        }

        vv.repaint();
        syndrom.getVv2().repaint();
    }

    /**
     * There is no undo operation of this action.
     */
    @Override
    public void undo() {
        return;
    }
}
