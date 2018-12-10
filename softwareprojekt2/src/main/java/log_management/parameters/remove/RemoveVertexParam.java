package log_management.parameters.remove;

import graph.graph.Vertex;
import log_management.parameters.Param;

import java.io.Serializable;

public class RemoveVertexParam extends Param implements Serializable {

    private Vertex vertex;
    private int sphereId;
    private String sphereAnnotation;

    public RemoveVertexParam(Vertex vertex, int sphereId, String sphereAnnotation) {
        this.vertex = vertex;
        this.sphereId = sphereId;
        this.sphereAnnotation = sphereAnnotation;
    }
}
