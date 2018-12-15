package actions.remove;

import actions.LogAction;
import actions.LogEntryName;
import log_management.parameters.add_remove.AddRemoveAnchorPointsParam;

/**
 * Removes anchor points from the syndrom-graph.
 */
public class RemoveAnchorPointsLogAction extends LogAction {

    /**
     * Removes all anchor points of every (picked) edge.
     * Gets the picked edges through picksupport.
     */
    public RemoveAnchorPointsLogAction() {
        super(LogEntryName.REMOVE_ANCHOR_POINTS);
        throw new UnsupportedOperationException();
    }

    /**
     * Removes all anchor points that are defined in pParam. Also used to implement the undo-method of
     * AddAnchorPointsLogAction.
     *
     * @param pParam the AddAnchorPointsParam, containing all edges (with anchor points) to remove
     */
    public RemoveAnchorPointsLogAction(AddRemoveAnchorPointsParam pParam) {
        super(LogEntryName.REMOVE_ANCHOR_POINTS);
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
