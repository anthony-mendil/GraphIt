package actions.edit.color;

import actions.LogAction;
import log_management.LogDatabaseManager;
import log_management.LogEntryName;
import log_management.parameters.edit.EditEdgeColorParam;
import log_management.parameters.edit.EditEdgesColorParam;

/*
    ([shift+]linksclick, bereich markieren) Kanten markieren -> GUI Button Farbe
    ([shift+]linksclick, bereich markieren) Kanten markieren -> rechtsclick auf Kante ->
     neuer Drop-Down-Menü -> Standard-Farben
=======

/**
 * Changes the color of the selected edges.
>>>>>>> developCleme
 */
public class EditEdgesColorLogAction extends LogAction {
    /**
     * Constructor in case the user changes the color of all/several edges.
     */
    public EditEdgesColorLogAction(){
        super(LogEntryName.EDIT_EDGE_COLOR);
    }

    /**
     * Constructor which will be used to realize the undo-method of itself.
     * @param parameters
     * The used parameters.
     */
    public EditEdgesColorLogAction(EditEdgeColorParam parameters) {
        super(LogEntryName.EDIT_EDGE_COLOR);
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
