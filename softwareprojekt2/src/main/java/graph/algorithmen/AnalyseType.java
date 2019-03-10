package graph.algorithmen;

/**
 * Lists all criteria in the analyse-mode treating several vertices.
 *
 * @author Nina Unterberg
 */
public enum AnalyseType {
    /**
     * Filter-type which will filter the predecessor-vertices of the selected vertex.
     */
    NEIGHBOUR_PREDECESSOR,
    /**
     * Filter-type which will filter the successor-vertices of the selected vertex.
     */
    NEIGHBOUR_SUCCESSOR,
    /**
     * Filter-type which will filter the predecessor/successor-vertices of the selected vertex.
     */
    NEIGHBOUR_PREDECESSOR_SUCCESSOR,
}
