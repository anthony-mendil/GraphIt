package actions.edit.font;

import actions.LogAction;
import actions.LogEntryName;
import log_management.parameters.edit.EditFontSphereParam;

/**
 * Changes the font of annotations.
 */
public class EditFontSphereLogAction extends LogAction {
    /**
     * Constructor in case the user wants to change the font.
     *
     * @param pFont The font-name.
     */
    public EditFontSphereLogAction(String pFont) {
        super(LogEntryName.EDIT_FONT_SPHERE);
    }

    /**
     * Constructor which will be used to realize the undo-method of itself.
     *
     * @param pEditFontSphereParam The EditFontSphereParam object containing the new font and the sphere.
     */
    public EditFontSphereLogAction(EditFontSphereParam pEditFontSphereParam) {
        super(LogEntryName.EDIT_FONT_SPHERE);
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
