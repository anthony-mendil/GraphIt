package actions.edit.size;

import actions.LogAction;
import log_management.LogDatabaseManager;
import log_management.LogEntryName;
import log_management.parameters.edit.EditSphereSizeParam;

/*
    ([shift+]linksclick, bereich markieren) Spähre markieren -> auftauchende +/- Button
    ([shift+]linksclick, bereich markieren) Sphäre markieren -> strg + mittlere Maustaste
    ([shift+]linksclick, bereich markieren) Sphäre markieren -> strg + +/- Button

 */
public class EditSphereSizeLogAction extends LogAction {
    public EditSphereSizeLogAction(EditSphereSizeParam parameters) {
        super(LogEntryName.EDIT_SPHERE_SIZE, parameters);
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
