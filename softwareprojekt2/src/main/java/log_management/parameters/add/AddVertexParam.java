package log_management.parameters.add;

import graph.graph.Vertex;
import log_management.parameters.Param;

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


    @Override
    public String convertToJson() {
        throw new UnsupportedOperationException();
    }
}
