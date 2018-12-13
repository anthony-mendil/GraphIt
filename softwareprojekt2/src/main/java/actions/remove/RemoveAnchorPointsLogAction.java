package actions.remove;

import actions.LogAction;
import graph.graph.Edge;
import log_management.LogEntryName;
import log_management.parameters.add_remove.AddRemoveAnchorPointsParam;


import java.util.Collection;

/**
 * Removes anchor points from the syndrom-graph.
 */
public class RemoveAnchorPointsLogAction extends LogAction {
    /**
     * all edge containing the anchor points which should be removed
     */
    Collection<Edge> edges;

    /**
     * Removes all anchor points of the passed edges.
     *
     * @param edges collection with edges, if the edge has a anchor point the anchor point will be removed
     */
    public RemoveAnchorPointsLogAction(Collection<Edge> edges) {
        super(LogEntryName.REMOVE_ANCHOR_POINTS);
        throw new UnsupportedOperationException();
    }

    /**
     * Removes all anchor points of every edge.
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
