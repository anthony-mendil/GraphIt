package graph.graph;

import lombok.Data;

/**
 * Represents a template, which can be created by the creator.
 */
@Data
public class Template {

    /**
     * Indicator, whether the number of spheres is locked.
     */
    private boolean lockedSphereNumber = false;
    /**
     * Indicator, whether the number of vertices is locked.
     */
    private boolean lockedVertexNumber = false;
    /**
     * Indicator, whether the number of Edges is locked.
     */
    private boolean lockedEdgesNumber = false;

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
     * Allows the user to use reinforced arrows.
     */
    private boolean reinforcedEdgesAllowed;

    /**
     * Allows the user to use extenuating arrows.
     */
    private boolean extenuatingEdgesAllowed;

    /**
     * Allows the user to use neutral arrows.
     */
    private boolean neutralEdgesAllowed;

    /**
     * Indicator whether the checkbox is selected in one of them.(No use atm)
     */
    private boolean set;

    /**
     * Creates a new Template object.
     */
    public Template(int pMaxSpheres,int pMaxVertices, int pMaxEdges, boolean pReinforcedEdgesAllowed, boolean pExtenuatingEdgesAllowed, boolean pUnknownEdgesAllowed) {
        maxSpheres=pMaxSpheres;
        maxVertices=pMaxVertices;
        maxEdges=pMaxEdges;
        reinforcedEdgesAllowed=pReinforcedEdgesAllowed;
        extenuatingEdgesAllowed=pExtenuatingEdgesAllowed;
        neutralEdgesAllowed=pUnknownEdgesAllowed;
    }
    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(maxSpheres);
        stringBuilder.append(" spheres allowed, ");
        stringBuilder.append(maxVertices);
        stringBuilder.append(" vertices, ");
        stringBuilder.append(maxEdges);
        stringBuilder.append(" edges allowed");
        if(reinforcedEdgesAllowed){
            stringBuilder.append(", reinforced edges allowed");
        }else{
            stringBuilder.append(", reinforced edges not allowed");
        }
        if(extenuatingEdgesAllowed){
            stringBuilder.append(", extenuating edges allowed");
        }else {
            stringBuilder.append(", extenuating edges not allowed");
        }
        if(neutralEdgesAllowed){
            stringBuilder.append(", unknown edges allowed");
        }else {
            stringBuilder.append(", unknown edges not allowed");
        }
        return stringBuilder.toString();
    }
}
