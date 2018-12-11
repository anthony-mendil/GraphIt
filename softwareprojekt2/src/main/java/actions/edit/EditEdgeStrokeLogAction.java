package actions.edit;

import actions.LogAction;
import log_management.LogDatabaseManager;
import log_management.LogEntryName;
import log_management.parameters.edit.EditEdgeFormParam;

public class EditEdgeStrokeLogAction extends LogAction {
    public EditEdgeStrokeLogAction(EditEdgeFormParam parameters) {
        super(LogEntryName.EDIT_EDGE_FORM);
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
