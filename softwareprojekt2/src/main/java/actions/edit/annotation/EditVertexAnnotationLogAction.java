package actions.edit.annotation;

import actions.LogAction;
import log_management.LogDatabaseManager;
import log_management.LogEntryName;
import log_management.parameters.edit.EditSphereAnnotationParam;
import log_management.parameters.edit.EditVertexAnnotationParam;
/*
    rechtclick auf Sphäre -> Drop-Down-Menü -> Umbenennen
    *Wenn möglich, doppelclick auf Sphäre -> Umbenennen
    *Wenn möglich, doppelclick auf Sphäre in Übersicht -> Umbenennen


=======
/**
 * Changes the annotation of a selected Vertex.

 */
public class EditVertexAnnotationLogAction extends LogAction {
    /**
     * Constructor in case the user wants to change the annotation of vertex.
     */
    public EditVertexAnnotationLogAction(String pText){
        super(LogEntryName.EDIT_VERTEX_ANNOTATION);
    }
    /**
     * Constructor which will be used to realize the undo-method of itself.
     * @param pEditVertexAnnotationParam
     */
    public EditVertexAnnotationLogAction(EditVertexAnnotationParam pEditVertexAnnotationParam) {
        super(LogEntryName.EDIT_VERTEX_ANNOTATION);
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

