package graph.graph;

import com.google.inject.Inject;
import gui.Values;

import java.awt.geom.Point2D;

/**
 *
 */
public class GraphObjectsFactory {
    /**
     * the values to use
     */
    @Inject
    Values values;
    /**
     * the global objects counter for the ids for the graph objects
     */
    int objectCounter;

    /**
     * creates a new graphObjectsFactory
     * @param currentId The GraphID
     */
    public GraphObjectsFactory(int currentId) {
        throw new UnsupportedOperationException();
    }

    /**
     * creates a new edge with the values set in values
     * @return a new edge object
     */
    public Edge createEdge() {
        throw new UnsupportedOperationException();
    }

    /**
     * creates a new vertex with the values set in values
     * @return a new vertex object
     */
    public Vertex createVertex() {
        throw new UnsupportedOperationException();
    }

    /**
     * creates a new sphere with the values set in values
     * @param position The position of the new Sphere
     * @return a new sphere object
     */
    public Sphere createSphere(Point2D position) {
        throw new UnsupportedOperationException();
    }
}
