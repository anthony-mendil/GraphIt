package log_management.parameters.move;

import graph.graph.Sphere;
import log_management.parameters.Param;
import lombok.Data;
import lombok.Getter;

import java.awt.geom.Point2D;
import java.io.Serializable;

/**
 * Parameter object of the action MoveSphereLogAction.
 */
@Data
public class MoveSphereParam extends Param{
    /**
     * the sphere containing its old position
     */
    @Getter
    private Sphere sphere;
    /**
     * The new position of the sphere.
     */
    @Getter
    private Point2D newPosition;

    /**
     * Creates a parameter object of its own class.
     * @param pSphere the sphere containing its old position
     * @param pNewPosition The new position of the sphere.
     */
    public MoveSphereParam(Sphere pSphere, Point2D pNewPosition) {
        this.sphere = pSphere;
        this.newPosition = pNewPosition;
    }
    @Override
    public String toString() {
        throw new UnsupportedOperationException();
    }
}
