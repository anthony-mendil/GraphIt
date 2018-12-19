package log_management.parameters.edit;
import graph.graph.Edge;
import graph.graph.EdgeArrowType;
import graph.graph.Vertex;
import javafx.util.Pair;
import log_management.parameters.Param;
import lombok.Data;
import lombok.Getter;
import java.util.Map;

/**
 * Parameter object of the action EditEdgesTypeLogAction.
 */
@Data
public class EditEdgesTypeParam extends Param{
    /**
     * The set of all edges containing their old edge-type.
     */
    @Getter
    private Map<Edge,Pair<Vertex,Vertex>> edgesOldEdgeType;
    /**
     * The set of all edges containing their new edge-type.
     */
    @Getter
    private Map<Edge,Pair<Vertex,Vertex>> edgesNewEdgeType;
    //private EdgeArrowType edgeType;

    /**
     * Creates a new parameter object of its own class.
     * @param pOldEdges The set of edges containing their old edge-types.
     * @param pNewEdges The set of edges containing their new edge-types.
     */
    public EditEdgesTypeParam(Map<Edge,Pair<Vertex,Vertex>> pOldEdges, Map<Edge,Pair<Vertex,Vertex>> pNewEdges) {
        this.edgesOldEdgeType = pOldEdges;
        this.edgesNewEdgeType = pNewEdges;
    }
    @Override
    public String toString() {
        throw new UnsupportedOperationException();
    }

}
