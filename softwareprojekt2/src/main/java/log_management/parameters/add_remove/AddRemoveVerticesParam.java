package log_management.parameters.add_remove;

import graph.graph.Vertex;
import javafx.util.Pair;
import log_management.parameters.Param;
import lombok.Getter;

import java.io.Serializable;
import java.util.Map;

/**
 * Parameterobject of the action of AddVerticesLogAction.
 */
public class AddRemoveVerticesParam extends Param{
    /**
     * Set of vertices to their respective Sphere-Id and Sphere-Annotation.
     */
    @Getter
    private Map<Vertex, Pair<Integer, String>> parameter;

    /**
     * Creates an parameterobject of its own class.
     * @param pParameter The set of vertices and their sphere/sphere-annoatation.
     */
    public AddRemoveVerticesParam(Map<Vertex, Pair<Integer, String>> pParameter) {
        parameter = pParameter;
    }

    @Override
    public String convertToJson() {
        throw new UnsupportedOperationException();
    }
}
