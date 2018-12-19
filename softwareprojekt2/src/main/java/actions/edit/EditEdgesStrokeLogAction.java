package actions.edit;

import actions.LogAction;
import actions.LogEntryName;
import graph.graph.StrokeType;
import log_management.parameters.edit.EditEdgesStrokeParam;

/**
 * Changes the stroke of the selected edges.
 */
public class EditEdgesStrokeLogAction extends LogAction {
    /**
     * Constructor for the EditEdgesStrokeLogAction.
     * @param pStrokeType The new stroke type.
     */
    public EditEdgesStrokeLogAction(StrokeType pStrokeType) {
        super(LogEntryName.EDIT_EDGES_STROKE);
        throw new UnsupportedOperationException();
    }
    /**
     * Constructor which will be used to realize the undo-method of itself.
     *
     * @param pEditEdgesStrokeParam The parameter object that contains every parameter that is needed.
     */
    public EditEdgesStrokeLogAction(EditEdgesStrokeParam pEditEdgesStrokeParam) {
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
