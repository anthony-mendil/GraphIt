package actions.add;

import actions.LogAction;
import log_management.LogDatabaseManager;
import log_management.LogEntryName;
import log_management.parameters.add.AddSphereParam;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/*
    GUI Button Sphäre hinzufügen
    rechtsclick auf Arbeitsfläche -> Drop-Down-Menu -> Sphäre hinzufügen
 */

import java.awt.geom.Point2D;

/**
 * Adds a sphere to the graph.
 */
public class AddSphereLogAction extends LogAction {

    /**
     * Constructor in case the user clicks on somewhere blank space to create a sphere.
     * @param pPoint2D
     */
    public AddSphereLogAction(Point2D pPoint2D){
        super(LogEntryName.ADD_SPHERE);
    }
    /**
     * Constructor which will be used to realize the undo-method of RemoveSphereLogAction.
     * @param pAddSphereParam
     * The used parameters.
     */
    public AddSphereLogAction(AddSphereParam pAddSphereParam) {
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
    public void createParameter() {
        throw new NotImplementedException();
    }
}
