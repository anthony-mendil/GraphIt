package log_management.parameters.move;

import log_management.parameters.Param;

import java.io.Serializable;
import java.util.List;

public class MoveVerticesParam extends Param {

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

    public MoveVerticesParam(List<Integer> verticesId, List<String> verticesAnnotation, int oldSphereId, String oldSphereAnnotation, int newSphereId, String newSphereAnnotation) {
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

    @Override
    public String convertToJson() {
        return null;
    }
}
