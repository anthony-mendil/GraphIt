package actions.edit.form;

import actions.LogAction;
import actions.LogEntryName;
import log_management.parameters.edit.EditVerticesFormParam;

/*
 * Changes the form of the selected vertices.
 */
public class EditVerticesFormLogAction extends LogAction {
    /**
     * Constructor in case the user changes the form of the selected vertices.
     * Gets the picked vertices though picksupport.
     */
    public EditVerticesFormLogAction() {
        super(LogEntryName.EDIT_VERTICES_FORM);
    }

    /**
     * Constructor which will be used to realize the undo-method of itself.
     *
     * @param parameters The used parameters.
     */
    public EditVerticesFormLogAction(EditVerticesFormParam parameters) {
        super(LogEntryName.EDIT_VERTICES_FORM);
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
