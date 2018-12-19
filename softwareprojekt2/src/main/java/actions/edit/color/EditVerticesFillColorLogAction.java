package actions.edit.color;

import actions.LogAction;
import actions.LogEntryName;
import log_management.parameters.edit.EditVerticesFillColorParam;

import java.awt.*;

/**
 * Changes the color of a single/several vertices.
 */
public class EditVerticesFillColorLogAction extends LogAction {
    /**
     * Constructor in case the user changes the color of a single/multiple vertices.
     * Gets the vertices though pick support.
     * @param pColor The color to paint the edges.
     */
    public EditVerticesFillColorLogAction(Color pColor) {
        super(LogEntryName.EDIT_VERTICES_FILL_COLOR);
    }
    /**
     * Constructor which will be used to realize the undo-method of itself.
     *
     * @param pEditVerticesFillColorParam The EditVertexAnnotationParam object containing the new vertex annotation.
     */
    public EditVerticesFillColorLogAction(EditVerticesFillColorParam pEditVerticesFillColorParam) {
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
