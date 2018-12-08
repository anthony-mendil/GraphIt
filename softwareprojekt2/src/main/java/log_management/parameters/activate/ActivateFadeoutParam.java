package log_management.parameters.activate;

import LogManagement.Parameters.Param;

import java.io.Serializable;
import java.util.List;

public class ActivateFadeoutParam extends Param implements Serializable {

    private List<Integer> vertexIdList;
    private List<Integer> edgeIdList;

    public ActivateFadeoutParam(List<Integer> vertexIdList, List<Integer> edgeIdList) {
        this.vertexIdList = vertexIdList;
        this.edgeIdList = edgeIdList;
    }
}
