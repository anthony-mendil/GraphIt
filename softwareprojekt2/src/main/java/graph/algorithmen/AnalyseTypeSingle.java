package graph.algorithmen;
/**
 * List all criteria in the analyse-mode treating on a single vertex.
 */
public enum AnalyseTypeSingle {
    //da muss immer eins ausgewählt sein
    /**
     * Filter-type which will fiter edge-chains of the graph.
     */
    EDGE_CHAINS,
    /**
     * Filter-type which will fiter converget-branches of the graph.
     */
    CONVERGENT_BRANCHES,
    /**
     * Filter-type which will fiter divergent-branches of the graph.
     */
    DIVERGENT_BRANCHES,
    /**
     * Filter-type which will fiter branches of the graph.
     */
    BRANCHES,
    /**
     * Filter-type which will fiter cycles of the graph.
     */
    CYCLEN
}
