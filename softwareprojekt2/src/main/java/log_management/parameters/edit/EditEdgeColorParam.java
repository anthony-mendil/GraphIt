package log_management.parameters.edit;

import log_management.parameters.Param;

import java.awt.*;
import java.io.Serializable;

public class EditEdgeColorParam extends Param implements Serializable {

    private int edgeId;
    private int sourceVertexId;
    private String sourceVertexAnnotation;
    private int targetVertexId;
    private String targetVertexAnnotation;
    private Color oldColor;
    private Color newColor;

    public EditEdgeColorParam(int edgeId, int sourceVertexId, String sourceVertexAnnotation, int targetVertexId, String targetVertexAnnotation, Color oldColor, Color newColor) {
        this.edgeId = edgeId;
        this.sourceVertexId = sourceVertexId;
        this.sourceVertexAnnotation = sourceVertexAnnotation;
        this.targetVertexId = targetVertexId;
        this.targetVertexAnnotation = targetVertexAnnotation;
        this.oldColor = oldColor;
        this.newColor = newColor;
    }

    @Override
    public String convertToJson() {
        throw new UnsupportedOperationException();
    }
}
