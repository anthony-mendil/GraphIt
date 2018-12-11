package actions.edit.annotation;

import actions.LogAction;
import log_management.LogEntryName;
import log_management.parameters.edit.EditSphereAnnotationParam;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/*
    rechtclick auf Sphäre -> Drop-Down-Menü -> Umbenennen
    *Wenn möglich, doppelclick auf Sphäre -> Umbenennen
    *Wenn möglich, doppelclick auf Sphäre in Übersicht -> Umbenennen

 * Changes the annotation of a selected sphere.
 */
public class EditSphereAnnotationLogAction extends LogAction {
    /**
     * Constructor in case the user changes the annotation of a sphere.
     */
    public EditSphereAnnotationLogAction(String pText) {
        super(LogEntryName.EDIT_SPHERE_ANNOTATION);
    }

    /**
     * Constructor which will be used to realize the undo-method of itself.
     *
     * @param pEditSphereAnnotationParam The
     */
    public EditSphereAnnotationLogAction(EditSphereAnnotationParam pEditSphereAnnotationParam) {
        super(LogEntryName.EDIT_SPHERE_ANNOTATION);
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
