package actions.activate;

import actions.LogAction;
import actions.LogEntryName;
import log_management.parameters.activate_deactivate.ActivateDeactivateAnchorPointsFadeoutParam;

import java.awt.geom.Point2D;

/**
 * All existing anchor-points fadeout and are no longer visible for the user.
 */
public class ActivateAnchorPointsFadeoutLogAction extends LogAction {

    /**
     * Constructor in case all/several anchor-points shall fadeout. The action is applied to all picked edges/anchor
     * points.
     */
    public ActivateAnchorPointsFadeoutLogAction() {
        super(LogEntryName.ACTIVATE_ANCHOR_POINTS_FADEOUT);
        throw new UnsupportedOperationException();
    }

    /**
     * Constructor for the action in case only one anchor-point shall fade out.
     *
     * @param pPoint2D The position of the vertex which hosts the anchor-point.
     */
    public ActivateAnchorPointsFadeoutLogAction(Point2D pPoint2D) {
        super(LogEntryName.ACTIVATE_ANCHOR_POINTS_FADEOUT);
        throw new UnsupportedOperationException();
    }

    /**
     * Fadeout all anchor points defined in ActivateAnchorPointsFadeoutParam. Also used to implement the undo-method of
     * DeactivateAnchorPointsFadeoutLogAction.
     *
     * @param pActivateAnchorPointsFadeoutParam The used parameter object containing the anchor points to fade out.
     */
    public ActivateAnchorPointsFadeoutLogAction(ActivateDeactivateAnchorPointsFadeoutParam pActivateAnchorPointsFadeoutParam) {
        super(LogEntryName.ACTIVATE_ANCHOR_POINTS_FADEOUT);
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


    public void createParameter() {
        throw new UnsupportedOperationException();
    }
}
