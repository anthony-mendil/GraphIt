package graph.graph;

import lombok.Data;

import java.util.List;

/**
 * Represents a template, which can be created by the creator(Ersteller).
 */
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
     * A list with all object ids where the name is not changeable.
     */
    private List<Integer> nameLockedObjects;

    /**
     * A list with all object ids where the color is not changeable.
     */
    private List<Integer> colorLockedObjects;

    /**
     * A list with all object ids where all attributes are not changeable.
     */
    private List<Integer> isLockedObjects;

    /**
     * All edge types that are allowed to use.
     */
    private List<EdgeArrowType> allowedEdgeTypes;

    /**
     * Creates a new Template object.
     */
    public Template(int pMaxSphereCounter, int pMaxVertexCounter, int pMaxVertexInSphereCounter, int pMaxEdgeCounter,
                    List<Integer> pNameLockedObjects, List<Integer> pColorLockedObjects, List<Integer> pIsLockedObjects,
                    List<EdgeArrowType> pAllowedEdgeTypes) {

        maxSphereCounter = pMaxSphereCounter;
        maxVertexCounter = pMaxVertexCounter;
        maxVertexInSphereCounter = pMaxVertexInSphereCounter;
        maxEdgeCounter = pMaxEdgeCounter;
        nameLockedObjects = pNameLockedObjects;
        colorLockedObjects = pColorLockedObjects;
        isLockedObjects = pIsLockedObjects;
        allowedEdgeTypes = pAllowedEdgeTypes;

    }
}
