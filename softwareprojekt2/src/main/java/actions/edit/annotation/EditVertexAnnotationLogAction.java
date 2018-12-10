package actions.edit.annotation;

import actions.LogAction;
import log_management.LogDatabaseManager;
import log_management.LogEntryName;
import log_management.parameters.edit.EditSphereAnnotationParam;
import log_management.parameters.edit.EditVertexAnnotationParam;

public class EditVertexAnnotationLogAction extends LogAction {
    public EditVertexAnnotationLogAction(EditVertexAnnotationParam parameters) {
        super(LogEntryName.EDIT_VERTEX_ANNOTATION, parameters);
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

    public static class EditSphereAnnotationLogAction extends LogAction {
        public EditSphereAnnotationLogAction(EditSphereAnnotationParam parameters) {
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
}
