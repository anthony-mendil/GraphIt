package actions.edit;

import actions.Action;
import log_management.LogDatabaseManager;
import log_management.LogEntryName;
import log_management.parameters.edit.EditEdgesFormParam;

public class EditEdgesFormAction extends Action {
    public EditEdgesFormAction(EditEdgesFormParam parameters) {
        super(LogEntryName.EDIT_EDGES_FORM, parameters);
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
