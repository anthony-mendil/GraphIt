package actions.activate;

import actions.LogAction;
import log_management.LogDatabaseManager;
import log_management.LogEntryName;
import log_management.parameters.activate.ActivateHighlightParam;

public class ActivateHighlightLogAction extends LogAction {
    public ActivateHighlightLogAction(ActivateHighlightParam parameters) {
        super(LogEntryName.ACTIVATE_HIGHLIGHT, parameters);
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
