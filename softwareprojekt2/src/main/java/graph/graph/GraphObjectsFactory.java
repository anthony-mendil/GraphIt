package graph.graph;

import edu.uci.ics.jung.graph.util.Pair;
import gui.Values;
import lombok.AccessLevel;
import lombok.Setter;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.Map;

/**
 * Factory to create spheres, edges and vertices.
 */
public class GraphObjectsFactory {
    /**
     * The values to use.
     */
    @Setter(AccessLevel.NONE)
    private final Values values;
    /**
     * The global objects counter for the ids for the graph objects.
     */
    private int objectCounter;

    /**
     * Creates a new GraphObjectsFactory object.
     */
    GraphObjectsFactory() {
        values = Values.getInstance();
        objectCounter = 0;
    }

    /**
     * sets the object counter to specific value
     *
     * @param id the id
     */
    public void setObjectCounter(int id) {
        objectCounter = id;
    }

    /**
     * Creates a new edge with the values set in values.
     *
     * @return A new edge object.
     */
    Edge createEdge() {
        int id = objectCounter++;
        Color color = values.getEdgePaint();
        StrokeType stroke = values.getStrokeEdge();
        EdgeArrowType arrowType = values.getEdgeArrowType();
        return new Edge(id, color, stroke, arrowType, true, false, false);
    }

    /**
     * Creates a new vertex with the values set in values.
     * @param position the position of the new vertex i
     * @return A new vertex object.
     */
    Vertex createVertex(Point2D position) {
        int id = objectCounter++;
        Color fillPaint = values.getFillPaintVertex();
        Color drawPaint = values.getDrawPaintVertex();
        Map<String, String> annotation = values.getDefaultAnnotationVertex();
        Map<String, String> vertexAnnotation = new HashMap<>();
        for (Map.Entry<String, String> entry : annotation.entrySet()) {
            vertexAnnotation.put(entry.getKey(), entry.getValue() + " " + id);
        }
        String font = values.getFontVertex();
        int fontSize = values.getFontSizeVertex();
        VertexShapeType shape = values.getShapeVertex();
        int size = Values.DEFAULT_SIZE_VERTEX;
        Pair<Color> colors = new Pair<>(fillPaint, drawPaint);
        Pair<Integer> sizes = new Pair<>(size, fontSize);
        return new Vertex(id, colors, position, shape,
                vertexAnnotation, sizes, font);
    }


    /**
     * Creates a new sphere with the values set in values.
     *
     * @param position The position of the new sphere.
     * @return A new sphere object.
     */
    Sphere createSphere(Point2D position) {
        int id = objectCounter++;
        Color fillPaint = values.getFillPaintSphere();
        double width = Values.DEFAULT_WIDTH_SPHERE;
        double height = Values.DEFAULT_HEIGHT_SPHERE;
        Pair<Double> size = new Pair<>(width, height);
        Map<String, String> annotation = values.getDefaultAnnotationSphere();
        Map<String, String> sphereAnnotation = new HashMap<>();
        for (Map.Entry<String, String> entry : annotation.entrySet()) {
            sphereAnnotation.put(entry.getKey(), entry.getValue() + " " + id);
        }
        String font = values.getFontSphere();
        int fontSize = values.getFontSizeSphere();
        return new Sphere(id, fillPaint, position, size, sphereAnnotation, font, fontSize);
    }
}
