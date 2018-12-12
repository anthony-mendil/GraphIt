package actions.edit.font;

import actions.LogAction;
import log_management.LogDatabaseManager;
import log_management.LogEntryName;
import log_management.parameters.edit.EditFontSizeParam;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/*
 *   ([shift+]linksclick, bereich markieren) Sphäre/Symptom -> GUI Button Größe
 *
 * Changes the font-size of annotations.
 *
 */
public class EditFontSizeVerticesLogAction extends LogAction {
    /**
     * Constructor in case the user changes the font-size of the annotation.
     * @param pSize the size of the font
     */
    public EditFontSizeVerticesLogAction(int pSize) {
        super(LogEntryName.EDIT_FONT_SIZE);
    }
    /**
     * Constructor which will be used to realize the undo-method of itself.
     * @param pEditFontSizeParam The EditFontSizeParam containing the new font size and the vertices
     */
    public EditFontSizeVerticesLogAction(EditFontSizeParam pEditFontSizeParam) {
        super(LogEntryName.EDIT_FONT_SIZE);
    }

    @Override
    public void action() {
        throw new NotImplementedException();
    }

    @Override
    public void undo() {
        throw new NotImplementedException();
    }

    @Override
    public void createParameter() {
        throw new NotImplementedException();
    }
}
