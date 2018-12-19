package actions.edit.form;

import actions.LogAction;
import actions.LogEntryName;
import graph.graph.VertexShapeType;
import log_management.parameters.edit.EditVerticesFormParam;

/**
 * Changes the form of the selected vertices.
 */
public class EditVerticesFormLogAction extends LogAction {
    /**
     * Constructor in case the user changes the form of the selected vertices.
     * Gets the picked vertices though pick support.
     * @param pVertexShapeType  The vertices shape type.
     */
    public EditVerticesFormLogAction(VertexShapeType pVertexShapeType) {
        super(LogEntryName.EDIT_VERTICES_FORM);
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
