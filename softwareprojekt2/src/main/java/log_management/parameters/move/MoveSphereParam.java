package log_management.parameters.move;

import LogManagement.Parameters.Param;

import java.io.Serializable;

public class MoveSphereParam extends Param implements Serializable {

    private int sphereId;
    private String sphereAnnotation;
    private double oldXCoor;
    private double newXCoor;
    private double oldYCoor;
    private double newYCoor;

    public MoveSphereParam(int sphereId, String sphereAnnotation, double oldXCoor, double newXCoor, double oldYCoor, double newYCoor) {
        this.sphereId = sphereId;
        this.sphereAnnotation = sphereAnnotation;
        this.oldXCoor = oldXCoor;
        this.newXCoor = newXCoor;
        this.oldYCoor = oldYCoor;
        this.newYCoor = newYCoor;
    }
}
