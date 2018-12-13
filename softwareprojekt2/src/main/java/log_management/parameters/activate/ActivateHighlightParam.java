package log_management.parameters.activate;

import log_management.parameters.Param;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;

/**
 * Parameterobject for action ActivateHighlightLogAction.
 */
public class ActivateHighlightParam extends Param{
    /**
     * List of vertices, which will highlight.
     */
    @Getter
    private List<Integer> vertexIdList;
    /**
     * List of edges, which will highlight.
     */
    @Getter
    private List<Integer> edgeIdList;

    /**
     * Creates an parameterobject of its own class.
     * @param vertexIdList List of selected vertices.
     * @param edgeIdList List of edges attached to the vertices.
     */
    public ActivateHighlightParam(List<Integer> vertexIdList, List<Integer> edgeIdList) {
        this.vertexIdList = vertexIdList;
        this.edgeIdList = edgeIdList;
    }

    @Override
    public String convertToJson() {
        throw new UnsupportedOperationException();
    }
}
