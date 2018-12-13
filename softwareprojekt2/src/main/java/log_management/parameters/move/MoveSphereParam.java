package log_management.parameters.move;

import log_management.parameters.Param;

import java.awt.geom.Point2D;
import java.io.Serializable;

public class MoveSphereParam extends Param {

    private int sphereId;
    private String sphereAnnotation;
    private Point2D oldPosition;
    private Point2D newPosition;

    public MoveSphereParam(int sphereId, String sphereAnnotation, Point2D oldPosition, Point2D newPosition) {
        this.sphereId = sphereId;
        this.sphereAnnotation = sphereAnnotation;
        this.oldPosition = oldPosition;
    }

    @Override
    public String convertToJson() {
        return null;
    }
}
