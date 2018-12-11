package graph.graph;

import lombok.Data;
import lombok.NonNull;
import org.hibernate.annotations.NotFound;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Map;

/**
 * A vertex of the syndrom graph. A vertex is always assigned to a sphere. Vertices have a unique location and are
 * connected through edges. All attributes of a vertex can be changed, just the id is unique and final.
 */
@Data
public class Vertex {
    /**
     * the unique id of a vertex
     */
    @NonNull
    private final int id;

    /**
     * the fill color of a vertex
     */
    @NonNull
    private Paint fillPaint;

    /**
     * the coordinate of a vertex. A vertex have to be placed within a sphere and where no other vertex is located.
     */
    @NonNull
    private Point2D coordinate;

    /**
     * the shape of a vertex.
     */
    @NonNull
    private VertexShapeType shape;

    /**
     * the annotation of a vertex
     */
     @NonNull
    private String annotation;

    /**
     * the draw color of a vertex
     */
    @NonNull
    private Paint drawPaint;

    /**
     * the edges with a reinforcing relation with its position on the vertex.
     */
    @NonNull
    private Map<ScopePoint, Point2D> vertixArrowReinforced = null;

    /**
     * the edges with a neutral relation with its position on the vertex.
     */
    @NonNull
    private Map<ScopePoint, Point2D> vertixArrowNeutral = null;

    /**
     * the edges with a extenuating relation with its position on the vertex.
     */
    @NonNull
    private Map<ScopePoint, Point2D> vertixArrowExtenuating = null;

    /**
     * the siz of a vertex
     */
    @NonNull
    private int size;

    /**
     * the radio of the vertex
     */
    @NonNull
    private float radio;

    /**
     * Creates a vertex with a unique id and a coordinate where its placed.
     * All other attributes get the default value defined in Values.
     *
     * @param id         the id of the vertex
     * @param coordinate the coordinate where the vertex is placed
     */
    public Vertex(int id, Point2D coordinate) {
        this.id = id;
        this.coordinate = coordinate;
        throw new NotImplementedException();
    }
}
