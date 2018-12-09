package actions.add;

import actions.LogAction;
import edu.uci.ics.jung.algorithms.layout.Layout;
import log_management.LogDatabaseManager;
import log_management.LogEntryName;
import log_management.parameters.add.AddVertexParam;

public class AddVertexLogAction extends LogAction {
    private Layout layout;

    public AddVertexLogAction(AddVertexParam parameters, Layout layout) {
        super(LogEntryName.ADD_VERTEX, parameters);
        this.layout = layout;
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

    @Override
    public void redo() {
        // stuff that is done when redoing
        // and adding the according actions to the database
        // (opposite actions)
    }
}
