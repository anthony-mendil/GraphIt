package log_management.parameters.remove;

import graph.graph.Edge;
import log_management.parameters.Param;
import lombok.Getter;

import java.util.List;

/**
 * Parameterobject of the action RemoveAnchorPointsLogAction.
 */
public class RemoveAnchorPointsParam extends Param {
    /**
     * The selected edges.
     */
    @Getter
    private List<Edge> edges;

    /**
     * Creates a parameterobject of its own class.
     * @param pEdges The selected edges.
     */
    public RemoveAnchorPointsParam(List<Edge> pEdges) {
    this.edges = pEdges;
    }

    @Override
    public String convertToJson() {
        return null;
    }
}
