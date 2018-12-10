package log_management.parameters.move;

import LogManagement.Parameters.Param;

import java.io.Serializable;

public class MoveVertexParam extends Param implements Serializable {

    private int vertexId;
    private String vertexAnnotation;
    private int oldSphereId;
    private String oldSphereAnnotation;
    private int newSphereId;
    private String newSphereAnnotation;
    private double oldXCoor;
    private double newXCoor;
    private double oldYCoor;
    private double newYCoor;

    public MoveVertexParam(int vertexId, String vertexAnnotation, int oldSphereId, String oldSphereAnnotation, int newSphereId, String newSphereAnnotation, double oldXCoor, double newXCoor, double oldYCoor, double newYCoor) {
        this.vertexId = vertexId;
        this.vertexAnnotation = vertexAnnotation;
        this.oldSphereId = oldSphereId;
        this.oldSphereAnnotation = oldSphereAnnotation;
        this.newSphereId = newSphereId;
        this.newSphereAnnotation = newSphereAnnotation;
        this.oldXCoor = oldXCoor;
        this.newXCoor = newXCoor;
        this.oldYCoor = oldYCoor;
        this.newYCoor = newYCoor;
    }
}
