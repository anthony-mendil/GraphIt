package actions.edit.font;

import actions.LogAction;
import log_management.LogDatabaseManager;
import log_management.LogEntryName;
import log_management.parameters.edit.EditFontParam;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/*
 *  ([shift+]linksclick, bereich markieren) Sphäre/Symptom -> GUI Button Schriftart
 *
 *
 * Changes the font of annotations.
 */
public class EditFontSphereLogAction extends LogAction {
    /**
     * Constructor in case the user wants to change the font.
     * @param pFont the font-name.
     */
    public EditFontSphereLogAction(String pFont){
        super(LogEntryName.EDIT_FONT);
    }
    /**
     * Constructor which will be used to realize the undo-method of itself.
     * @param parameters the EditFontParam containing the new font and the sphere
     */
    public EditFontSphereLogAction(EditFontParam parameters) {
        super(LogEntryName.EDIT_FONT);
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
