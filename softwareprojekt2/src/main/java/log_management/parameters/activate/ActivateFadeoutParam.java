package log_management.parameters.activate;

import log_management.parameters.Param;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;

/**
 * Parameterobject for action ActivateFadeoutLogAction.
 */
public class ActivateFadeoutParam extends Param{
    /**
     * List of vertices, which will fadeout.
     */
    @Getter
    private List<Integer> vertexIdList;
    /**
     * List of edges, which will fadeout.
     */
    @Getter
    private List<Integer> edgeIdList;

    /**
     * Creates an Parameterobjct of its class.
     * @param vertexIdList List of selected vertices.
     * @param edgeIdList List of edges attached to the vertices.
     */
    public ActivateFadeoutParam(List<Integer> vertexIdList, List<Integer> edgeIdList) {
        this.vertexIdList = vertexIdList;
        this.edgeIdList = edgeIdList;
    }

    @Override
    public String convertToJson() {
        throw new UnsupportedOperationException();
    }
}
