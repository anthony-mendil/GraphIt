package log_management.parameters.deactivate;

import LogManagement.Parameters.Param;

import java.io.Serializable;
import java.util.List;

public class DeactivateFadeoutParam extends Param implements Serializable {

    private List<Integer> verticesId;
    private List<Integer> edgesId;

    public DeactivateFadeoutParam(List<Integer> verticesId, List<Integer> edgesId) {
        this.verticesId = verticesId;
        this.edgesId = edgesId;
    }
}
