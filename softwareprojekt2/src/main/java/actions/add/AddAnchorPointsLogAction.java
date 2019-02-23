package actions.add;

import actions.LogAction;
import actions.LogEntryName;
import log_management.parameters.add_remove.AddRemoveAnchorPointsParam;

import java.awt.geom.Point2D;

/**
 * Adds an anchor-point to the selected vertex.
 */
public class AddAnchorPointsLogAction extends LogAction {
    /**
     * Constructor in the case few anchor-points shall be added.
     * Gets all picked edges through pick support.
     */
    public AddAnchorPointsLogAction() {
        super(LogEntryName.ADD_ANCHOR_POINTS);
        throw new UnsupportedOperationException();
    }

    /**
     * Constructor in the case only one anchor-point shall be added.
     *
     * @param pPoint2D The position of the vertex which hosts the anchor-point.
     */
    public AddAnchorPointsLogAction(Point2D pPoint2D) {
        super(LogEntryName.ADD_ANCHOR_POINTS);
        throw new UnsupportedOperationException();
    }

    /**
     * Adds an anchor-point to the edge.
     * Constructor which will be used to realize the undo-method of RemoveAnchorPointsAction.
     *
     * @param pAddRemoveAnchorPointsParam The parameter object containing all anchor points to add.
     */
    public AddAnchorPointsLogAction(AddRemoveAnchorPointsParam pAddRemoveAnchorPointsParam) {
        super(LogEntryName.ADD_ANCHOR_POINTS);
        throw new UnsupportedOperationException();
    }


    /**
     * Single/several anchor-points will be added.
     */
    @Override
    public void action() {
        throw new UnsupportedOperationException();
    }

    /**
     * Undoes the addition of a single/multiple existing anchor-points.
     */
    @Override
    public void undo() {
        throw new UnsupportedOperationException();
    }


    public void createParameter() {
        throw new UnsupportedOperationException();
    }
}
