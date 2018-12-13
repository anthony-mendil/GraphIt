package log_management.parameters.remove;

import graph.graph.Vertex;
import javafx.util.Pair;
import log_management.parameters.Param;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Parameterobject of the action RemoveVerticesLogAction.
 */
public class RemoveVerticesParam extends Param{
    /**
     * The set of vertices, their respective sphere and annotation that they belongs to.
     */
    private Map<Vertex, Pair<Integer,String>> vertices;
    public RemoveVerticesParam(Map<Vertex, Pair<Integer, String>> pVertices) {
        this.vertices = pVertices;

    }

    @Override
    public String convertToJson() {
        return null;
    }
}
