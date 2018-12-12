package log_management.parameters.remove;

import graph.graph.Edge;
import javafx.util.Pair;
import log_management.parameters.Param;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class RemovesEdgesParam extends Param implements Serializable {

    private Map<Edge, List<Pair<Integer, String>>> edges;

    public RemovesEdgesParam(Map<Edge, List<Pair<Integer, String>>> edges) {
        this.edges = edges;
    }

    @Override
    public String convertToJson() {
        throw new UnsupportedOperationException();
    }
}
