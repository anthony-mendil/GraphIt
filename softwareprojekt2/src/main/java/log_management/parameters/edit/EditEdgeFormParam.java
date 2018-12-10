package log_management.parameters.edit;

import LogManagement.Parameters.Param;

import java.awt.*;
import java.io.Serializable;

public class EditEdgeFormParam extends Param implements Serializable {

    private int edgeId;
    private int sourceVertexId;
    private String sourceVertexAnnotation;
    private int targetVertexId;
    private String targetVertexAnnotation;
    private Stroke oldStroke;
    private Stroke newStroke;

    public EditEdgeFormParam(int edgeId, int sourceVertexId, String sourceVertexAnnotation, int targetVertexId, String targetVertexAnnotation, Stroke oldStroke, Stroke newStroke) {
        this.edgeId = edgeId;
        this.sourceVertexId = sourceVertexId;
        this.sourceVertexAnnotation = sourceVertexAnnotation;
        this.targetVertexId = targetVertexId;
        this.targetVertexAnnotation = targetVertexAnnotation;
        this.oldStroke = oldStroke;
        this.newStroke = newStroke;
    }
}
