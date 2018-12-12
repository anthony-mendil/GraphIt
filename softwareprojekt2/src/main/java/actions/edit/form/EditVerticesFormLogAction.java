package actions.edit.form;

import actions.LogAction;
import log_management.LogDatabaseManager;
import log_management.LogEntryName;
import log_management.parameters.edit.EditVerticesFormParam;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.awt.*;

/*
 *  ([shift+]linksclick, bereich markieren) Sphäre markieren -> GUI Button Form
 *   ([shift+]linksclick, bereich markieren) Sphäre markieren -> rechtsclick auf Sphäre -> Form -> neuer Drop-Down-Menü
 *
 * Changes the form of the selected vertices.
 */
public class EditVerticesFormLogAction extends LogAction {
    /**
     * Constructor in case the user changes the form of the selected vertices.
     * @param pShape
     * The new shape.
     */
    public EditVerticesFormLogAction(Shape pShape) {
        super(LogEntryName.EDIT_VERTICES_FORM);
    }
    /**
     * Constructor which will be used to realize the undo-method of itself.
     * @param parameters
     * The used parameters.
     */
    public EditVerticesFormLogAction(EditVerticesFormParam parameters) {
        super(LogEntryName.EDIT_VERTICES_FORM);
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
