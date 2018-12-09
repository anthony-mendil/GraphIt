package log_management.parameters.edit;

import LogManagement.Parameters.Param;

import java.io.Serializable;

public class EditSphereAnnotationParam extends Param implements Serializable {

    private int sphereId;
    private String oldAnnotation;
    private String newAnnotation;

    public EditSphereAnnotationParam(int sphereId, String oldAnnotation, String newAnnotation) {
        this.sphereId = sphereId;
        this.oldAnnotation = oldAnnotation;
        this.newAnnotation = newAnnotation;
    }
}
