package actions.edit;

import actions.LogAction;
import graph.graph.EdgeArrowType;
import log_management.LogDatabaseManager;
import log_management.LogEntryName;
import log_management.parameters.edit.EditEdgesTypeParam;

/**
 * Changes the edge-type of the selected edges.
 */
public class EditEdgesTypeLogAction extends LogAction {
    /**
     * Constructor which will be used to realize the undo-method of itself.
     * @param pEdgeArrowType
     * The used parameters.
     */
    public EditEdgesTypeLogAction(EdgeArrowType pEdgeArrowType) {
        super(LogEntryName.EDIT_EDGES_TYPE);
    }
    /**
     * Constructor which will be used to realize the undo-method of itself.
     * @param pEditEdgesTypeParam
     * The used parameters.
     */
    public EditEdgesTypeLogAction(EditEdgesTypeParam pEditEdgesTypeParam) {
        super(LogEntryName.EDIT_EDGES_TYPE);
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
