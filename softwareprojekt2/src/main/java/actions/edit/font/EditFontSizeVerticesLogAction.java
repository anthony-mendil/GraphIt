package actions.edit.font;

import actions.LogAction;
import actions.LogEntryName;
import log_management.parameters.edit.EditFontSizeSphereParam;

/*
 * Changes the font-size of annotations.
 */
public class EditFontSizeVerticesLogAction extends LogAction {
    /**
     * Constructor in case the user changes the font-size of the annotation of vertices.
     */
    public EditFontSizeVerticesLogAction() {
        super(LogEntryName.EDIT_VERTICES_FONT_SIZE);
    }

    /**
     * Constructor which will be used to realize the undo-method of itself.
     *
     * @param pEditFontSizeSphereParam The EditFontSizeSphereParam containing the new font size and the vertices
     */
    public EditFontSizeVerticesLogAction(EditFontSizeSphereParam pEditFontSizeSphereParam) {
        super(LogEntryName.EDIT_VERTICES_FONT_SIZE);
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
