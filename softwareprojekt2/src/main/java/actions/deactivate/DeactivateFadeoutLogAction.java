package actions.deactivate;

import actions.LogAction;
import log_management.LogEntryName;
import log_management.parameters.deactivate.DeactivateFadeoutParam;

/**
 * Makes the vertices and attached edges, which used to be invisible, visible again.
 */
public class DeactivateFadeoutLogAction extends LogAction {
    /**
     * Constructor in case the user wants to make every vertex and edge visible again.
     */
    public DeactivateFadeoutLogAction() {
        super(LogEntryName.DEACTIVATE_FADEOUT);
    }

    /**
     * Constructor which will be used to realize the undo-method of ActivateFadeoutLogAction.
     *
     * @param pDeactivateFadeoutParam The used parameters.
     */
    public DeactivateFadeoutLogAction(DeactivateFadeoutParam pDeactivateFadeoutParam) {
        super(LogEntryName.DEACTIVATE_FADEOUT);
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
