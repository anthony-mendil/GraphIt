package log_management.parameters.edit;

import edu.uci.ics.jung.graph.util.Pair;
import graph.graph.Edge;
import graph.graph.EdgeArrowType;
import graph.graph.Vertex;
import log_management.parameters.Param;
import lombok.Getter;
import java.util.Map;

/**
 * Parameter object of the action EditEdgesTypeLogAction.
 */
public class EditEdgesTypeParam extends Param{
    /**
     * The set of all edges containing their old edge-type.
     */
    @Getter
    Map<Edge,Pair<Vertex>> edgesOldEdgeType;
    /**
     * The new edge-type.
     */
    @Getter
    private EdgeArrowType edgeType;

    /**
     * Creates a new parameter object of its own class.
     * @param pEdges The set of edges containing their old edge-types.
     * @param pEdgeType The new edge-type.
     */
    public EditEdgesTypeParam(Map<Edge,Pair<Vertex>> pEdges, EdgeArrowType pEdgeType) {
        this.edgesOldEdgeType = pEdges;
        this.edgeType = pEdgeType;
    }
    @Override
    public String toString() {
        throw new UnsupportedOperationException();
    }
}
