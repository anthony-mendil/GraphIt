package actions.edit.color;

import actions.LogAction;
import log_management.LogDatabaseManager;
import log_management.LogEntryName;
import log_management.parameters.edit.EditVerticesColorParam;

/**
 * Changes the color of a single/several vertices.
 */
public class EditVerticesColorLogAction extends LogAction {
    /**
     * Constructor in case the user changes the color of a single/multiple vertex.
     */
    public EditVerticesColorLogAction(){
        super(LogEntryName.EDIT_VERTICES_COLOR);
    }

    /**
     * Constructor which will be used to realize the undo-method of itself.
     * @param pEditVerticesColorParam
     * The used parameters.
     */
    public EditVerticesColorLogAction(EditVerticesColorParam pEditVerticesColorParam) {
        super(LogEntryName.EDIT_VERTICES_COLOR);
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
