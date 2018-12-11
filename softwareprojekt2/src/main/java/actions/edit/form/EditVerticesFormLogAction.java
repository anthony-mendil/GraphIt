package actions.edit.form;

import actions.LogAction;
import log_management.LogDatabaseManager;
import log_management.LogEntryName;
import log_management.parameters.edit.EditVerticesFormParam;
/*
    ([shift+]linksclick, bereich markieren) Sphäre markieren -> GUI Button Form
    ([shift+]linksclick, bereich markieren) Sphäre markieren -> rechtsclick auf Sphäre -> Form -> neuer Drop-Down-Menü
 */
public class EditVerticesFormLogAction extends LogAction {
    public EditVerticesFormLogAction(EditVerticesFormParam parameters) {
        super(LogEntryName.EDIT_VERTICES_FORM, parameters);
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
