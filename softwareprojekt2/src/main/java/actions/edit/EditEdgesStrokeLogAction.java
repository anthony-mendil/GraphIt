package actions.edit;

import actions.LogAction;
import log_management.LogDatabaseManager;
import log_management.LogEntryName;
import log_management.parameters.edit.EditEdgesSizeParam;

import java.awt.*;

/**
 * Changes the stroke of the selected edges.
 */
public class EditEdgesStrokeLogAction extends LogAction {
    /**
     * Constructor which will be used to realize the undo-method of itself.
     * @param pStroke
     * The new stroke.
     */
    public EditEdgesStrokeLogAction(String pStroke) {
        super(LogEntryName.EDIT_EDGES_SIZE);
    }
    /**
     * Constructor which will be used to realize the undo-method of itself.
     * @param pEdgesSizeParam
     * The used parameters.
     */
    public EditEdgesStrokeLogAction(EditEdgesSizeParam pEdgesSizeParam) {
        super(LogEntryName.EDIT_EDGES_SIZE);
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
