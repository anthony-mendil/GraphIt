package actions.edit.font;

import actions.LogAction;
import actions.LogEntryName;

/*
 * Changes the font-size of vertex annotations.
 */
public class EditFontSizeVerticesLogAction extends LogAction {
    /**
     * Constructor in case the user changes the font-size of the annotation.
     *
     * @param pSize the size of the font
     */
    public EditFontSizeVerticesLogAction(int pSize) {
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
