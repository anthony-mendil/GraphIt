package graph.graph;

import javafx.util.Pair;
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

    private boolean isHighlighted = false;

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
