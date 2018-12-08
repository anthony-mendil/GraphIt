package actions.deactivate;

import actions.Action;
import log_management.LogDatabaseManager;
import log_management.LogEntryName;
import log_management.parameters.deactivate.DeactivateHighlightParam;

public class DeactivateHighlightAction extends Action {
    public DeactivateHighlightAction(DeactivateHighlightParam parameters) {
        super(LogEntryName.DEACTIVATE_HIGHLIGHT, parameters);
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

    @Override
    public void redo() {
        // stuff that is done when redoing
        // and adding the according actions to the database
        // (opposite actions)
    }
}
