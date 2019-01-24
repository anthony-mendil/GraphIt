package actions.deactivate;

import actions.LogAction;
import actions.LogEntryName;
import log_management.parameters.activate_deactivate.ActivateDeactivateFadeoutParam;


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
     * Makes the vertices and edges visible. Also used to implement the undo-method of
     * ActivateFadeoutLogAction.
     *
     * @param pParam The vertices object that contains every vertices that is needed.
     */
    public DeactivateFadeoutLogAction(ActivateDeactivateFadeoutParam pParam) {
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


    public void createParameter() {
        throw new UnsupportedOperationException();
    }
}
