package actions.edit.annotation;

import actions.LogAction;
import actions.LogEntryName;
import log_management.parameters.edit.EditVertexAnnotationParam;

/**
 * Changes the annotation of a selected Vertex.
 */
public class EditVertexAnnotationLogAction extends LogAction {
    /**
     * Constructor in case the user wants to change the annotation of vertex.
     *
     * @param pText The new vertex annotation.
     */
    public EditVertexAnnotationLogAction(String pText) {
        super(LogEntryName.EDIT_VERTEX_ANNOTATION);
    }

    /**
     * Constructor which will be used to realize the undo-method of itself.
     *
     * @param pEditVertexAnnotationParam The parameter object containing the new vertex annotation.
     */
    public EditVertexAnnotationLogAction(EditVertexAnnotationParam pEditVertexAnnotationParam) {
        super(LogEntryName.EDIT_VERTEX_ANNOTATION);
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

