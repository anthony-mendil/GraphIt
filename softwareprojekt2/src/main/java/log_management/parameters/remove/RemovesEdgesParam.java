package log_management.parameters.remove;

import graph.graph.Edge;
import javafx.util.Pair;
import log_management.parameters.Param;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Parameterobject for the action RemoveEdgesLogAction.
 */
public class RemovesEdgesParam extends Param{
    /**
     * The selected edges between the vertices.
     */
    @Getter
    private Map<Pair<Integer,Integer>,Edge> edges;

    /**
     * Creates a parameterobject of its own class.
     * @param edges the selected edges.
     */
    public RemovesEdgesParam(Map<Pair<Integer, Integer>,Edge> edges) {
        this.edges = edges;
    }

    @Override
    public String convertToJson() {
        throw new UnsupportedOperationException();
    }
}
