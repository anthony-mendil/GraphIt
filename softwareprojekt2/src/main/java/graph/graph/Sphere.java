package graph.graph;

import edu.uci.ics.jung.graph.util.Pair;
import gui.Values;
import gui.properties.Language;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.LinkedList;
import java.util.Map;

/**
 * A sphere of the syndrom graph. A sphere is a contiguous area in a syndrome graph. Spheres are clearly separated from
 * each other. They can contain vertices. A vertex is always assigned to exactly one sphere. The id is unique and final.
 */
@Data
public class Sphere {
    /**
     * The id of the sphere.
     */
    private final int id;

    /**
     * The fill color of the sphere.
     */
    @NonNull
    private Color color;

    /**
     * The coordinate of the sphere. The coordinate refers to the upper left corner of a sphere. A sphere can
     * not be positioned where a different sphere is already located.
     */
    @NonNull
    private Point2D coordinates;

    /**
     * The width of the sphere.
     */
    @NonNull
    private double width;

    /**
     * The height of the sphere.
     */
    @NonNull
    private double height;

    /**
     * The annotation of the sphere.
     */
    @NonNull
    private Map<String, String> annotation;

    /**
     * A list with all vertices, that are assigned to this sphere.
     */
    @NonNull
    private LinkedList<Vertex> vertices;

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
     * The indicator whether the annotation and it's style
     * of the Sphere can be modified.
     */
    @Getter
    @Setter
    private boolean lockedAnnotation = false;
    /**
     * The indicator whether the sphere can be moved deleted.
     */
    @Getter
    @Setter
    private boolean lockedPosition = false;
    /**
     * The Indicator whether the sphere can be changed
     * in terms of style.
     */
    @Getter
    @Setter
    private boolean lockedStyle = false;
    /**
     * The indicator whether the vertices in the sphere
     * can be added or deleted.
     */
    @Getter
    @Setter
    private boolean lockedVertices = false;
    /**
     * The indicator that locks the maximum amount of vertices
     * that are allowed in the sphere.
     */
    @Getter
    @Setter
    private String lockedMaxAmountVertices = "";


    /**
     * Creates an Sphere. It will be created by the factory.
     *
     * @param id          The unique id of the sphere.
     * @param color       The color of the sphere.
     * @param coordinates The coordinates of the sphere.
     * @param sizes       The sizes of the sphere (getFirst() - width, getSecond() - height)
     * @param annotation  The annotation of the sphere.
     * @param font        The font of the annotation of the sphere.
     * @param fontSize    The font-size of the sphere.
     */
    public Sphere(int id, Color color, Point2D coordinates, Pair<Double> sizes, Map<String, String>
            annotation, String font, int fontSize) {
        this.id = id;
        this.color = color;
        this.coordinates = coordinates;
        this.width = sizes.getFirst();
        this.height = sizes.getSecond();
        this.annotation = annotation;
        this.font = font;
        this.fontSize = fontSize;
        vertices = new LinkedList<>();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Sphere) {
            Sphere s = (Sphere) obj;
            return this.id == s.id;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        if (Values.getInstance().getGuiLanguage() == Language.GERMAN) {
            return annotation.get(Language.GERMAN.name());
        } else if (Values.getInstance().getGuiLanguage() == Language.ENGLISH) {
            return annotation.get(Language.ENGLISH.name());
        }
        return "";
    }
}
