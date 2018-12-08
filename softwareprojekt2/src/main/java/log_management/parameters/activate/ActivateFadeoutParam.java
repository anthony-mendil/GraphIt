package log_management.parameters.activate;

import LogManagement.Parameters.Param;
import graph.graph.Edge;
import graph.graph.Vertex;

import java.io.Serializable;
import java.util.List;

public class ActivateFadeoutParam extends Param implements Serializable {

    private List<Vertex> vertexList;
    private List<Edge> edgeList;

    public ActivateFadeoutParam(List<Vertex> vertexList, List<Edge> edgeList) {
        this.vertexList = vertexList;
        this.edgeList = edgeList;
    }

}
