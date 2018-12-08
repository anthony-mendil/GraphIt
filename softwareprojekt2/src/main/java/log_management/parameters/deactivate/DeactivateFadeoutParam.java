package log_management.parameters.deactivate;

import LogManagement.Parameters.Param;
import graph.graph.Edge;
import graph.graph.Vertex;

import java.io.Serializable;
import java.util.List;

public class DeactivateFadeoutParam extends Param implements Serializable {

    private List<Vertex> vertexList;
    private List<Edge> edgeList;

    public DeactivateFadeoutParam(List<Vertex> vertexList, List<Edge> edgeList) {
        this.vertexList = vertexList;
        this.edgeList = edgeList;
    }

}
