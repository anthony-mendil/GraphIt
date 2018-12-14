package log_management.parameters.edit;

import javafx.util.Pair;
import log_management.parameters.Param;
import lombok.Getter;

import java.awt.*;
import java.io.Serializable;

/**
 * Parameterobject of the action EditEdgeColor.
 */
public class EditEdgeColorParam extends Param{
    /**
     * The identity of the edge.
     */
    @Getter
    private int edgeId;
    /**
     * The source-vertex id and annotation.
     */
    @Getter
    private Pair<Integer,String> sourceVertex;
    /**
     * The target-vertex id and annotation.
     */
    @Getter
    private Pair<Integer,String> targetVertex;
    /**
     * Old color of the edge.
     */
    @Getter
    private Color oldColor;
    /**
     * New color of the edge.
     */
    @Getter
    private Color newColor;

    /**
     * Creates an parameterobject of its own class.
     * @param pEdgeId The Edge-Id.
     * @param pSourceVertex The source-vertex Id/annotation.
     * @param pTargetVertex The target-vertex Id/annotation.
     * @param pOldColor The old color.
     * @param pNewColor The new color.
     */
    public EditEdgeColorParam(int pEdgeId, Pair<Integer,String> pSourceVertex, Pair<Integer,String> pTargetVertex, Color pOldColor, Color pNewColor) {
        this.edgeId = edgeId;
        this.sourceVertex = pSourceVertex;
        this.targetVertex = pTargetVertex;
        this.oldColor = oldColor;
        this.newColor = newColor;
    }
}
