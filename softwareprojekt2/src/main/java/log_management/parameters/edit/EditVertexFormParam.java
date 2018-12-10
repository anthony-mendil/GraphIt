package log_management.parameters.edit;

import LogManagement.Parameters.Param;

import java.awt.*;
import java.io.Serializable;

public class EditVertexFormParam extends Param implements Serializable {

    private int vertexId;
    private String vertexAnnotation;
    private Shape oldShape;
    private Shape newShape;

    public EditVertexFormParam(int vertexId, String vertexAnnotation, Shape oldShape, Shape newShape) {
        this.vertexId = vertexId;
        this.vertexAnnotation = vertexAnnotation;
        this.oldShape = oldShape;
        this.newShape = newShape;
    }
}
