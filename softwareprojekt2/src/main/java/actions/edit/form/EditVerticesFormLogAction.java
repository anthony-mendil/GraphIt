package actions.edit.form;

import actions.LogAction;
import log_management.LogDatabaseManager;
import log_management.LogEntryName;
import log_management.parameters.edit.EditVerticesFormParam;

import java.awt.*;

/**
 * Changes the form of the selected vertices.
 */
public class EditVerticesFormLogAction extends LogAction {
    /**
     * Constructor in case the user changes the form of the selected vertices.
     * @param pShape
     * The new shape.
     */
    public EditVerticesFormLogAction(Shape pShape) {
        super(LogEntryName.EDIT_VERTICES_FORM);
    }
    /**
     * Constructor which will be used to realize the undo-method of itself.
     * @param parameters
     * The used parameters.
     */
    public EditVerticesFormLogAction(EditVerticesFormParam parameters) {
        super(LogEntryName.EDIT_VERTICES_FORM);
    }

    @Override
    public void action() {
        // other stuff that is done when actions is performed

        LogDatabaseManager.addLogEntryToDatabase(this);
    }

    @Override
    public void undo() {
        // stuff that is done when undoing
        // and adding the according actions to the database
        // (opposite actions)
    }

}
