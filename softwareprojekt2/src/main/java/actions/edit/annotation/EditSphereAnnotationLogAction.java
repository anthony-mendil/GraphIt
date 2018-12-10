package actions.edit.annotation;

import actions.LogAction;
import log_management.LogDatabaseManager;
import log_management.LogEntryName;
import log_management.parameters.edit.EditVertexAnnotationParam;

public class EditSphereAnnotationLogAction extends LogAction {
    public EditSphereAnnotationLogAction(EditVertexAnnotationParam parameters) {
        super(LogEntryName.EDIT_SPHERE_ANNOTATION, parameters);
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
