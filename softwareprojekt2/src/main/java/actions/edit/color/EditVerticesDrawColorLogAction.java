package actions.edit.color;

import actions.LogAction;
import actions.LogEntryName;
import log_management.parameters.edit.EditVerticesDrawColorParam;

import java.awt.*;

/*
 * Changes the color of a single/several vertices.
 */
public class EditVerticesDrawColorLogAction extends LogAction {
    /**
     * Constructor in case the user changes the color of a single/multiple vertices.
     * Gets the vertices though picksupport.
     * @param pColor the color to draw the vertices
     */
    public EditVerticesDrawColorLogAction(Color pColor) {
        super(LogEntryName.EDIT_VERTICES_DRAW_COLOR);
    }
    /**
     * Constructor which will be used to realize the undo-method of itself.
     *
     * @param pEditVerticesDrawColorParam the used parameters
     */
    public EditVerticesDrawColorLogAction(EditVerticesDrawColorParam pEditVerticesDrawColorParam) {
        super(LogEntryName.EDIT_VERTICES_DRAW_COLOR);
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
