package log_management.parameters.edit;

import LogManagement.Parameters.Param;

import java.io.Serializable;

public class EditVertexAnnotationParam extends Param implements Serializable {

    private int vertexId;
    private String oldAnnotation;
    private String newAnnotation;

    public EditVertexAnnotationParam(int vertexId, String oldAnnotation, String newAnnotation) {
        this.vertexId = vertexId;
        this.oldAnnotation = oldAnnotation;
        this.newAnnotation = newAnnotation;
    }
}
