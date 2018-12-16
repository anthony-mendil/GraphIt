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
     * Changes the edge type from all passed edges
     * @param pEdgeArrowType the new edge type
     */
    public EditEdgesTypeLogAction(EdgeArrowType pEdgeArrowType) {
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
