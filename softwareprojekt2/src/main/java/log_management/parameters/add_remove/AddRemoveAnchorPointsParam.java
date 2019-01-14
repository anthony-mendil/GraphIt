package log_management.parameters.add_remove;

import graph.graph.Edge;
import log_management.parameters.Param;
import lombok.Data;
import lombok.Getter;

import java.util.List;

/**
 * Parameter object of the action AddAnchorPointsLogAction/RemoveAnchorPointsLogAction.
 */
@Data
public class AddRemoveAnchorPointsParam extends Param {
    /**
     * The selected edges.
     */
    @Getter
    private List<Edge> edges;

    /**
     * Creates a parameter object of its own class.
     * @param pEdges The selected edges.
     */
    public AddRemoveAnchorPointsParam(List<Edge> pEdges) {
    this.edges = pEdges;
    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException();
    }
}
