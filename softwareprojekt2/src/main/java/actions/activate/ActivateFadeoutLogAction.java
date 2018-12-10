package actions.activate;

import actions.LogAction;
import log_management.LogDatabaseManager;
import log_management.LogEntryName;
import log_management.parameters.activate.ActivateFadeoutParam;

public class ActivateFadeoutLogAction extends LogAction {
    public ActivateFadeoutLogAction(ActivateFadeoutParam parameters) {
        super(LogEntryName.ACTIVATE_FADEOUT, parameters);
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