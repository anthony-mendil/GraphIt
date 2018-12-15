package log_management.parameters.add_remove;

import graph.graph.Edge;
import log_management.parameters.Param;
import lombok.Getter;
import java.util.Set;

/**
 * Parameter object of the action AddAnchorPointsLogAction/RemoveAnchorPointsLogAction.
 */
public class AddRemoveAnchorPointsParam extends Param {
    /**
     * The selected edges.
     */
    @Getter
    private Set<Edge> edges;

    /**
     * Creates a parameter object of its own class.
     * @param pEdges The selected edges.
     */
    public AddRemoveAnchorPointsParam(Set<Edge> pEdges) {
    this.edges = pEdges;
    }
}
