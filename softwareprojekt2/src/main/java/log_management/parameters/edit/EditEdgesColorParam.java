package log_management.parameters.edit;

import log_management.parameters.Param;
import lombok.Getter;

import java.awt.*;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Parameterobject of the action EditEdgesColorLogAction.
 */
public class EditEdgesColorParam extends Param{
    /**
     * The list of edges to change the color.
     */
    @Getter
    private List<Integer> edgesId;
    /**
     * The set of source-vertices and their annotation.
     */
    @Getter
    private Map<Integer, String> sourceVertices;
    /**
     * The set of target-vertices and their annotation.
     */
    @Getter
    private Map<Integer, String> targetVertices;
    /**
     * The map of colors the old edges had.
     */
    @Getter
    private Map<Integer,Color> oldColors;
    /**
     * The new color the edges should have.
     */
    @Getter
    private Color newColor;

    /**
     * Creates an parameterobject of its own class.
     * @param pEdgesId The list of edges.
     * @param pSourceVertices The list of start-vertices and their annotation.
     * @param pTargetVertices The list of target-vertices and their annotation.
     * @param pOldColors The map of edges and their old colors.
     * @param pNewColor The new color.
     */
    public EditEdgesColorParam(List<Integer> pEdgesId, Map<Integer, String> pSourceVertices, Map<Integer, String> pTargetVertices, Map<Integer,Color> pOldColors, Color pNewColor) {
        this.edgesId = pEdgesId;
        this.sourceVertices = pSourceVertices;
        this.targetVertices = pTargetVertices;
        this.oldColors = pOldColors;
        this.newColor = pNewColor;
    }

}
