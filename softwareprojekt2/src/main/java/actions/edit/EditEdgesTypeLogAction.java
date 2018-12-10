package actions.edit;

import actions.LogAction;
import log_management.LogDatabaseManager;
import log_management.LogEntryName;
import log_management.parameters.edit.EditEdgesTypeParam;

/*
    ([shift+]linksclick, bereich markieren) Kanten markieren -> GUI-Button Pfeiltyp
    ([shift+]linksclick, bereich markieren) Kanten markieren -> rechtsclick auf Kante -> Drop-Down-Menü -> Pfeiltyp
        -> neuer Drop-Down-Menü
 */
public class EditEdgesTypeLogAction extends LogAction {
    public EditEdgesTypeLogAction(EditEdgesTypeParam parameters) {
        super(LogEntryName.EDIT_EDGES_TYPE, parameters);
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
