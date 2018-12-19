package actions.edit.color;

import actions.LogAction;
import actions.LogEntryName;
import log_management.parameters.edit.EditEdgesColorParam;

import java.awt.*;

/**
 * Changes the color of the selected edges.
 */
public class EditEdgesColorLogAction extends LogAction {

    /**
     * Constructor in case the user changes the color of all/several edges.
     * Gets the picked edges through pick support.
     * @param pColor The color to paint the edges.
     */
    public EditEdgesColorLogAction(Color pColor) {
        super(LogEntryName.EDIT_EDGES_COLOR);
    }
    /**
     * Constructor which will be used to realize the undo-method of itself.
     *
     * @param pEditEdgesColorParam The parameter object that contains every parameter that is needed.
     */
    public EditEdgesColorLogAction(EditEdgesColorParam pEditEdgesColorParam) {
        super(LogEntryName.EDIT_EDGES_COLOR);
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
