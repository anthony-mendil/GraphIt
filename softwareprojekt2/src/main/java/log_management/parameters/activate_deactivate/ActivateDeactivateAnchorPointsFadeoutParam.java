package log_management.parameters.activate_deactivate;

import graph.graph.Edge;
import log_management.parameters.Param;
import lombok.Getter;

import java.util.List;

/**
 * Parameterobject for action ActivateAnchotPointsFadeoutLogAction.
 */
public class ActivateDeactivateAnchorPointsFadeoutParam extends Param{

    /**
     * Set of edges, which anchor-points will (cancel)fadeout.
     */
    @Getter
    private List<Edge> edges;

    /**
     * Creates a parameterobject of its own class.
     * @param pEdges The list of edges.
     */
    public ActivateDeactivateAnchorPointsFadeoutParam(List<Edge> pEdges) {
        this.edges = pEdges;
    }

    @Override
    public String convertToJson() {
        return null;
    }
}
