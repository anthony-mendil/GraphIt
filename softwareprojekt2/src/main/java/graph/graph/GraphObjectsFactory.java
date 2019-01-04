package graph.graph;

import gui.Values;
import lombok.Data;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.LinkedList;
import java.util.Map;

/**
 * Factory to create spheres, edges and vertices.
 */
@Data
public class GraphObjectsFactory {
    /**
     * The values to use.
     */
    Values values;
    /**
     * The global objects counter for the ids for the graph objects.
     */
    int objectCounter;

    /**
     * creates a new graphObjectsFactory
     */
    public GraphObjectsFactory() {
        values = Values.getInstance();
        objectCounter = 0;
    }

    /**
     * Creates a new edge with the values set in values.
     * @return A new edge object.
     */
    public Edge createEdge() {
        throw new UnsupportedOperationException();
    }

    /**
     * Creates a new vertex with the values set in values.
     * @return A new vertex object.
     */
    public Vertex createVertex() {
        throw new UnsupportedOperationException();
    }

    /**
     * Creates a new sphere with the values set in values.
     * @param position The position of the new sphere.
     * @return A new sphere object.
     */
    public Sphere createSphere(Point2D position) {
        int id = objectCounter++;
        Paint fillPaint = values.getFillPaintSphere();
        double width = values.getDefaultWidthSphere();
        double height = values.getDefaultHeightSphere();
        Map<String, String> annotation = values.getDefaultAnnotationSphere();
        String font = values.getFontSphere();
        int fontSize = values.getFontSizeSphere();
        return new Sphere(id, fillPaint, position, width, height, annotation, font, fontSize);
    }
}
