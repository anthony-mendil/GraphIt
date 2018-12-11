package actions.edit.font;

import actions.LogAction;
import log_management.LogDatabaseManager;
import log_management.LogEntryName;
import log_management.parameters.edit.EditFontSizeParam;

/*
 *   ([shift+]linksclick, bereich markieren) Sphäre/Symptom -> GUI Button Größe
 *
 * Changes the font-size of annotations.
 *
 */
public class EditFontSizeLogAction extends LogAction {
    /**
     * Constructor in case the user changes the font-size of the annotation.
     * @param pSize
     * The size of the font.
     */
    public EditFontSizeLogAction(int pSize) {
        super(LogEntryName.EDIT_FONT_SIZE);
    }
    /**
     * Constructor which will be used to realize the undo-method of itself.
     * @param pEditFontSizeParam
     * The used parameters.
     */
    public EditFontSizeLogAction(EditFontSizeParam pEditFontSizeParam) {
        super(LogEntryName.EDIT_FONT_SIZE);
    }

    @Override
    public void action() {
        // other stuff that is done when actions is performed

        LogDatabaseManager.addLogEntryToDatabase(this);
    }

    @Override
    public void undo() {
        // stuff that is done when undoing
        // and adding the according actions to the database
        // (opposite actions)
    }
}
