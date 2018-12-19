package actions.edit.font;

import actions.LogAction;
import actions.LogEntryName;
import log_management.parameters.edit.EditFontVerticesParam;

/**
 * Changes the font-size of vertex annotations.
 */
public class EditFontSizeVerticesLogAction extends LogAction {
    /**
     * Constructor in case the user changes the font-size of the annotation.
     *
     * @param pSize The size of the font.
     */
    public EditFontSizeVerticesLogAction(int pSize) {
        super(LogEntryName.EDIT_VERTICES_FONT_SIZE);
    }
    /**
     * Constructor which will be used to realize the undo-method of itself.
     *
     * @param pEditFontVerticesParam The EditVertexAnnotationParam object containing the new vertex annotation.
     */
    public EditFontSizeVerticesLogAction(EditFontVerticesParam pEditFontVerticesParam) {
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
