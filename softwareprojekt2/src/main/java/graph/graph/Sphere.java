package graph.graph;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

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
    @NonNull
    private final int id;

    /**
     * The fill color of the sphere.
     */
    @NonNull
    private Paint fillPaint;

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
    private LinkedList<Vertex> vertices = null;

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

    public Sphere(int id, Paint fillPaint, Point2D coordinates, double width, double height, Map<String, String>
            annotation, String font, int fontSize ){
        this.id = id;
        this.fillPaint = fillPaint;
        this.coordinates = coordinates;
        this.width = width;
        this.height = height;
        this.annotation = annotation;
        this.font = font;
        this.fontSize = fontSize;

    }
}
