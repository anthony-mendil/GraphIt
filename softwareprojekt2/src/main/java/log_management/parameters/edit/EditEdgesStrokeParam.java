package log_management.parameters.edit;

import graph.graph.StrokeType;
import javafx.util.Pair;
import log_management.parameters.Param;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Parameter object of the action EditEdgesStrokeLogAction.
 */
public class EditEdgesStrokeParam extends Param{
    /**
     * The list of edges getting a new stroke
     */
    @Getter
    List<Integer> edges;
    /**
     * The set of all edges and their old strokes.
     */
    @Getter
    private Map<Integer, StrokeType> oldStrokes;
    /**
     * The new stroke-type.
     */
    @Getter
    private StrokeType newStroke;

    /**
     * Creates a new parameter object of its own class.
     * @param pEdges The map of all edges.
     * @param pOldStrokes The set of old strokes.
     * @param pNewStroke The new stroke.
     */
    public EditEdgesStrokeParam(List<Integer> pEdges, Map<Integer, StrokeType> pOldStrokes,
                                StrokeType pNewStroke) {
        this.edges = pEdges;
        this.oldStrokes = pOldStrokes;
        this.newStroke = pNewStroke;
    }

}
