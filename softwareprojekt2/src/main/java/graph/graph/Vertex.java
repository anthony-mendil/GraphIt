package graph.graph;


import gui.Values;
import gui.properties.Language;
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
 *
 * @author Nina Unterberg
 */
@Data
public class Vertex {
    /**
     * The unique id of a vertex.
     */
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
    private Map<ScopePoint, Pair<Point2D, AffineTransform>> vertexArrowReinforced;

    /**
     * The edges with a neutral relation with its position on the vertex.
     */
    @NonNull
    private Map<ScopePoint, Pair<Point2D, AffineTransform>> vertexArrowNeutral;

    /**
     * The edges with a extenuating relation with its position on the vertex.
     */
    @NonNull
    private Map<ScopePoint, Pair<Point2D, AffineTransform>> vertexArrowExtenuating;

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
     * The indicator whether the vertex can be moved and deleted.
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
     * @param colors      The colors for the vertex (getFirst() - fillColor, getSecond() - drawColor)
     * @param coordinates The coordinates of the vertex.
     * @param shape       The shape of the vertex.
     * @param annotation  The annotation of the vertex.
     * @param font        The font of the annotation of the vertex.
     * @param sizes       The sizes for the vertex (getFirst() - shapeSize, getSecond() - fontSize)
     */
    public Vertex(int id, edu.uci.ics.jung.graph.util.Pair<Color> colors, Point2D coordinates, VertexShapeType shape, Map<String, String>
            annotation, edu.uci.ics.jung.graph.util.Pair<Integer> sizes, String font) {
        this.id = id;
        this.fillColor = colors.getFirst();
        this.coordinates = coordinates;
        this.shape = shape;
        this.drawColor = colors.getSecond();
        this.size = sizes.getFirst();
        this.annotation = annotation;
        this.font = font;
        this.fontSize = sizes.getSecond();
        vertexArrowExtenuating = new EnumMap<>(ScopePoint.class);
        vertexArrowNeutral = new EnumMap<>(ScopePoint.class);
        vertexArrowReinforced = new EnumMap<>(ScopePoint.class);
        isVisible = true;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
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
    public String toString() {
        if (Values.getInstance().getGuiLanguage() == Language.ENGLISH) {
            return annotation.get(Language.ENGLISH.name());
        } else {
            return annotation.get(Language.GERMAN.name());
        }
    }
}
