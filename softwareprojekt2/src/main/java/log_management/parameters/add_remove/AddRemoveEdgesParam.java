package log_management.parameters.add_remove;

import graph.graph.Edge;
import javafx.util.Pair;
import log_management.parameters.Param;
import lombok.Getter;
import java.util.Map;

/**
 * Parameter object for the action AddEdgesLogAction/RemoveEdgesLogAction.
 */
public class AddRemoveEdgesParam extends Param{
    /**
     * The set of edges(pair describes the repspective start-vertex and sink-vertex) bound to their edge-type.
     */
    @Getter
    private Map<Pair<Integer,Integer>,Edge> edges;
    /**
     * Creates an parameter object of its own class.
     * @param pListOfEdges List of edges and their start/ end vertex id
     */
    public AddRemoveEdgesParam(Map<Pair<Integer,Integer>,Edge> pListOfEdges){
        this.edges = pListOfEdges;
    }

    @Override
    public String convertToJson() {
        return null;
    }
}