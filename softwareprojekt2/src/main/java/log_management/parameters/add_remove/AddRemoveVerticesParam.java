package log_management.parameters.add_remove;

import graph.graph.Vertex;
import javafx.util.Pair;
import log_management.parameters.Param;
import lombok.Getter;

import java.util.Map;

/**
 * Parameter object of the action of AddVerticesLogAction.
 */
public class AddRemoveVerticesParam extends Param{
    /**
     * Set of vertices to their respective Sphere-Id and Sphere-Annotation.
     */
    @Getter
    private Map<Vertex, Pair<Integer, String>> parameter;

    /**
     * Creates an parameter object of its own class.
     *
     * @param pParameter The set of vertices and their sphere/sphere-annotation.
     */
    public AddRemoveVerticesParam(Map<Vertex, Pair<Integer, String>> pParameter) {
        parameter = pParameter;
    }

}
