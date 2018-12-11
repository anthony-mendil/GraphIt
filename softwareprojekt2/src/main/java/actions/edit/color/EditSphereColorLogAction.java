package actions.edit.color;

import actions.LogAction;
import log_management.LogDatabaseManager;
import log_management.LogEntryName;
import log_management.parameters.edit.EditSphereColorParam;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;


/*
    GUI Button Farbe -> linksclick auf Sphäre
    linksclick auf Sphäre -> GUI Button Farbe
    linksclick auf Sphäre -> Drop-Down-Menü -> Farbe -> neuer Drop-Down-Menü -> Standard-Farbe
 *
 *
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
        throw new NotImplementedException();
    }

    @Override
    public void undo() {
        throw new NotImplementedException();
    }

    @Override
    public void createParameter() {
        throw new NotImplementedException();
    }
}
