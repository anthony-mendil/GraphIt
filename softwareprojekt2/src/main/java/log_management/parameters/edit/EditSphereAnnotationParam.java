package log_management.parameters.edit;

import log_management.parameters.Param;

import java.io.Serializable;

public class EditSphereAnnotationParam extends Param {

    private int sphereId;
    private String oldAnnotation;
    private String newAnnotation;

    public EditSphereAnnotationParam(int sphereId, String oldAnnotation, String newAnnotation) {
        this.sphereId = sphereId;
        this.oldAnnotation = oldAnnotation;
        this.newAnnotation = newAnnotation;
    }

    @Override
    public String convertToJson() {
        throw new UnsupportedOperationException();
    }
}
