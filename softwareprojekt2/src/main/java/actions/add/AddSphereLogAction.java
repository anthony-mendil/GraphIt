package actions.add;

import actions.LogAction;
import actions.LogEntryName;
import log_management.parameters.add_remove.AddRemoveSphereParam;

import java.awt.geom.Point2D;

/**
 * Adds a sphere to the graph.
 */
public class AddSphereLogAction extends LogAction {

    /**
     * Constructor in case the user clicks on somewhere blank space to create a sphere.
     *
     * @param pPoint2D The point where to add the sphere and it refers to the upper left corner of the sphere.
     */
    public AddSphereLogAction(Point2D pPoint2D) {
        super(LogEntryName.ADD_SPHERE);
        throw new UnsupportedOperationException();
    }

    /**
     * Adds a sphere to the graph.
     * Constructor which will be used to realize the undo-method of RemoveSphereLogAction.
     *
     * @param pAddRemoveSphereParam The used parameters.
     */
    public AddSphereLogAction(AddRemoveSphereParam pAddRemoveSphereParam) {
        super(LogEntryName.ADD_SPHERE);
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

    @Override
    public void createParameter() {
        throw new UnsupportedOperationException();
    }
}
