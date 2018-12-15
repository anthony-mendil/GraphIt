package actions.edit.font;

import actions.LogAction;
import log_management.LogEntryName;
import log_management.parameters.edit.EditFontSphereParam;
import log_management.parameters.edit.EditFontVerticesParam;

/*
 * Changes the font of annotations.
 */
public class EditFontVertexLogAction extends LogAction {
    /**
     * Constructor in case the user wants to change the font.
     *
     * @param pFont the font-name.
     */
    public EditFontVertexLogAction(String pFont) {
        super(LogEntryName.EDIT_FONT_VERTICES);
    }

    /**
     * Constructor which will be used to realize the undo-method of itself.
     *
     * @param parameters the EditFontSphereParam containing the new font and a collection of vertices
     */
    public EditFontVertexLogAction(EditFontVerticesParam parameters) {
        super(LogEntryName.EDIT_FONT_VERTICES);
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
