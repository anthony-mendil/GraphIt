package actions.edit.color;

import actions.LogAction;
import log_management.LogDatabaseManager;
import log_management.LogEntryName;
import log_management.parameters.edit.EditVerticesColorParam;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/*
    ([shift+]linksclick, bereich markieren) Sphäre markieren -> GUI Button Farbe
    ([shift+]linksclick, bereich markieren) Sphäre markieren -> rechtsclick auf Sphäre ->
     neuer Drop-Down-Menü -> Standard-Farben
 *
 * Changes the color of a single/several vertices.
 */
public class EditVerticesColorLogAction extends LogAction {
    /**
     * Constructor in case the user changes the color of a single/multiple vertex.
     */
    public EditVerticesColorLogAction(){
        super(LogEntryName.EDIT_VERTICES_COLOR);
    }

    /**
     * Constructor which will be used to realize the undo-method of itself.
     * @param pEditVerticesColorParam
     * The used parameters.
     */
    public EditVerticesColorLogAction(EditVerticesColorParam pEditVerticesColorParam) {
        super(LogEntryName.EDIT_VERTICES_COLOR);
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
