package actions.edit.color;

import actions.LogAction;
import log_management.LogEntryName;
import log_management.parameters.edit.EditVerticesColorParam;

/*
 * Changes the color of a single/several vertices.
 */
public class EditVerticesFillColorLogAction extends LogAction {
    /**
     * Constructor in case the user changes the color of a single/multiple vertices.
     * Gets the vertices though picksupport.
     */
    public EditVerticesFillColorLogAction() {
        super(LogEntryName.EDIT_VERTICES_FILL_COLOR);
    }

    /**
     * Constructor which will be used to realize the undo-method of itself.
     *
     * @param pEditVerticesColorParam The used parameters.
     */
    public EditVerticesFillColorLogAction(EditVerticesColorParam pEditVerticesColorParam) {
        super(LogEntryName.EDIT_VERTICES_FILL_COLOR);
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
