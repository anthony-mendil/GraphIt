package graph.graph;

import lombok.Data;
import lombok.NonNull;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.awt.*;

/**
 * An edge of the syndrom-graph. There are 3 type of edges, defined by the three types of relations: extenuating,
 * neutral, reinforcing. These are typified by the arrowhead.
 * All attributes of the edge can be changed, only the id is final.
 */
@Data
public class Edge {
    /**
     * the id of the edge.
     */
    @NonNull
    private final int id;
    /**
     * the fill color of the edge/ arrow
     */
    @NonNull
    private Paint fillPaint;
    /**
     * the draw color of the edge/ arrow
     */
    @NonNull
    private Paint drawPaint;
    /**
     * the stroke type of the edge
     */
    @NonNull
    private StrokeType stroke;
    /**
     * the edge arrow type of the edge
     */
    @NonNull
    private EdgeArrowType arrowType;
    /**
     * defines whether the edge has an anchor point on its vertex
     */
    @NonNull
    private boolean hasAnchor;
    /**
     * defines the degree to which the anchor point is attached to the vertex
     */
    @NonNull
    private double anchorAngle;


    /**
     * Creates an edge with a set id and a specific edge type.
     * All other attributes get the default value defined in Values.
     *
     * @param id        the id of the edge
     * @param arrowType the arrow type of the edge
     */
    public Edge(int id, EdgeArrowType arrowType) {
        this.id = id;
        this.arrowType = arrowType;
        throw new NotImplementedException();
    }
}
