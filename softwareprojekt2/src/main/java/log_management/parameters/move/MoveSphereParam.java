package log_management.parameters.move;

import graph.graph.Sphere;
import log_management.parameters.Param;
import lombok.Data;
import lombok.Getter;

import java.awt.geom.Point2D;
import java.io.Serializable;
import java.util.Map;

/**
 * Parameter object of the action MoveSphereLogAction.
 */
@Data
public class MoveSphereParam extends Param implements Serializable {
    /**
     * The sphere containing its old position.
     */
    @Getter
    private Map<Sphere,Point2D> oldSphere;
    /**
     * The sphere containing its new position.
     */
    @Getter
    private Map<Sphere,Point2D> newSphere;

    /**
     * Creates a parameter object of its own class.
     * @param pOldSphere The sphere containing its old position.
     * @param pNewSphere The sphere containing its new position.
     */
    public MoveSphereParam(Map<Sphere,Point2D> pOldSphere, Map<Sphere,Point2D> pNewSphere) {
        this.oldSphere = pNewSphere;
        this.newSphere = pOldSphere;
    }
    @Override
    public String toString() {
        throw new UnsupportedOperationException();
    }
}
