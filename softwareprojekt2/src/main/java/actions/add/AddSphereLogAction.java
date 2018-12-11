package actions.add;

import actions.LogAction;
import log_management.LogEntryName;
import log_management.parameters.add.AddSphereParam;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.awt.geom.Point2D;

/*
    GUI Button Sphäre hinzufügen
    rechtsclick auf Arbeitsfläche -> Drop-Down-Menu -> Sphäre hinzufügen
 */

/**
 * Adds a sphere to the graph.
 */
public class AddSphereLogAction extends LogAction {

    /**
     * Constructor in case the user clicks on somewhere blank space to create a sphere.
     *
     * @param pPoint2D
     */
    public AddSphereLogAction(Point2D pPoint2D) {
        super(LogEntryName.ADD_SPHERE);
        throw new NotImplementedException();
    }

    /**
     * Constructor which will be used to realize the undo-method of RemoveSphereLogAction.
     *
     * @param pAddSphereParam The used parameters.
     */
    public AddSphereLogAction(AddSphereParam pAddSphereParam) {
        super(LogEntryName.ADD_SPHERE);
        throw new NotImplementedException();
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
