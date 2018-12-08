package log_management.parameters.add;

import LogManagement.Parameters.Param;
import graph.graph.Edge;
import graph.graph.Vertex;

import java.io.Serializable;

public class AddEdgeParam extends Param implements Serializable {

    private Vertex sourceVertex;
    private Vertex targetVertex;
    private Edge edge;

    public AddEdgeParam(Vertex sourceVertex, Vertex targetVertex, Edge edge) {
        this.sourceVertex = sourceVertex;
        this.targetVertex = targetVertex;
        this.edge = edge;
    }
}
