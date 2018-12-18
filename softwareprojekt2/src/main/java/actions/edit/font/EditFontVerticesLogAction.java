package actions.edit.font;

import actions.LogAction;
import actions.LogEntryName;
import log_management.parameters.edit.EditFontVerticesParam;

/*
 * Changes the font of annotations.
 */
public class EditFontVerticesLogAction extends LogAction {
    /**
     * Constructor in case the user wants to change the font.
     *
     * @param pFont the font-name.
     */
    public EditFontVerticesLogAction(String pFont) {
        super(LogEntryName.EDIT_FONT_VERTICES);
    }
    /**
     * Constructor which will be used to realize the undo-method of itself.
     *
     * @param pEditFontVerticesParam the used parameters
     */
    public EditFontVerticesLogAction(EditFontVerticesParam pEditFontVerticesParam) {
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
