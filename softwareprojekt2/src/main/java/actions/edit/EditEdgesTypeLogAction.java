package actions.edit;

import actions.LogAction;
import edu.uci.ics.jung.graph.util.EdgeType;
import graph.graph.Edge;
import graph.graph.EdgeArrowType;
import actions.LogEntryName;
import log_management.parameters.edit.EditEdgesTypeParam;

import java.util.Collection;

/**
 * Changes the EdgeType from a collection of edges.
 */
public class EditEdgesTypeLogAction extends LogAction {
    /**
     * all edge  which should get the new EdgeType
     */
    Collection<Edge> edges;

    /**
     * the new edge type
     */
    EdgeArrowType edgeType;

    /**
     * Changes the edge type from all passed edges
     * @param edgeArrowType the new edge type
     */
    public EditEdgesTypeLogAction(EdgeArrowType edgeArrowType) {
        super(LogEntryName.EDIT_EDGES_TYPE);
        throw new UnsupportedOperationException();
    }

    @Override
    public void action() {
        throw new UnsupportedOperationException();
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
