package actions.edit.font;

import actions.LogAction;
import log_management.LogEntryName;
import log_management.parameters.edit.EditFontSizeParam;

/*
 *   ([shift+]linksclick, bereich markieren) Sphäre/Symptom -> GUI Button Größe
 *
 * Changes the font-size of annotations.
 *
 */
public class EditFontSizeSphereLogAction extends LogAction {
    /**
     * Constructor in case the user changes the font-size of a sphere annotation.
     *
     * @param pSize the size of the font.
     */
    public EditFontSizeSphereLogAction(int pSize) {
        super(LogEntryName.EDIT_FONT_SIZE);
    }

    /**
     * Constructor which will be used to realize the undo-method of itself.
     *
     * @param pEditFontSizeParam The EditFontSizeParam containing the font size and the sphere
     */
    public EditFontSizeSphereLogAction(EditFontSizeParam pEditFontSizeParam) {
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
