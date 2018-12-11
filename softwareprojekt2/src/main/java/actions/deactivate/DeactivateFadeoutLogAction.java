package actions.deactivate;

import actions.LogAction;
import log_management.LogDatabaseManager;
import log_management.LogEntryName;
import log_management.parameters.deactivate.DeactivateFadeoutParam;

/**
 * Makes the vertices and attached edges, which used to be invisible, visible again.
 */
public class DeactivateFadeoutLogAction extends LogAction {
    /**
     * Constructor in case the user wants to make every vertex and edge visible again.
     */
    public DeactivateFadeoutLogAction(){
        super(LogEntryName.DEACTIVATE_FADEOUT);
    }

    /**
     * Constructor which will be used to realize the undo-method of ActivateFadeoutLogAction.
     * @param pDeactivateFadeoutParam
     * The used parameters.
     */
    public DeactivateFadeoutLogAction(DeactivateFadeoutParam pDeactivateFadeoutParam) {
        super(LogEntryName.DEACTIVATE_FADEOUT);
    }

    @Override
    public void action() {
        // other stuff that is done when actions is performed

        LogDatabaseManager.addLogEntryToDatabase(this);
    }

    @Override
    public void undo() {
        // stuff that is done when undoing
        // and adding the according actions to the database
        // (opposite actions)
    }

}
