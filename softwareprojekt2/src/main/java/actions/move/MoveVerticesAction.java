package actions.move;

import actions.Action;
import log_management.LogDatabaseManager;
import log_management.LogEntryName;
import log_management.parameters.move.MoveVerticesParam;

public class MoveVerticesAction extends Action {
    public MoveVerticesAction(MoveVerticesParam parameters) {
        super(LogEntryName.MOVE_VERTICES, parameters);
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
