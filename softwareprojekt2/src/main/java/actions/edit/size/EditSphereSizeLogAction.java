package actions.edit.size;

import actions.LogAction;
import actions.LogEntryName;
import log_management.parameters.edit.EditSphereSizeParam;

/*
 * changes the sphere size
 */
public class EditSphereSizeLogAction extends LogAction {
    /**
     * Constructor which will be used to realize the undo-method of itself.
     * @param parameters the parameters containing the sphere and size
     */
    public EditSphereSizeLogAction(EditSphereSizeParam parameters) {
        super(LogEntryName.EDIT_SPHERE_SIZE);
        throw new UnsupportedOperationException();
    }

    /**
     * creates a new action to change the size of a a sphere
     * @param size the new size
     */
    public EditSphereSizeLogAction(Integer size){
        super(LogEntryName.EDIT_SPHERE_SIZE);
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
