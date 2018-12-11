package actions.edit.form;

import actions.LogAction;
import log_management.LogDatabaseManager;
import log_management.LogEntryName;

/**
 *
 */
public class EditVertexFormLogAction extends LogAction {
    public EditVertexFormLogAction(EditVertexFormParam parameters) {
        super(LogEntryName.EDIT_VERTEX_FORM);
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
