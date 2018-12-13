package log_management.parameters.add;

import graph.graph.Edge;
import javafx.util.Pair;
import log_management.parameters.Param;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Parameterobject for the action AddEdgesLogAction.
 */
public class AddEdgesParam extends Param{
    /**
     * Set of edges bound to their edge-type.
     */
    @Getter
    private Map<Pair<Integer,Integer>,Edge> ListOfEdges;
    /**
     * Creates an parameterobject of its own class.
     * @param pListOfEdges List of edges and the edge-type.
     */
    public AddEdgesParam(Map<Pair<Integer,Integer>,Edge> pListOfEdges){
        ListOfEdges = pListOfEdges;
    }

    @Override
    public String convertToJson() {
        return null;
    }
}