package log_management.parameters.edit;

import graph.graph.StrokeType;
import javafx.util.Pair;
import log_management.parameters.Param;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Parameterobject of the action EditEdgesStrokeLogAction.
 */
public class EditEdgesStrokeParam extends Param{
    /**
     * The list of edges getting a new stroke.
     */
    @Getter
    Map<Integer, List<Pair<Integer, String>>> edges;
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
     * Creates a new parameterobject of its own class.
     * @param pEdges The map of all edges.
     * @param pOldStrokes The set of old strokes.
     * @param pNewStroke The new stroke.
     */
    public EditEdgesStrokeParam(Map<Integer, List<Pair<Integer, String>>> pEdges, Map<Integer, StrokeType> pOldStrokes,
                                StrokeType pNewStroke) {
        this.edges = pEdges;
        this.oldStrokes = pOldStrokes;
        this.newStroke = pNewStroke;
    }

    @Override
    public String convertToJson() {
        throw new UnsupportedOperationException();
    }
}
