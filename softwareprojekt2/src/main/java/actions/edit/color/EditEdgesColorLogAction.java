package actions.edit.color;

import actions.LogAction;
import log_management.LogDatabaseManager;
import log_management.LogEntryName;
import log_management.parameters.edit.EditEdgesColorParam;
/*
    ([shift+]linksclick, bereich markieren) Kanten markieren -> GUI Button Farbe
    ([shift+]linksclick, bereich markieren) Kanten markieren -> rechtsclick auf Kante ->
     neuer Drop-Down-Menü -> Standard-Farben
 */
public class EditEdgesColorLogAction extends LogAction {
    public EditEdgesColorLogAction(EditEdgesColorParam parameters) {
        super(LogEntryName.EDIT_EDGES_COLOR, parameters);
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
