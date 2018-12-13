package log_management.parameters.activate_deactivate;

import log_management.parameters.Param;
import lombok.Getter;

import java.util.List;

/**
 * Parameter object for action ActivateHighlightLogAction/DeactivateHighlightLogAction.
 */
public class ActivateDeactivateHighlightParam extends Param{
    /**
     * List of vertices, which will highlight/stop highlight.
     */
    @Getter
    private List<Integer> vertexIdList;
    /**
     * List of edges, which will highlight/stop highlight.
     */
    @Getter
    private List<Integer> edgeIdList;

    /**
     * Creates an parameterobject of its own class.
     *
     * @param vertexIdList List of selected vertices.
     * @param edgeIdList   List of edges attached to the vertices.
     */
    public ActivateDeactivateHighlightParam(List<Integer> vertexIdList, List<Integer> edgeIdList) {
        this.vertexIdList = vertexIdList;
        this.edgeIdList = edgeIdList;
    }
}
