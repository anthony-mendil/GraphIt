package log_management.parameters.activate_deactivate;

import graph.graph.Edge;
import graph.graph.Vertex;
import log_management.parameters.Param;
import lombok.Getter;

import java.util.List;

/**
 * Parameter object for action ActivateHighlightLogAction/DeactivateHighlightLogAction.
 */
public class ActivateDeactivateHighlightParam extends Param{
    /**
     * List of vertices, which will highlight/stop highlight.
     */
    @Getter
    private List<Vertex> vertices;
    /**
     * List of edges, which will highlight/stop highlight.
     */
    @Getter
    private List<Edge> edges;

    /**
     * Creates an parameter object of its own class.
     *
     * @param vertices List of selected vertices.
     * @param edges   List of edges attached to the vertices.
     */
    public ActivateDeactivateHighlightParam(List<Vertex> vertices, List<Edge> edges) {
        this.vertices = vertices;
        this.edges = edges;
    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException();
    }
}
