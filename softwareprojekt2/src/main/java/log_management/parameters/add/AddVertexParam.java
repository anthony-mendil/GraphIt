package log_management.parameters.add;

import LogManagement.Parameters.Param;
import graph.graph.Sphere;
import graph.graph.Vertex;

import java.io.Serializable;

public class AddVertexParam extends Param implements Serializable {

    private Vertex vertex;
    private Sphere sphere;

    public AddVertexParam(Vertex vertex, Sphere sphere) {
        this.vertex = vertex;
        this.sphere = sphere;
    }
}
