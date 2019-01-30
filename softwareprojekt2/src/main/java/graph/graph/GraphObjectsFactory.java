package graph.graph;

import gui.Values;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.awt.*;
import java.awt.geom.Point2D;
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
    public GraphObjectsFactory() {
        values = Values.getInstance();
        objectCounter = 0;
    }

    public void setObjectCounter(int id){
        objectCounter = id;
    }

    /**
     * Creates a new edge with the values set in values.
     * @return A new edge object.
     */
    public Edge createEdge() {

       int id = objectCounter++;
       Color color = values.getEdgePaint();
       StrokeType stroke = values.getStrokeEdge();
       EdgeArrowType arrowType = values.getEdgeArrowType();
       boolean hasAnchor = false;
       boolean isVisible = true;
       return new Edge(id, color, stroke, arrowType, hasAnchor, isVisible);
    }

    /**
     * Creates a new vertex with the values set in values.
     * @return A new vertex object.
     */
    public Vertex createVertex(Point2D position) {
        int id = objectCounter++;
        Color fillPaint = values.getFillPaintVertex();
        Color drawPaint = values.getDrawPaintVertex();
        Map<String, String> annotation = values.getDefaultAnnotationVertex();
        String font = values.getFontVertex();
        int fontSize = values.getFontSizeVertex();
        VertexShapeType shape = values.getShapeVertex();
        int size = values.getDefaultSizeVertex();
        return new Vertex(id, fillPaint, position, shape,
                annotation, drawPaint, size, font, fontSize);
    }


    /**
     * Creates a new sphere with the values set in values.
     * @param position The position of the new sphere.
     * @return A new sphere object.
     */
    public Sphere createSphere(Point2D position) {
        System.out.println("create: "+objectCounter);
        int id = objectCounter++;
        Color fillPaint = values.getFillPaintSphere();
        double width = values.getDefaultWidthSphere();
        double height = values.getDefaultHeightSphere();
        Map<String, String> annotation = values.getDefaultAnnotationSphere();
        String font = values.getFontSphere();
        int fontSize = values.getFontSizeSphere();
        return new Sphere(id, fillPaint, position, width, height, annotation, font, fontSize);
    }
}
