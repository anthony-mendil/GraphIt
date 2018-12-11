package actions.edit.annotation;

import actions.LogAction;
import log_management.LogDatabaseManager;
import log_management.LogEntryName;
import log_management.parameters.edit.EditSphereAnnotationParam;
import log_management.parameters.edit.EditVertexAnnotationParam;

import java.awt.geom.Point2D;

/**
 * Changes the annotation of a selected sphere.
 */
public class EditSphereAnnotationLogAction extends LogAction {
    /**
     * Constructor in case the user changes the annotation of a sphere.
     */
    public EditSphereAnnotationLogAction(String pText){
        super(LogEntryName.EDIT_SPHERE_ANNOTATION);
    }
    /**
     * Constructor which will be used to realize the undo-method of itself.
     * @param pEditSphereAnnotationParam
     * The
     */
    public EditSphereAnnotationLogAction(EditSphereAnnotationParam pEditSphereAnnotationParam) {
        super(LogEntryName.EDIT_SPHERE_ANNOTATION);
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
