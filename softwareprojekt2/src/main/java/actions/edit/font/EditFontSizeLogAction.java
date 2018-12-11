package actions.edit.font;

import actions.LogAction;
import log_management.LogDatabaseManager;
import log_management.LogEntryName;
import log_management.parameters.edit.EditFontSizeParam;

/*
    ([shift+]linksclick, bereich markieren) Sphäre/Symptom -> GUI Button Größe
 */
public class EditFontSizeLogAction extends LogAction {
    public EditFontSizeLogAction(EditFontSizeParam parameters) {
        super(LogEntryName.EDIT_FONT_SIZE, parameters);
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
