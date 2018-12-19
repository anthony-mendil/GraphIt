package graph.graph;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import java.awt.*;

/**
 * An edge of the syndrom-graph. There are 3 type of edges, defined by the three types of relations: extenuating,
 * neutral, reinforcing. These are typified by the arrowhead. All attributes of the edge can be changed, only the id is
 * final.
 */
@Data
@AllArgsConstructor

public class Edge {
    /**
     * The id of the edge.
     */
    @NonNull
    private final int id;
    /**
     * The fill color of the edge/arrow.
     */
    @NonNull
    private Paint paint;
    /**
     * The stroke type of the edge.
     */
    @NonNull
    private StrokeType stroke;
    /**
     * The edge arrow type of the edge.
     */
    @NonNull
    private EdgeArrowType arrowType;
    /**
     * Defines whether the edge has an anchor point or not on its vertex.
     */
    @NonNull
    private boolean hasAnchor;
    /**
     * Defines the degree to which the anchor point is attached to the vertex.
     */
    @NonNull
    private double anchorAngle;
    /**
     * Defines whether a edge is visible or not.
     */
    @NonNull
    private boolean isVisible;
}
