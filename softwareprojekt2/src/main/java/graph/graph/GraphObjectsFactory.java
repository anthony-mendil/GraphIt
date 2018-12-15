package graph.graph;

import com.google.inject.Inject;
import gui.Values;

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
     *
     */
    public GraphObjectsFactory(int currentId) {
        throw new UnsupportedOperationException();
    }

    /**
     * @return
     */
    public Edge createEdge() {
        throw new UnsupportedOperationException();
    }

    /**
     * @return
     */
    public Vertex createVertex() {
        throw new UnsupportedOperationException();
    }

    /**
     * @return
     */
    public Sphere createSphere() {
        throw new UnsupportedOperationException();
    }
}
