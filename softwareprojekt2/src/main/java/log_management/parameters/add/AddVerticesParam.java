package log_management.parameters.add;

import LogManagement.Parameters.Param;
import graph.graph.Vertex;

import java.io.Serializable;
import java.util.List;

public class AddVerticesParam extends Param implements Serializable {

    private List<Vertex> vertices;
    private List<Integer> spheres;
    private List<String> sphereAnnotations;

    public AddVerticesParam(List<Vertex> vertices, List<Integer> spheres, List<String> sphereAnnotations) {
        this.vertices = vertices;
        this.spheres = spheres;
        this.sphereAnnotations = sphereAnnotations;
    }
}
