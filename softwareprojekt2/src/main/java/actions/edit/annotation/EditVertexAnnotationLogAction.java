package actions.edit.annotation;

import actions.LogAction;
import log_management.LogEntryName;
import log_management.parameters.edit.EditVertexAnnotationParam;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/*
    rechtclick auf Sphäre -> Drop-Down-Menü -> Umbenennen
    *Wenn möglich, doppelclick auf Sphäre -> Umbenennen
    *Wenn möglich, doppelclick auf Sphäre in Übersicht -> Umbenennen
 *
 * Changes the annotation of a selected Vertex.

 */
public class EditVertexAnnotationLogAction extends LogAction {
    /**
     * Constructor in case the user wants to change the annotation of vertex.
     *
     * @param pText the new vertex annotation
     */
    public EditVertexAnnotationLogAction(String pText) {
        super(LogEntryName.EDIT_VERTEX_ANNOTATION);
    }

    /**
     * Constructor which will be used to realize the undo-method of itself.
     *
     * @param pEditVertexAnnotationParam the EditVertexAnnotationParam containing the new vertex annotation
     */
    public EditVertexAnnotationLogAction(EditVertexAnnotationParam pEditVertexAnnotationParam) {
        super(LogEntryName.EDIT_VERTEX_ANNOTATION);
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

