package log_management.parameters.edit;

import graph.graph.Edge;
import graph.graph.Vertex;
import javafx.util.Pair;
import log_management.parameters.Param;
import lombok.Data;
import lombok.Getter;

import java.io.Serializable;
import java.util.Map;

/**
 * Parameter object of the action EditEdgesColorLogAction.
 */
@Data
public class EditEdgesColorParam extends Param implements Serializable {
    /**
     * The list of edges containing their old color.
     */
    @Getter
    private Map<Edge,Pair<Vertex,Vertex>> edgesOld;
    /**
     * The list of edges containing their new color.
     */
    @Getter
    private Map<Edge,Pair<Vertex,Vertex>> edgesNew;

    /**
     * Creates an vertices object of its own class.
     * @param pEdgesOld The list of edges and their old color.
     * @param pEdgesNew The list of edges and their new color.
     */
    public EditEdgesColorParam(Map<Edge,Pair<Vertex,Vertex>> pEdgesOld, Map<Edge,Pair<Vertex,Vertex>> pEdgesNew) {
        this.edgesOld = pEdgesOld;
        this.edgesNew = pEdgesNew;
    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException();
    }
}
