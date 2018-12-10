package log_management.parameters.add;

import LogManagement.Parameters.Param;
import graph.graph.Vertex;

import java.io.Serializable;

public class AddVertexParam extends Param implements Serializable {

    private Vertex vertex;
    private int sphereId;
    private String sphereAnnotation;

    public AddVertexParam(Vertex vertex, int sphereId, String sphereAnnotation) {
        this.vertex = vertex;
        this.sphereId = sphereId;
        this.sphereAnnotation = sphereAnnotation;
    }
}
