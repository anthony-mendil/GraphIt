package graph.graph;

import java.util.List;

public class Template {

    /**
     * The maximum number of spheres allowed to exist in the graph. This is defined in the template.
     */
    private int maxSphereCounter;

    /**
     * The maximum number of vertices allowed to exist in the graph. This is defined in the template.
     */
    private int maxVertexCounter;

    /**
     * The maximum number of vertices in a sphere. This is defined in the template.
     */
    private int maxVertexinSphereCounter;

    /**
     * The maximum number of edges allowed to exist in the graph. This is defined in the template.
     */
    private int maxEdgeCounter;

    private List<Integer> nameLockedObjects;
    private List<Integer> colorLockedObjects;
    private List<Integer> isLockedObjects;
}
