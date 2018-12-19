package actions.edit.size;

import actions.LogAction;
import actions.LogEntryName;
import log_management.parameters.edit.EditSphereSizeParam;

/**
 * Changes the sphere size.
 */
public class EditSphereSizeLogAction extends LogAction {
    /**
     * Constructor which will be used to realize the undo-method of itself.
     * @param parameters The parameter object containing the sphere and size.
     */
    public EditSphereSizeLogAction(EditSphereSizeParam parameters) {
        super(LogEntryName.EDIT_SPHERE_SIZE);
        throw new UnsupportedOperationException();
    }

    /**
     * Creates a new action to change the size of a a sphere.
     * @param size The new size.
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
     * Creates the parameter object.
     */
    @Override
    public void createParameter() {
        throw new UnsupportedOperationException();
    }
}
