package actions.edit.color;

import actions.LogAction;
import actions.LogEntryName;
import log_management.parameters.edit.EditEdgesColorParam;

/*
 * Changes the color of the selected edges.
 */
public class EditEdgesColorLogAction extends LogAction {
    /**
     * Constructor in case the user changes the color of all/several edges.
     * Gets the picked edges through picksupport
     */
    public EditEdgesColorLogAction() {
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
