package actions.edit.color;

import actions.LogAction;
import log_management.LogDatabaseManager;
import log_management.LogEntryName;
import log_management.parameters.edit.EditSphereColorParam;

/*
    GUI Button Farbe -> linksclick auf Sphäre
    linksclick auf Sphäre -> GUI Button Farbe
    linksclick auf Sphäre -> Drop-Down-Menü -> Farbe -> neuer Drop-Down-Menü -> Standard-Farbe
 */

public class EditSphereColorLogAction extends LogAction {
    public EditSphereColorLogAction(EditSphereColorParam parameters) {
        super(LogEntryName.EDIT_SPHERE_COLOR, parameters);
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
