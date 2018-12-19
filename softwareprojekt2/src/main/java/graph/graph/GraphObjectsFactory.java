package graph.graph;

import com.google.inject.Inject;
import gui.Values;
import lombok.Data;

import java.awt.geom.Point2D;

/**
 * Factory to create spheres, edges and vertices.
 */
@Data
public class GraphObjectsFactory {
    /**
     * The values to use.
     */
    @Inject
    Values values;
    /**
     * The global objects counter for the ids for the graph objects.
     */
    int objectCounter;

    /**
     * creates a new graphObjectsFactory
     */
    public GraphObjectsFactory() {
        throw new UnsupportedOperationException();
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
        throw new UnsupportedOperationException();
    }
}
