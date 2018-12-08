package log_management.parameters.move;

import LogManagement.Parameters.Param;

import java.io.Serializable;
import java.util.List;

public class MoveVerticesParam extends Param implements Serializable {

    private List<Integer> verticesId;
    private List<String> verticesAnnotation;
    private int oldSphereId;
    private String oldSphereAnnotation;
    private int newSphereId;
    private String newSphereAnnotation;
    private List<Double> oldXCoors;
    private List<Double> newXCoors;
    private List<Double> oldYCoors;
    private List<Double> newYCoors;

    public MoveVerticesParam(List<Integer> verticesId, List<String> verticesAnnotation, int oldSphereId, String oldSphereAnnotation, int newSphereId, String newSphereAnnotation, List<Double> oldXCoors, List<Double> newXCoors, List<Double> oldYCoors, List<Double> newYCoors) {
        this.verticesId = verticesId;
        this.verticesAnnotation = verticesAnnotation;
        this.oldSphereId = oldSphereId;
        this.oldSphereAnnotation = oldSphereAnnotation;
        this.newSphereId = newSphereId;
        this.newSphereAnnotation = newSphereAnnotation;
        this.oldXCoors = oldXCoors;
        this.newXCoors = newXCoors;
        this.oldYCoors = oldYCoors;
        this.newYCoors = newYCoors;
    }
}
