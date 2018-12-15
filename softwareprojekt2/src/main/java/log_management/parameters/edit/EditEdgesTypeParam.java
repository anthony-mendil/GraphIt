package log_management.parameters.edit;

import graph.graph.EdgeArrowType;
import javafx.util.Pair;
import log_management.parameters.Param;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Parameter object of the action EditEdgesTypeLogAction.
 */
public class EditEdgesTypeParam extends Param{
    /**
     * The set of all edges which will get a new type.
     */
    @Getter
    private List<Integer> edges;
    /**
     * The new edge-type.
     */
    @Getter
    private EdgeArrowType edgeType;

    /**
     * Creates a new parameter object of its own class.
     * @param edges The set of edges.
     * @param edgeType The new edges.
     */
    public EditEdgesTypeParam(List<Integer> edges, EdgeArrowType edgeType) {
        this.edges = edges;
        this.edgeType = edgeType;
    }

}
