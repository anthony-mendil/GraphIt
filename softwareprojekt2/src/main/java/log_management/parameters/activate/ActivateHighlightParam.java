package log_management.parameters.activate;

import log_management.parameters.Param;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.Serializable;
import java.util.List;

public class ActivateHighlightParam extends Param implements Serializable {

    private List<Integer> vertexIdList;
    private List<Integer> edgeIdList;

    public ActivateHighlightParam(List<Integer> vertexIdList, List<Integer> edgeIdList) {
        this.vertexIdList = vertexIdList;
        this.edgeIdList = edgeIdList;
    }

    @Override
    public String convertToJson() {
        throw new NotImplementedException();
    }
}
