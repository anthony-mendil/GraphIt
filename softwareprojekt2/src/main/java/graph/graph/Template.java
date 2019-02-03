package graph.graph;

import lombok.Data;

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


    /**
     * Creates a new Template object.
     */
    public Template(int pMaxSpheres,int pMaxVertices, int pMaxEdges, boolean pReinforcedEdgesAllowed, boolean pExtenuatingEdgesAllowed, boolean pUnknownEdgesAllowed) {
        maxSpheres=pMaxSpheres;
        maxVertices=pMaxVertices;
        maxEdges=pMaxEdges;
        reinforcedEdgesAllowed=pReinforcedEdgesAllowed;
        extenuatingEdgesAllowed=pExtenuatingEdgesAllowed;
        unknownEdgesAllowed=pUnknownEdgesAllowed;
    }
}
