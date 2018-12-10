package actions.add;

import actions.LogAction;
import log_management.LogDatabaseManager;
import log_management.LogEntryName;
import log_management.parameters.add.AddSphereParam;

import java.awt.geom.Point2D;

/**
 * Adds a sphere to the graph.
 */
public class AddSphereLogAction extends LogAction {
    public AddSphereLogAction(AddSphereParam pAddSphereParam) {
        super(LogEntryName.ADD_SPHERE);
    }

    /**
     * Constructor in case the user
     * @param pPoint2D
     */
    public AddSphereLogAction(Point2D pPoint2D){
        super(LogEntryName.ADD_SPHERE);
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
