package log_management.parameters.deactivate;

import log_management.parameters.Param;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;
/**
 * Parameterobject of the action DeactivateFadeoutLogAction.
 */
public class DeactivateFadeoutParam extends Param{
    /**
     * List of vertices to cancel the highlight-option.
     */
    @Getter
    private List<Integer> verticesId;
    /**
     * List of edges to cancel the highlight-option.
     */
    @Getter
    private List<Integer> edgesId;

    /**
     * Creates an paramterobject of its own class.
     * @param pVerticesId The list of vertices to work on.
     * @param pEdgesId The list of edges to work on.
     */
    public DeactivateFadeoutParam(List<Integer> pVerticesId, List<Integer> pEdgesId) {
        this.verticesId = verticesId;
        this.edgesId = edgesId;
    }

    @Override
    public String convertToJson() {
        throw new UnsupportedOperationException();
    }
}
