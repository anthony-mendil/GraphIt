package log_management.parameters.edit;

import graph.graph.EdgeArrowType;
import javafx.util.Pair;
import log_management.parameters.Param;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class EditEdgesTypeParam extends Param {

    private Map<Integer, List<Pair<Integer, String>>> edges;
    private EdgeArrowType edgeType;


    public EditEdgesTypeParam(Map<Integer, List<Pair<Integer, String>>> edges, EdgeArrowType edgeType) {
        this.edges = edges;
        this.edgeType = edgeType;
    }

    @Override
    public String convertToJson() {
        throw new UnsupportedOperationException();
    }
}
