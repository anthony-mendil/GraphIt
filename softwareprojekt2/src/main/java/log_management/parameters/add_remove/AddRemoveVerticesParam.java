package log_management.parameters.add_remove;

import graph.graph.Sphere;
import graph.graph.Vertex;
import log_management.parameters.Param;
import lombok.Data;
import lombok.Getter;

import java.util.Map;

/**
 * Parameter object of the action of AddVerticesLogAction/RemoveVerticesLogAction.
 */
@Data
public class AddRemoveVerticesParam extends Param{
    /**
     * Set of vertices to their sphere.
     */
    @Getter
    private Map<Vertex, Sphere> parameter;

    /**
     * Creates an parameter object of its own class.
     * @param pParameter The set of vertices and their sphere.
     */
    public AddRemoveVerticesParam(Map<Vertex, Sphere> pParameter) {
        parameter = pParameter;
    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException();
    }
}
