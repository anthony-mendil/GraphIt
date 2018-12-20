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
     * The shape of a vertex.
     */
    @NonNull
    private VertexShapeType shape;

    /**
     * The annotation of a vertex.
     */
    @NonNull
    private Map<String, String> annotation;

    /**
     * The draw color of a vertex.
     */
    @NonNull
    private Paint drawPaint;

    /**
     * The edges with a reinforcing relation with its position on the vertex.
     */
    @NonNull
    private Map<ScopePoint, Point2D> vertexArrowReinforced = null;

    /**
     * The edges with a neutral relation with its position on the vertex.
     */
    @NonNull
    private Map<ScopePoint, Point2D> vertexArrowNeutral = null;

    /**
     * The edges with a extenuating relation with its position on the vertex.
     */
    @NonNull
    private Map<ScopePoint, Point2D> vertexArrowExtenuating = null;

    /**
     * The size of a vertex.
     */
    @NonNull
    private int size;

    /**
     * Defines whether an edge is visible or not.
     */
    @NonNull
    private boolean isVisible;

    /**
     * The font of the annotation.
     */
    @NonNull
    private String font;

    /**
     * The font size of the annotation.
     */
    @NonNull
    private int fontSize;
}
