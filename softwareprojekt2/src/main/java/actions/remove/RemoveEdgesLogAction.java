package actions.remove;

import actions.LogAction;
import graph.graph.Edge;
import log_management.LogEntryName;
import log_management.parameters.add_remove.AddRemoveEdgesParam;


import java.util.Collection;

/**
 * Removes edges from the syndrom-graph.
 */
public class RemoveEdgesLogAction extends LogAction {
    /**
     * all edge which should be removed
     */
    Collection<Edge> edges;

    /**
     * Removes all passed edges from the graph.
     *
     * @param edges collection of edges which should be removed
     */
    public RemoveEdgesLogAction(Collection<Edge> edges) {
        super(LogEntryName.REMOVE_EDGES);
        throw new UnsupportedOperationException();
    }

    /**
     * Removes all edges with are defined in pParam. Also used to implement the undo-method of
     * AddEdgesLogAction.
     *
     * @param pParam the RemoveEdgesParam, containing all edges to remove
     */
    public RemoveEdgesLogAction(AddRemoveEdgesParam pParam) {
        super(LogEntryName.REMOVE_EDGES);
        throw new UnsupportedOperationException();
    }


    @Override
    public void createParameter() {
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
}
