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
    @NonNull
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
     * Defines whether the edge has an anchor point or not on its vertex.
     */
    @NonNull
    private boolean hasAnchor;

    /**
     * first: ausgehender Ankerpunkt
     * second: mündender Ankerpunkt
     */
    private Pair<Point2D, Point2D> anchorPoints;

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
     * Creates an edge between two vertices.
     * @param id           The unique ID of the edge.
     * @param color        The color of the edge.
     * @param stroke       The stroke of the edge.
     * @param arrowType    The arrowType of the edge.
     * @param hasAnchor    Indicator whether of the Edge is an anchorpoint.
     * @param isVisible    Indicator whether the edge is currently visible.
     */
    public Edge(int id, Color color, StrokeType stroke, EdgeArrowType arrowType, boolean hasAnchor, boolean isVisible){
        this.id = id;
        this.color = color;
        this.stroke = stroke;
        this.arrowType = arrowType;
        this.hasAnchor = hasAnchor;
        this.isVisible = isVisible;
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
    public String toString(){
        return Syndrom.getInstance().getVv().getGraphLayout().getGraph().getSource(this) + " -> " +
                Syndrom.getInstance().getVv().getGraphLayout().getGraph().getDest(this);
    }

}
