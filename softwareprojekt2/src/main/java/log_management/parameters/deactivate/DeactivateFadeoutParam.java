package log_management.parameters.deactivate;

import log_management.parameters.Param;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.Serializable;
import java.util.List;

public class DeactivateFadeoutParam extends Param implements Serializable {

    private List<Integer> verticesId;
    private List<Integer> edgesId;

    public DeactivateFadeoutParam(List<Integer> verticesId, List<Integer> edgesId) {
        this.verticesId = verticesId;
        this.edgesId = edgesId;
    }

    @Override
    public String convertToJson() {
        throw new NotImplementedException();
    }
}
