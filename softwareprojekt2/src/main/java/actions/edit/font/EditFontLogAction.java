package actions.edit.font;

import actions.LogAction;
import log_management.LogDatabaseManager;
import log_management.LogEntryName;
import log_management.parameters.edit.EditFontParam;

public class EditFontLogAction extends LogAction {
    public EditFontLogAction(EditFontParam parameters) {
        super(LogEntryName.EDIT_FONT, parameters);
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
