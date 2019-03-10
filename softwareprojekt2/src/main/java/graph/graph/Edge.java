package graph.graph;

import javafx.util.Pair;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

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
    private final int id;
    /**
     * The fill color of the edge/arrow.
     */
    @NonNull
    private Color color;
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
     * Defines whether the edge has an anchor point (incoming) or not on its vertex.
     */
    @NonNull
    private boolean hasAnchorIn;
    /**
     * Defines whether the edge has an anchor point (outgoing) or not on its vertex.
     */
    @NonNull
    private boolean hasAnchorOut;
    /**
     * first: outgoing anchor point
     * second: incoming anchor point
     */
    private Pair<Point2D, Point2D> anchorPoints = new Pair<>(null, null);
    /**
     * Defines whether a edge is visible or not.
     */
    @NonNull
    private boolean isVisible;
    /**
     * Indicator whether the edge is currently highlighted.
     */
    private boolean isHighlighted = false;
    /**
     * Indicator whether the edge can be modified in terms of style.
     */
    @Getter
    @Setter
    private boolean isLockedStyle = false;
    /**
     * Indicator whether the edge-type can be modified.
     */
    @Getter
    @Setter
    private boolean isLockedEdgeType = false;

    /**
     * defines whether the edge has priority by rendering or not
     */
    private boolean hasPriority = false;

    /**
     * @param id        The unique ID of the edge.
     * @param color     The color of the edge.
     * @param stroke    The stroke of the edge.
     * @param arrowType The arrowType of the edge.
     * @param isVisible Indicator whether the edge is currently visible.
     * @param anchorIn  true if the edge is having an anchor point in it second vertex (incoming vertex)
     * @param anchorOut true if the edge is having an anchor point in it first vertex  (outgoing vertex)
     */
    public Edge(int id, Color color, StrokeType stroke, EdgeArrowType arrowType, boolean isVisible, boolean anchorIn, boolean anchorOut) {
        this.id = id;
        this.color = color;
        this.stroke = stroke;
        this.arrowType = arrowType;
        this.isVisible = isVisible;
        this.hasAnchorIn = anchorIn;
        this.hasAnchorOut = anchorOut;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Edge) {
            Edge e = (Edge) obj;
            return this.id == e.id;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return Syndrom.getInstance().getVv().getGraphLayout().getGraph().getSource(this) + " \u2192 " +
                Syndrom.getInstance().getVv().getGraphLayout().getGraph().getDest(this);
    }
}
