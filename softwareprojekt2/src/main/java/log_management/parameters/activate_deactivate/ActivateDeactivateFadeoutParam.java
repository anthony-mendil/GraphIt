package log_management.parameters.activate_deactivate;

import log_management.parameters.Param;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;
/**
 * Parameterobject of the action ActivateFadeoutLogAction andDeactivateFadeoutLogAction.
 */
public class ActivateDeactivateFadeoutParam extends Param{
    /**
     * List of vertices to activate/cancel the highlight-option.
     */
    @Getter
    private List<Integer> verticesId;
    /**
     * List of edges to activate/cancel the highlight-option.
     */
    @Getter
    private List<Integer> edgesId;

    /**
     * Creates an paramterobject of its own class.
     * @param pVerticesId The list of vertices to work on.
     * @param pEdgesId The list of edges to work on.
     */
    public ActivateDeactivateFadeoutParam(List<Integer> pVerticesId, List<Integer> pEdgesId) {
        this.verticesId = verticesId;
        this.edgesId = edgesId;
    }

}
