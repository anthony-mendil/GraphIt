package log_management.parameters.edit;

import log_management.parameters.Param;

import java.awt.*;
import java.io.Serializable;
import java.util.List;

public class EditVerticesFormParam extends Param {

    private List<Integer> verticesId;
    private List<String> verticesAnnotations;
    private List<Shape> oldShape;
    private Shape newShape;

    public EditVerticesFormParam(List<Integer> verticesId, List<String> verticesAnnotations, List<Shape> oldShape, Shape newShape) {
        this.verticesId = verticesId;
        this.verticesAnnotations = verticesAnnotations;
        this.oldShape = oldShape;
        this.newShape = newShape;
    }

    @Override
    public String convertToJson() {
        throw new UnsupportedOperationException();
    }
}
