package graph.algorithmen;

/**
 * List all criteria in the analyse-mode treating several vertices.
 */
public enum AnalyseTypeSeveral {
    /**
     * Filter-type which will fiter predecessor-verteices of the selected vertex.
     */
    NEIGHBOUR_PREDECESSOR,
    /**
     * Filter-type which will fiter successor-verteices of the selected vertex.
     */
    NEIGHBOUR_SUCCESSOR,
    /**
     * Filter-type which will fiter outgoing edges of the selected vertex.
     */
    OUTGOING_EDGES,
    /**
     * Filter-type which will fiter incoming edges of the selected vertex.
     */
    INCOMING_EDGES,
    /**
     * Filter-type which will fiter the edgetype of the selected vertex.
     */
    EDGETYPE,
}
