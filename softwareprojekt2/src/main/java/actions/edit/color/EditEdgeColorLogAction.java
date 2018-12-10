package actions.edit.color;

import actions.LogAction;
import log_management.LogDatabaseManager;
import log_management.LogEntryName;
import log_management.parameters.edit.EditEdgeColorParam;

public class EditEdgeColorLogAction extends LogAction {
    public EditEdgeColorLogAction(EditEdgeColorParam parameters) {
        super(LogEntryName.EDIT_EDGE_COLOR, parameters);
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
