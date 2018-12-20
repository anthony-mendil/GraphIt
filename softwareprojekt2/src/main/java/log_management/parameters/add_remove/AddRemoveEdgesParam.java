package log_management.parameters.add_remove;

import graph.graph.Edge;
import graph.graph.Vertex;
import javafx.util.Pair;
import log_management.parameters.Param;
import lombok.Data;
import lombok.Getter;
import java.util.Map;

/**
 * Parameter object for the action AddEdgesLogAction/RemoveEdgesLogAction.
 */
@Data
public class AddRemoveEdgesParam extends Param{
    /**
     * The set of edges(pair describes the respective start-vertex and sink-vertex) bound to their edge-type.
     */
    @Getter
    private Map<Pair<Vertex,Vertex>,Edge> edges;
    /**
     * Creates an parameter object of its own class.
     * @param edges List of edges and their start/end vertex id.
     */
    public AddRemoveEdgesParam(Map<Pair<Vertex,Vertex>,Edge> edges){
        this.edges = edges;
    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException();
    }
}