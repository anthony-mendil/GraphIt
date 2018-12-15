package actions.remove;

import actions.LogAction;
import graph.graph.Vertex;
import log_management.LogEntryName;
import log_management.parameters.add_remove.AddRemoveVerticesParam;

import java.util.Collection;

/**
 * Removes vertices from the syndrom-graph.
 */
public class RemoveVerticesLogAction extends LogAction {

    /**
     * all vertices which should be removed
     */
    Collection<Vertex> vertices;

    /**
     * Removes all passed vertices from the graph.
     * Gets the picked vertices through picksupport
     */
    public RemoveVerticesLogAction() {
        super(LogEntryName.REMOVE_VERTICES);
    }

    /**
     * Removes all vertices which are defined in pParam. Also used to implement the undo-method of
     * AddVerticesLogAction.
     *
     * @param pParam the RemoveVerticesParam, containing all vertices to remove
     */
    public RemoveVerticesLogAction(AddRemoveVerticesParam pParam) {
        super(LogEntryName.REMOVE_VERTICES);
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
