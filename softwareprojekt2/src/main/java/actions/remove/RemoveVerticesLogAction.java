package actions.remove;

import actions.LogAction;
import actions.LogEntryName;
import edu.uci.ics.jung.visualization.picking.PickedState;
import graph.graph.Edge;
import graph.graph.SyndromGraph;
import graph.graph.Vertex;
import graph.visualization.SyndromVisualisationViewer;
import log_management.parameters.add_remove.AddRemoveVerticesParam;

import java.util.Collection;

/**
 * Removes vertices from the syndrom graph.
 */
public class RemoveVerticesLogAction extends LogAction {

    /**
     * All vertices which should be removed.
     */
    /* Must be set on private or public */
    public Collection<Vertex> vertices;

    /**
     * Removes all passed vertices from the graph.
     * Gets the picked vertices through pick support.
     */
    public RemoveVerticesLogAction() {
        super(LogEntryName.REMOVE_VERTICES);
    }

    /**
     * Removes all vertices which are defined in pParam. Also used to implement the undo-method of
     * AddVerticesLogAction.
     *
     * @param pParam The RemoveVerticesParam object, containing all vertices to remove.
     */
    public RemoveVerticesLogAction(AddRemoveVerticesParam pParam) {
        super(LogEntryName.REMOVE_VERTICES);
    }

    @Override
    public void action() {
        SyndromVisualisationViewer<Vertex, Edge> vv = syndrom.getVv();
        PickedState<Vertex> pickedState = vv.getPickedVertexState();
        SyndromGraph<Vertex, Edge> graph = (SyndromGraph<Vertex, Edge>) vv.getGraphLayout().getGraph();
        for (Vertex vertex: pickedState.getPicked()) {
            graph.removeVertex(vertex);
        }
        vv.repaint();
    }

    @Override
    public void undo() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void createParameter() {
        throw new UnsupportedOperationException();
    }
}
