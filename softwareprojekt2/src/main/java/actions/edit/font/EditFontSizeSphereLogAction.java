package actions.edit.font;

import actions.LogAction;
import actions.LogEntryName;
import log_management.parameters.edit.EditFontSizeSphereParam;

/*
 * Changes the font-size of annotations of a sphere.
 */
public class EditFontSizeSphereLogAction extends LogAction {
    /**
     * Constructor in case the user changes the font-size of a sphere annotation.
     * @param pSize the size of the font
     *
     */
    public EditFontSizeSphereLogAction(Integer pSize) {
        super(LogEntryName.EDIT_SPHERE_FONT_SIZE);
    }

    /**
     * Constructor which will be used to realize the undo-method of itself.
     *
     * @param pEditFontSizeSphereParam The EditFontSizeSphereParam containing the font size and the sphere
     */
    public EditFontSizeSphereLogAction(EditFontSizeSphereParam pEditFontSizeSphereParam) {
        super(LogEntryName.EDIT_SPHERE_FONT_SIZE);
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
