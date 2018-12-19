package log_management.parameters.move;


import graph.graph.Vertex;
import javafx.util.Pair;
import log_management.parameters.Param;
import lombok.Data;
import lombok.Getter;

import java.awt.geom.Point2D;
import java.util.List;
import java.util.Map;

/**
 * parameter object for LayoutGraphLogAction, changes the position of the vertices
 */
@Data
public class LayoutParam extends Param {
    /**
     * the vertices containing their old position
     */
    @Getter
    private Map<Vertex, Point2D> oldVertices;

    /**
     * map from vertex ids to new position
     */
    @Getter
    private Map<Vertex, Point2D> newVertices;

    /**
     * Creates a parameter object of its own class.
     *
     * @param pOldPosition map of vertices containing their old positions.
     * @param pNewPosition map of vertices containing their new positions.
     */
    public LayoutParam(Map<Vertex, Point2D> pOldPosition, Map<Vertex, Point2D> pNewPosition) {
        this.oldVertices = pOldPosition;
        this.newVertices = pNewPosition;
    }
    @Override
    public String toString() {
        throw new UnsupportedOperationException();
    }
}
