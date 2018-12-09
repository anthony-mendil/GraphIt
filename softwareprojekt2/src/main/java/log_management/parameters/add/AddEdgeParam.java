package log_management.parameters.add;

import LogManagement.Parameters.Param;
import graph.graph.Edge;

import java.io.Serializable;

public class AddEdgeParam extends Param implements Serializable {

    private int sourceVertexId;
    private int targetVertexId;
    private Edge edge;

    public AddEdgeParam(int sourceVertexId, int targetVertexId, Edge edge) {
        this.sourceVertexId = sourceVertexId;
        this.targetVertexId = targetVertexId;
        this.edge = edge;
    }
}
