package log_management.parameters.edit;

import graph.graph.Edge;
import graph.graph.StrokeType;
import graph.graph.Vertex;
import javafx.util.Pair;
import log_management.parameters.Param;
import lombok.Getter;
import java.util.Map;

/**
 * Parameter object of the action EditEdgesStrokeLogAction.
 */
public class EditEdgesStrokeParam extends Param{
    /**
     * The set of edges containing their old stoke-type.
     */
    @Getter
    Map<Edge,Pair<Vertex,Vertex>> pEdges;
    /**
     * The set of all edges and their old strokes.
     */
    @Getter
    private StrokeType newStroke;

    /**
     * Creates a new parameter object of its own class.
     * @param pEdges The map of all edges
     * @param pNewStroke The new stroke.
     */
    public EditEdgesStrokeParam(Map<Edge,Pair<Vertex,Vertex>> pEdges,
                                StrokeType pNewStroke) {
        this.pEdges = pEdges;
        this.newStroke = pNewStroke;
    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException();
    }
}
