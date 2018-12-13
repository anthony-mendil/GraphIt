package actions.edit.font;

import actions.LogAction;
import log_management.LogEntryName;
import log_management.parameters.edit.EditFontSizeSphereParam;

/*
 *   ([shift+]linksclick, bereich markieren) Sphäre/Symptom -> GUI Button Größe
 *
 * Changes the font-size of annotations.
 *
 */
public class EditFontSizeVerticesLogAction extends LogAction {
    /**
     * Constructor in case the user changes the font-size of the annotation.
     *
     * @param pSize the size of the font
     */
    public EditFontSizeVerticesLogAction(int pSize) {
        super(LogEntryName.EDIT_FONT_SIZE);
    }

    /**
     * Constructor which will be used to realize the undo-method of itself.
     *
     * @param pEditFontSizeSphereParam The EditFontSizeSphereParam containing the new font size and the vertices
     */
    public EditFontSizeVerticesLogAction(EditFontSizeSphereParam pEditFontSizeSphereParam) {
        super(LogEntryName.EDIT_FONT_SIZE);
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
