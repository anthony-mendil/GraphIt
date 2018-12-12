package actions.add;

import actions.LogAction;
import log_management.LogEntryName;
import log_management.parameters.activate.ActivateAnchorPointsFadeoutParam;

import java.awt.geom.Point2D;

public class AddAnchorLogPoints extends LogAction {
    /**
     * Constructor in the case only one anchor-point shall be added.
     *
     * @param pPoint2D The position of the vertex which hosts the anchor-point.
     */
    public AddAnchorLogPoints(Point2D pPoint2D) {
        super(LogEntryName.ADD_ANCHOR_POINTS);
        throw new UnsupportedOperationException();
    }

    /**
     * Constructor in case several anchor-points shall be added.
     */
    public AddAnchorLogPoints() {
        super(LogEntryName.ADD_ANCHOR_POINTS);
        throw new UnsupportedOperationException();
    }

    /**
     * Constructor which will be used to realize the undo-method of RemoveAnchorPointAction.
     *
     * @param pActivateAnchorPointsFadeoutParam The used parameters.
     */
    public AddAnchorLogPoints(ActivateAnchorPointsFadeoutParam pActivateAnchorPointsFadeoutParam) {
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

    @Override
    public void createParameter() {
        throw new UnsupportedOperationException();
    }
}
