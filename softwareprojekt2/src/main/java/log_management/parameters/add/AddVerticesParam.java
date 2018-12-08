package log_management.parameters.add;

import LogManagement.Parameters.Param;
import graph.graph.Sphere;
import graph.graph.Vertex;

import java.io.Serializable;
import java.util.List;

public class AddVerticesParam extends Param implements Serializable {

    private List<Vertex> vertexList;
    private List<Sphere> sphereList;

    public AddVerticesParam(List<Vertex> vertexList, List<Sphere> sphereList) {
        this.vertexList = vertexList;
        this.sphereList = sphereList;
    }
}
