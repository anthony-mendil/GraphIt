package graph.graph;

import lombok.Data;

import java.util.List;

@Data
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
    private int maxVertexInSphereCounter;

    /**
     * The maximum number of edges allowed to exist in the graph. This is defined in the template.
     */
    private int maxEdgeCounter;

    /**
     * a list with all object ids where the name is not changeable
     */
    private List<Integer> nameLockedObjects;

    /**
     * a list with all object ids where the color is not changeable
     */
    private List<Integer> colorLockedObjects;

    /**
     * a list with all object ids where all attributes are not changeable
     */
    private List<Integer> isLockedObjects;

    /**
     * all edge types that are allowed to use
     */
    private List<EdgeArrowType> allowedEdgeTypes;

    /**
     * creates a new Template
     */
    public Template() {
        throw new UnsupportedOperationException();
    }
}
