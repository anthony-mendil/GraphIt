package actions.edit.color;

import actions.LogAction;
import log_management.LogEntryName;
import log_management.parameters.edit.EditSphereColorParam;

import java.awt.*;


/*
 * Changes the color of the selected sphere and also the vertices which belongs to it.
 */

public class EditSphereColorLogAction extends LogAction {
    /**
     * Constructor in case the user clicks on a Sphere to change the color.
     * Gets the sphere though picksupport.
     */
    public EditSphereColorLogAction() {
        super(LogEntryName.EDIT_SPHERE_COLOR);
    }

    /**
     * Constructor which will be used to realize the undo-method of itself.
     *
     * @param parameters The used parameters containing the sphere and color
     */
    public EditSphereColorLogAction(EditSphereColorParam parameters) {
        super(LogEntryName.EDIT_SPHERE_COLOR);
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
