package graph.graph;

import lombok.Data;
import lombok.NonNull;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * An edge of the syndrom-graph. There are 3 type of edges, defined by the three types of relations: extenuating,
 * neutral, reinforcing. These are typified by the arrowhead. All attributes of the edge can be changed, only the id is
 * final.
 */
@Data

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

    private Point2D anchorPoint;

    /**
     * Defines whether a edge is visible or not.
     */
    @NonNull
    private boolean isVisible;

    public Edge(int id, Paint paint, StrokeType stroke, EdgeArrowType arrowType, boolean hasAnchor, boolean isVisible){
        this.id = id;
        this.paint = paint;
        this.stroke = stroke;
        this.arrowType = arrowType;
        this.hasAnchor = hasAnchor;
        this.isVisible = isVisible;
    }

    public boolean equals(Edge e){
        return this.id == e.id;
    }
}
