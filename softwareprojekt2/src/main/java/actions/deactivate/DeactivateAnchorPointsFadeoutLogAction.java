package actions.deactivate;

import actions.LogAction;
import actions.LogEntryName;
import log_management.parameters.activate_deactivate.ActivateDeactivateAnchorPointsFadeoutParam;

/**
 * Makes the selected anchor-points visible again.
 */
public class DeactivateAnchorPointsFadeoutLogAction extends LogAction {

    /**
     * Makes all selected anchor-points visible again. Also used to implement the
     * undo-method of ActivateAnchorPointsFadeoutLogAction.
     *
     * @param pParam The parameter object that contains every parameter that is needed.
     */
    public DeactivateAnchorPointsFadeoutLogAction(ActivateDeactivateAnchorPointsFadeoutParam pParam) {
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


    public void createParameter() {
        throw new UnsupportedOperationException();
    }
}
