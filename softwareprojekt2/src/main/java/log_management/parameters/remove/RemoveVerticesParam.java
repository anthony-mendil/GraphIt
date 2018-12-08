package log_management.parameters.remove;

import LogManagement.Parameters.Param;
import graph.graph.Vertex;

import java.io.Serializable;
import java.util.List;

public class RemoveVerticesParam extends Param implements Serializable {

    private List<Vertex> vertices;
    private List<Integer> sphereIds;
    private List<String> sphereAnnotations;

    public RemoveVerticesParam(List<Vertex> vertices, List<Integer> sphereIds, List<String> sphereAnnotations) {
        this.vertices = vertices;
        this.sphereIds = sphereIds;
        this.sphereAnnotations = sphereAnnotations;
        //hi
    }
}
