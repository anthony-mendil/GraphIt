package actions.edit.color;

import actions.LogAction;
import log_management.LogDatabaseManager;
import log_management.LogEntryName;

public class EditVertexColorLogAction extends LogAction {
    public EditVertexColorLogAction(EditVertexColorParam parameters) {
        super(LogEntryName.EDIT_VERTEX_COLOR);
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
