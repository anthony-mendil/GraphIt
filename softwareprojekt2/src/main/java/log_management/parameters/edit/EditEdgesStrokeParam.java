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
     * The set of edges and their old stoke-type.
     */
    @Getter
    Map<Edge,Pair<Pair<Vertex,Vertex>,StrokeType>> edgesOldStroke;
    /**
     * The set of all edges and their old strokes.
     */
    @Getter
    private StrokeType newStroke;

    /**
     * Creates a new parameterobject of its own class.
     * @param pEdgesOldStroke The map of all edges and their old stokes.
     * @param pNewStroke The new stroke.
     */
    public EditEdgesStrokeParam(Map<Edge,Pair<Pair<Vertex,Vertex>, StrokeType>> pEdgesOldStroke,
                                StrokeType pNewStroke) {
        this.edgesOldStroke = pEdgesOldStroke;
        this.newStroke = pNewStroke;
    }

}
