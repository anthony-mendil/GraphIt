package actions.deactivate;

import actions.LogAction;
import log_management.LogEntryName;
import log_management.parameters.deactivate.DeactivateAnchorPointsFadeoutParam;

/**
 * Makes the selected anchor-points visible again.
 */
public class DeactivateAnchorPointsFadeoutLogAction extends LogAction {

    /**
     * Constructor which realizes the undo-method of ActivateAnchorPointsLogAction.
     *
     * @param pDeactivateAnchorPointsFadeoutParam The used parameters.
     */
    public DeactivateAnchorPointsFadeoutLogAction(DeactivateAnchorPointsFadeoutParam pDeactivateAnchorPointsFadeoutParam) {
        super(LogEntryName.DEACTIVATE_ANCHOR_POINTS_FADEOUT);
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
