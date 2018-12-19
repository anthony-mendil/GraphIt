package graph.graph;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Map;

/**
 * A vertex of the syndrom graph. A vertex is always assigned to a sphere. Vertices have a unique location and are
 * connected through edges. All attributes of a vertex can be changed, just the id is unique and final.
 */
@Data
@AllArgsConstructor
public class Vertex {
    /**
     * The unique id of a vertex.
     */
    @NonNull
    private final int id;

    /**
     * The fill color of a vertex.
     */
    @NonNull
    private Paint fillPaint;

    /**
     * The coordinate of a vertex. A vertex have to be placed within a sphere and where no other vertex is located.
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
    private Map<String, String> annotation;

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
     * defines whether a edge is visible or not
     */
    @NonNull
    private boolean isVisible;

    /**
     * the font of the annotation
     */
    @NonNull
    private String font;

    /**
     * the font size of the annotation
     */
    @NonNull
    private int fontSize;
}
