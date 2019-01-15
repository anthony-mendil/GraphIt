package actions.edit.size;

import actions.LogAction;
import actions.LogEntryName;
import log_management.parameters.edit.EditVerticesSizeParam;

/**
 * Changes the size of a vertex or vertices.
 */
public class EditVerticesSizeLogAction extends LogAction {
    /**
     * Changes the size of the vertices.
     */
    public EditVerticesSizeLogAction() {
        super(LogEntryName.EDIT_VERTICES_SIZE);
        throw new UnsupportedOperationException();
    }
    /**
     * Constructor which will be used to realize the undo-method of itself.
     *
     * @param pEditVerticesSizeParam The parameter object that contains every parameter that is needed.
     */
    public EditVerticesSizeLogAction(EditVerticesSizeParam pEditVerticesSizeParam) {
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
     * Creates the parameter object.
     */
    public void createParameter() {
        throw new UnsupportedOperationException();
    }

}
