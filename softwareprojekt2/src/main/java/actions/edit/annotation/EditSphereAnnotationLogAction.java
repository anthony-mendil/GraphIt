package actions.edit.annotation;

import actions.LogAction;
import actions.LogEntryName;
import log_management.parameters.edit.EditSphereAnnotationParam;

/*
 * Changes the annotation of a selected sphere.
 */
public class EditSphereAnnotationLogAction extends LogAction {
    /**
     * Constructor in case the user changes the annotation of a sphere.
     *
     * @param pText the new sphere annotation
     */
    public EditSphereAnnotationLogAction(String pText) {
        super(LogEntryName.EDIT_SPHERE_ANNOTATION);
    }

    /**
     * Constructor which will be used to realize the undo-method of itself.
     *
     * @param pEditSphereAnnotationParam The param object containing the sphere and annotation to change to.
     */
    public EditSphereAnnotationLogAction(EditSphereAnnotationParam pEditSphereAnnotationParam) {
        super(LogEntryName.EDIT_SPHERE_ANNOTATION);
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
