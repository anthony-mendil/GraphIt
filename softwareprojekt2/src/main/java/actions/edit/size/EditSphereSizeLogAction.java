package actions.edit.size;

import actions.LogAction;
import log_management.LogEntryName;
import log_management.parameters.edit.EditSphereSizeParam;

/*
    ([shift+]linksclick, bereich markieren) Spähre markieren -> auftauchende +/- Button
    ([shift+]linksclick, bereich markieren) Sphäre markieren -> strg + mittlere Maustaste
    ([shift+]linksclick, bereich markieren) Sphäre markieren -> strg + +/- Button

 */
public class EditSphereSizeLogAction extends LogAction {
    public EditSphereSizeLogAction(EditSphereSizeParam parameters) {
        super(LogEntryName.EDIT_SPHERE_SIZE);
        throw new UnsupportedOperationException();
    }

    @Override
    public void action() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void undo() {
        throw new UnsupportedOperationException();
    }

    /**
     * creates the parameters object
     */
    @Override
    public void createParameter() {
        throw new UnsupportedOperationException();
    }
}
