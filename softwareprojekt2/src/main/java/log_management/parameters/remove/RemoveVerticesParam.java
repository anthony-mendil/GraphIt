package log_management.parameters.remove;

import graph.graph.Vertex;
import javafx.util.Pair;
import log_management.parameters.Param;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class RemoveVerticesParam extends Param implements Serializable {

    private List<Vertex> vertices;
    private List<Integer> sphereIds;
    private List<String> sphereAnnotations;

    public RemoveVerticesParam(Map<Vertex, Pair<Integer, String>> map) {
        this.vertices = vertices;
        this.sphereIds = sphereIds;
        this.sphereAnnotations = sphereAnnotations;
    }

    @Override
    public String convertToJson() {
        return null;
    }
}
