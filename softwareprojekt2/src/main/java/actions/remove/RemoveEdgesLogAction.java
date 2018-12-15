package actions.remove;

import actions.LogAction;
import actions.LogEntryName;
import log_management.parameters.add_remove.AddRemoveEdgesParam;

/**
 * Removes edges from the syndrom-graph.
 */
public class RemoveEdgesLogAction extends LogAction {

    /**
     * Removes all passed edges from the graph.
     * Gets the picked edges through picksupport
     *
     */
    public RemoveEdgesLogAction() {
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
