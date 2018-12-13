package log_management.parameters.move;

import log_management.parameters.Param;
import lombok.Getter;

import java.awt.geom.Point2D;
import java.io.Serializable;

/**
 * Parameterobject of the action MoveSphereLogAction.
 */
public class MoveSphereParam extends Param{
    /**
     * The sphere-Id.
     */
    @Getter
    private int sphereId;
    /**
     * The annotation of the sphere.
     */
    @Getter
    private String sphereAnnotation;
    /**
     * The old position of the sphere.
     */
    @Getter
    private Point2D oldPosition;
    /**
     * The new posisiton of the sphere.
     */
    @Getter
    private Point2D newPosition;

    /**
     * Creates a paramteterobject of its own class.
     * @param pSphereId The sphere-Id.
     * @param pSphereAnnotation The annotation of the sphere.
     * @param pOldPosition The old position of the sphere.
     * @param pNewPosition The new position of the sphere.
     */
    public MoveSphereParam(int pSphereId, String pSphereAnnotation, Point2D pOldPosition, Point2D pNewPosition) {
        this.sphereId = sphereId;
        this.sphereAnnotation = sphereAnnotation;
        this.oldPosition = oldPosition;
        this.newPosition = pNewPosition;
    }

    @Override
    public String convertToJson() {
        return null;
    }
}
