package actions.edit.color;

import actions.LogAction;
import log_management.LogDatabaseManager;
import log_management.LogEntryName;
import log_management.parameters.edit.EditVerticesColorParam;

/*
    ([shift+]linksclick, bereich markieren) Sphäre markieren -> GUI Button Farbe
    ([shift+]linksclick, bereich markieren) Sphäre markieren -> rechtsclick auf Sphäre ->
     neuer Drop-Down-Menü -> Standard-Farben
 */
public class EditVerticesColorLogAction extends LogAction {
    public EditVerticesColorLogAction(EditVerticesColorParam parameters) {
        super(LogEntryName.EDIT_VERTICES_COLOR, parameters);
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
