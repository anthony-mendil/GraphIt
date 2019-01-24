package log_management.parameters.add_remove;

import graph.graph.Vertex;
import log_management.parameters.Param;
import lombok.Data;
import lombok.Getter;

import java.io.Serializable;
import java.util.Map;

/**
 * Parameter object for the action AddEdgesLogAction/RemoveEdgesLogAction.
 */
@Data
public class AddRemoveEdgesParam extends Param implements Serializable {
    /**
     * The set of edges(pair describes the respective start-vertex and sink-vertex) bound to their edge-type.
     */
    @Getter
    private Map<Vertex,Vertex> edges;
    /**
     * Creates an parameter object of its own class.
     * @param pEdges List of edges and their start/end vertex id.
     */
    public AddRemoveEdgesParam(Map<Vertex,Vertex> pEdges){
        this.edges = pEdges;
    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException();
    }
}