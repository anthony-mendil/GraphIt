package actions.edit.size;

import actions.LogAction;
import actions.LogEntryName;
import log_management.parameters.edit.EditVerticesSizeParam;

/*
 * changes the size of a vertex
 */
public class EditVerticesSizeLogAction extends LogAction {

    public EditVerticesSizeLogAction() {
        super(LogEntryName.EDIT_VERTICES_SIZE);
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

    /**
     * creates the parameters object
     */
    @Override
    public void createParameter() {
        throw new UnsupportedOperationException();
    }

}
