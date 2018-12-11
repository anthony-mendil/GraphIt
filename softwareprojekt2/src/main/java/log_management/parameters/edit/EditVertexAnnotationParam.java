package log_management.parameters.edit;

import log_management.parameters.Param;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

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

    @Override
    public String convertToJson() {
        throw new NotImplementedException();
    }
}
