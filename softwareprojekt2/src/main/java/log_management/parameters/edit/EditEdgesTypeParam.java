package log_management.parameters.edit;

import graph.graph.Edge;
import graph.graph.EdgeArrowType;
import graph.graph.Vertex;
import javafx.util.Pair;
import log_management.parameters.Param;
import lombok.Getter;
import java.util.Map;

/**
 * Parameter object of the action EditEdgesTypeLogAction.
 */
public class EditEdgesTypeParam extends Param{
    /**
     * The set of all edges and their old edge-type.
     */
    @Getter
    Map<Edge,Pair<Pair<Vertex,Vertex>,EdgeArrowType>> edgesOldEdgeType;
    /**
     * The new edge-type.
     */
    @Getter
    private EdgeArrowType edgeType;

    /**
     * Creates a new parameterobject of its own class.
     * @param pEdgesOldEdgeType The set of edges and their old edge-types.
     * @param pEdgeType The new edge-type.
     */
    public EditEdgesTypeParam(Map<Edge,Pair<Pair<Vertex,Vertex>,EdgeArrowType>> pEdgesOldEdgeType, EdgeArrowType pEdgeType) {
        this.edgesOldEdgeType = pEdgesOldEdgeType;
        this.edgeType = pEdgeType;
    }

}
