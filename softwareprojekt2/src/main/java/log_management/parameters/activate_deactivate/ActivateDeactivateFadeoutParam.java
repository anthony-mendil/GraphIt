package log_management.parameters.activate_deactivate;

import graph.graph.Edge;
import graph.graph.Vertex;
import log_management.parameters.Param;
import lombok.Data;
import lombok.Getter;

import java.util.List;
/**
 * Parameter object of the action ActivateFadeoutLogAction/DeactivateFadeoutLogAction.
 */
@Data
public class ActivateDeactivateFadeoutParam extends Param{
    /**
     * List of vertices to activate/cancel the highlight-option.
     */
    @Getter
    private List<Vertex> vertices;
    /**
     * List of edges to activate/cancel the highlight-option.
     */
    @Getter
    private List<Edge> edges;

    /**
     * Creates an parameter object of its own class.
     * @param pVertices The list of vertices to work on.
     * @param pEdges The list of edges to work on.
     */
    public ActivateDeactivateFadeoutParam(List<Vertex> pVertices, List<Edge> pEdges) {
        this.vertices = vertices;
        this.edges = edges;
    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException();
    }
}
