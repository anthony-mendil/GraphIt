package actions.add;

import actions.LogAction;
import log_management.LogDatabaseManager;
import log_management.LogEntryName;
import log_management.parameters.add.AddVerticesParam;

/**
 * Undoes the method RemoveVerticesLogAction by adding the missing vertices.
 */
public class AddVerticesLogAction extends LogAction {

    /**
     * Constructor which will be used to realize the undo-method of RemoveVerticesLogAction.
     * @param parameters
     * The used parameters.
     */
    public AddVerticesLogAction(AddVerticesParam parameters) {
        super(LogEntryName.ADD_VERTICES);
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
