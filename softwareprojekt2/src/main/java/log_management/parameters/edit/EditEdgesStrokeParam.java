package log_management.parameters.edit;

import graph.graph.Edge;
import graph.graph.StrokeType;
import graph.graph.Vertex;
import javafx.util.Pair;
import log_management.parameters.Param;
import lombok.Data;
import lombok.Getter;
import java.util.Map;

/**
 * Parameter object of the action EditEdgesStrokeLogAction.
 */
@Data
public class EditEdgesStrokeParam extends Param{
    /**
     * The set of edges containing their old stoke-type.
     */
    @Getter
    Map<Edge,Pair<Vertex,Vertex>> edgesOld;
    /**
     * The set of edges containing their old stoke-type.
     */
    @Getter
    Map<Edge,Pair<Vertex,Vertex>> edgesNew;

    /**
     * Creates a new parameter object of its own class.
     * @param pEdgesOld The map of all edges and their old info.
     * @param pEdgesNew The map of all edges and their new info.
     */
    public EditEdgesStrokeParam(Map<Edge,Pair<Vertex,Vertex>> pEdgesOld, Map<Edge,Pair<Vertex,Vertex>> pEdgesNew
                                ) {
        this.edgesOld = pEdgesOld;
        this.edgesNew = pEdgesNew;
    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException();
    }
}
