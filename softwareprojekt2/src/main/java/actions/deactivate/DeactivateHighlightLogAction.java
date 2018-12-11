package actions.deactivate;

import actions.LogAction;
import log_management.LogDatabaseManager;
import log_management.LogEntryName;
import log_management.parameters.deactivate.DeactivateHighlightParam;
/*
    (GUI Button Highlight deaktivieren)
    rechtsclick auf Arbeitsfläche -> Highlight deaktivieren
 */
public class DeactivateHighlightLogAction extends LogAction {
    public DeactivateHighlightLogAction(DeactivateHighlightParam parameters) {
        super(LogEntryName.DEACTIVATE_HIGHLIGHT, parameters);
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
