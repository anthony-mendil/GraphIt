package actions.edit.font;

import actions.LogAction;
import actions.LogEntryName;

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
