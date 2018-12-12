package log_management.parameters.deactivate;

import log_management.parameters.Param;

import java.io.Serializable;
import java.util.List;

public class DeactivateHighlightParam extends Param implements Serializable {

    private List<Integer> verticesId;
    private List<Integer> edgesId;

    public DeactivateHighlightParam(List<Integer> verticesId, List<Integer> edgesId) {
        this.verticesId = verticesId;
        this.edgesId = edgesId;
    }

    @Override
    public String convertToJson() {
        throw new UnsupportedOperationException();
    }
}
