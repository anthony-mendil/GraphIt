package actions.move;

import actions.LogAction;
import graph.graph.Sphere;
import log_management.LogEntryName;
import log_management.parameters.move.MoveSphereParam;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.awt.geom.Point2D;

/**
 * Moves a sphere from one to another position. A sphere can not be positioned where another sphere is
 * already located. If that's the case the sphere stays at its old position. All vertices assigned to this sphere
 * move with the sphere.
 */
public class MoveSphereLogAction extends LogAction {

    /**
     * Moves the sphere which is defined in pParam.
     * @param pParam the MoveSphereParam, containing the sphere and the position data.
     */
    public MoveSphereLogAction(MoveSphereParam pParam) {
        super(LogEntryName.MOVE_SPHERE);
    }


    /**
     * Moves the sphere from its old to the new position
     * @param newPos the new position to put the sphere
     */
    public MoveSphereLogAction(Sphere sphere, Point2D newPos) {
        super(LogEntryName.MOVE_SPHERE);
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
