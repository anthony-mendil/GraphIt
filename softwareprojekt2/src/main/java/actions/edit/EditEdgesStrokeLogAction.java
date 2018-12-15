package actions.edit;

import actions.LogAction;
import log_management.LogEntryName;
import log_management.parameters.edit.EditEdgesStrokeParam;

/**
 * Changes the stroke of the selected edges.
 */
public class EditEdgesStrokeLogAction extends LogAction {
    /**
     * Constructor which will be used to realize the undo-method of itself.
     */
    public EditEdgesStrokeLogAction() {
        super(LogEntryName.EDIT_EDGES_STROKE);
        throw new UnsupportedOperationException();
    }

    /**
     * Constructor which will be used to realize the undo-method of itself.
     *
     * @param pEdgesSizeParam The used parameters.
     */
    public EditEdgesStrokeLogAction(EditEdgesStrokeParam pEdgesSizeParam) {
        super(LogEntryName.EDIT_EDGES_STROKE);
        throw new UnsupportedOperationException();
    }

    @Override
    public void action() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void undo() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void createParameter() {
        throw new UnsupportedOperationException();
    }
}
