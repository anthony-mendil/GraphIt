package log_management.parameters.move;


import graph.graph.Vertex;
import log_management.parameters.Param;
import lombok.Data;
import lombok.Getter;

import java.awt.geom.Point2D;
import java.io.Serializable;
import java.util.Map;

/**
 * Parameter object for LayoutSphereGraphLogAction, changes the position of the vertices.
 */
@Data
public class LayoutVerticesParam extends Param implements Serializable {
    /**
     * The vertices containing their old position.
     */
    @Getter
    private Map<Vertex, Point2D> oldVertices;

    /**
     * The map from vertex ids to the new position.
     */
    @Getter
    private Map<Vertex, Point2D> newVertices;

    /**
     * Creates a parameter object of its own class.
     *
     * @param pOldPosition Map of vertices containing their old positions.
     * @param pNewPosition Map of vertices containing their new positions.
     */
    public LayoutVerticesParam(Map<Vertex, Point2D> pOldPosition, Map<Vertex, Point2D> pNewPosition) {
        this.oldVertices = pOldPosition;
        this.newVertices = pNewPosition;
    }
    @Override
    public String toString() {
        throw new UnsupportedOperationException();
    }
}
