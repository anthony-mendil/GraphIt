package log_management.parameters.remove;

import LogManagement.Parameters.Param;
import graph.graph.Edge;

import java.io.Serializable;

public class RemoveEdgeParam extends Param implements Serializable {

    private Edge edge;
    private int sourceVertexId;
    private String sourceVertexAnnotation;
    private int targetVertexId;
    private String targetVertexAnnotation;

    public RemoveEdgeParam(Edge edge, int sourceVertexId, String sourceVertexAnnotation, int targetVertexId, String targetVertexAnnotation) {
        this.edge = edge;
        this.sourceVertexId = sourceVertexId;
        this.sourceVertexAnnotation = sourceVertexAnnotation;
        this.targetVertexId = targetVertexId;
        this.targetVertexAnnotation = targetVertexAnnotation;
    }
}
