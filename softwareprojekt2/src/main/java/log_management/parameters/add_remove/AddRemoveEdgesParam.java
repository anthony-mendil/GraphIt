package log_management.parameters.add_remove;

import graph.graph.Edge;
import javafx.util.Pair;
import log_management.parameters.Param;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Parameterobject for the action AddEdgesLogAction/RemoveEdgesLogAction.
 */
public class AddRemoveEdgesParam extends Param{
    /**
     * The set of edges bound to their edge-type.
     */
    @Getter
    private Map<Pair<Integer,Integer>,Edge> edges;
    /**
     * Creates an parameterobject of its own class.
     * @param pListOfEdges List of edges and the edge-type.
     */
    public AddRemoveEdgesParam(Map<Pair<Integer,Integer>,Edge> pListOfEdges){
        this.edges = pListOfEdges;
    }

    @Override
    public String convertToJson() {
        return null;
    }
}