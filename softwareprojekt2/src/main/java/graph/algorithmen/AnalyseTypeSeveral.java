package graph.algorithmen;

/**
 * Lists all criteria in the analyse-mode treating several vertices.
 */
public enum AnalyseTypeSeveral {
    /**
     * Filter-type which will filter the predecessor-vertices of the selected vertex.
     */
    NEIGHBOUR_PREDECESSOR,
    /**
     * Filter-type which will filter the successor-vertices of the selected vertex.
     */
    NEIGHBOUR_SUCCESSOR,
//    /**
//     * Filter-type which will filter the originating edges of the selected vertex.
//     */
//    OUTGOING_EDGES,
//    /**
//     * Filter-type which will filter the incoming edges of the selected vertex.
//     */
//    INCOMING_EDGES,
}
