package log_management.parameters.add;

import graph.graph.Edge;
import log_management.parameters.Param;

import java.io.Serializable;

public class AddEdgesParam extends Param {

    private int sourceVertexId;
    private int targetVertexId;
    private Edge edge;

    public AddEdgesParam(int sourceVertexId, int targetVertexId, Edge edge) {
        this.sourceVertexId = sourceVertexId;
        this.targetVertexId = targetVertexId;
        this.edge = edge;
    }

    @Override
    public String convertToJson() {
        return null;
    }
}