package graph.graph;

import lombok.Data;
import lombok.NonNull;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.LinkedList;

/**
 * A sphere of the syndrom graph. A sphere is a contiguous area in a syndrome graph. Spheres are clearly separated from
 * each other. They can contain vertices. A vertex is always assigned to exactly one sphere.
 */
@Data
public class Sphere {
    /**
     * the id of the sphere
     */
    @NonNull
    private final int id;

    /**
     * the fill color of the sphere
     */
    @NonNull
    private Paint fillPaint;

    /**
     * the draw color of the sphere
     */
    @NonNull
    private Paint drawPaint;

    /**
     * the coordinate where the sphere is set. The coordinate refers to the upper left corner of a sphere. A sphere can
     * not be positioned where another sphere is already located.
     */
    @NonNull
    private Point2D coordinates;

    /**
     * the width of the sphere
     */
    @NonNull
    private double width;

    /**
     * the height of the sphere
     */
    @NonNull
    private double height;

    /**
     * the annotation of the sphere
     */
    @NonNull
    private String annotation;

    /**
     * a list with all vertices, that are assigned so this sphere
     */
    @NonNull
    private LinkedList<Vertex> vertices;


    /**
     * Creates a sphere with a specific id on a certain point. All other attributes get the default value defined in
     * Values.
     *
     * @param coordinates the point where to put the sphere
     * @param id          the id of the sphere
     */
    public Sphere(Point2D coordinates, int id) {
        this.id = id;
        this.coordinates = coordinates;
        throw new UnsupportedOperationException();
    }
}
