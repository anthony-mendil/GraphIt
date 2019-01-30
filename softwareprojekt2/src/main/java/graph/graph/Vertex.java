package graph.graph;


import javafx.util.Pair;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.EnumMap;
import java.util.Map;

/**
 * A vertex of the syndrom graph. A vertex is always assigned to a sphere. Vertices have a unique location and are
 * connected through edges. All attributes of a vertex can be changed, just the id is unique and final.
 */
@Data
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
    private Color fillColor;

    /**
     * The coordinate of a vertex. A vertex have to be placed within a sphere and where no other vertex is located.
     */
    @NonNull
    @Getter
    private Point2D coordinates;

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
    private Color drawColor;

    /**
     * The edges with a reinforcing relation with its position on the vertex.
     */
    @NonNull
    private EnumMap<ScopePoint, Pair<Point2D, AffineTransform>> vertexArrowReinforced;

    /**
     * The edges with a neutral relation with its position on the vertex.
     */
    @NonNull
    private EnumMap<ScopePoint, Pair<Point2D, AffineTransform>> vertexArrowNeutral;

    /**
     * The edges with a extenuating relation with its position on the vertex.
     */
    @NonNull
    private EnumMap<ScopePoint, Pair<Point2D, AffineTransform>> vertexArrowExtenuating;

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
    /**
     * The indicator whether the Vertex is highlighted.
     */
    private boolean isHighlighted = false;
    /**
     * The indicator whether the vertex is locked in terms of style.
     */
    @Getter
    @Setter
    private boolean isLockedStyle = false;
    /**
     * The indicator whether the vertex can be moved.
     */
    @Getter
    @Setter
    private boolean isLockedPosition = false;
    /**
     * The indicator whether the vertex is locked in terms of the style
     * and content of the annotation of the sphere.
     */
    @Getter
    @Setter
    private boolean isLockedAnnotation = false;

    /**
     * Creates a new vertex.
     *
     * @param id          The identification of the vertex.
     * @param fillColor   The inner colour of the vertex.
     * @param coordinates The cooridnates of the vertex.
     * @param shape       The shape of the vertex.
     * @param annotation  The annotation of the vertex.
     * @param drawColor   The colour of the boundary.
     * @param size        The size of the vertex.
     * @param font        The font of the annotation of the vertex.
     * @param fontSize    The font size of the annotation of the vertex.
     */

    public Vertex(int id, Color fillColor, Point2D coordinates, VertexShapeType shape, Map<String, String>
            annotation, Color drawColor, int size, String font, int fontSize) {
        this.id = id;
        this.fillColor = fillColor;
        this.coordinates = coordinates;
        this.shape = shape;
        this.drawColor = drawColor;
        this.size = size;
        this.annotation = annotation;
        this.font = font;
        this.fontSize = fontSize;
        vertexArrowExtenuating = new EnumMap<>(ScopePoint.class);
        vertexArrowNeutral = new EnumMap<>(ScopePoint.class);
        vertexArrowReinforced = new EnumMap<>(ScopePoint.class);
        isVisible = true;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Vertex) {
            Vertex v = (Vertex) obj;
            return this.id == v.id;
        }
        return false;
    }

    @Override

    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return annotation.get("de");
    }
}
