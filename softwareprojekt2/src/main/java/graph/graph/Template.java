package graph.graph;

import lombok.Data;

import java.util.List;

/**
 * Represents a template, which can be created by the creator(Ersteller).
 */
@Data
public class Template {

    //Graphelement options:
    /**
     * The maximum number of spheres allowed to exist in the graph. This is defined in the template.
     */
    private int maxSpheres;

    /**
     * The maximum number of vertices allowed to exist in the graph. This is defined in the template.
     */
    private int maxVertices;

    /**
     * The maximum number of edges allowed to exist in the graph. This is defined in the template.
     */
    private int maxEdges;

    /**
     * The maximum number of vertices in a sphere. This is defined in the template.
     */
    private int maxVerticesInSphere;

    //Edgetype options:
    /**
     *
     */
    private boolean reinforcedEdgesAllowed;

    /**
     *
     */
    private boolean extenuatingEdgesAllowed;

    /**
     *
     */
    private boolean unknownEdgesAllowed;

    //Locked Objects options:
    /**
     *
     */
    private List<Integer> styleLockedSpheres;

    /**
     *
     */
    private List<Integer> annotationLockedSpheres;

    /**
     *
     */
    private List<Integer> positionLockedSpheres;

    /**
     *
     */
    private List<Integer> verticesLockedSpheres;

    /**
     *
     */
    private List<Integer> styleLockedVertices;

    /**
     *
     */
    private List<Integer> annotationLockedVertices;

    /**
     *
     */
    private List<Integer> positionLockedVertices;

    /**
     *
     */
    private List<Integer> styleLockedEdges;

    /**
     *
     */
    private List<Integer> typeLockedEdges;


    /**
     * Creates a new Template object.
     */
    public Template() {

    }
}
