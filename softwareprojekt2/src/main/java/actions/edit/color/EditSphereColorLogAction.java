package actions.edit.color;

import actions.LogAction;
import log_management.LogDatabaseManager;
import log_management.LogEntryName;
import log_management.parameters.edit.EditSphereColorParam;

/**
 * Changes the color of the selected sphere and also the vertices which belongs to it.
 */
public class EditSphereColorLogAction extends LogAction {
    /**
     * Constructor in case the user clicks on a Sphere to change the color.
     */
    public EditSphereColorLogAction(){
        super(LogEntryName.EDIT_SPHERE_COLOR);
    }

    /**
     * Constructor which will be used to realize the undo-method of itself.
     * @param parameters
     * The used parameters.
     */
    public EditSphereColorLogAction(EditSphereColorParam parameters) {
        super(LogEntryName.EDIT_SPHERE_COLOR);
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

    @Override
    public void redo() {
        // stuff that is done when redoing
        // and adding the according actions to the database
        // (opposite actions)
    }
}
