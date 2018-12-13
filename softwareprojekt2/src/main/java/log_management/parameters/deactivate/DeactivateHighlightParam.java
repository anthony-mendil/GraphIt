package log_management.parameters.deactivate;

import log_management.parameters.Param;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;

/**
 * Parameterobject of the action DeactivateHighlightLogAction.
 */
public class DeactivateHighlightParam extends Param{
    /**
     * List of vertices to deactivate the highlight-option on.
     */
    @Getter
    private List<Integer> verticesId;
    /**
     *
     * List of edges to deactivate the highlight-option on.
     *
     */
    @Getter
    private List<Integer> edgesId;

    /**
     * Creates an parameterobject of its own class.
     * @param pVerticesId
     * @param pEdgesId
     */
    public DeactivateHighlightParam(List<Integer> pVerticesId, List<Integer> pEdgesId) {
        this.verticesId = pVerticesId;
        this.edgesId = pEdgesId;
    }

    @Override
    public String convertToJson() {
        throw new UnsupportedOperationException();
    }
}
