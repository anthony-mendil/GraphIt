package log_management.parameters.move;


import graph.graph.Vertex;
import javafx.util.Pair;
import log_management.parameters.Param;
import lombok.Getter;

import java.awt.geom.Point2D;
import java.util.List;
import java.util.Map;

/**
 * parameter object for LayoutGraphLogAction, changes the position of the vertices
 */
public class LayoutParam extends Param {
    /**
     * the vertices containing their old position
     */
    @Getter
    private List<Vertex>  vertices;

    /**
     * map from vertex ids to new position
     */
    @Getter
    private Map<Integer, Point2D> newPosition;

    /**
     * Creates a parameter object of its own class.
     *
     * @param pVertices list of vertices containing their old position
     * @param pNewPosition map from vertex ids to new position
     */
    public LayoutParam(List<Vertex> pVertices, Map<Integer, Point2D> pNewPosition) {
        this.vertices = pVertices;
        this.newPosition = pNewPosition;
    }
    @Override
    public String toString() {
        throw new UnsupportedOperationException();
    }
}
