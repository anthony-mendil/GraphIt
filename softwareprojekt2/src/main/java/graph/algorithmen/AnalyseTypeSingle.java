package graph.algorithmen;
/**
 * Lists all criteria in the analyse-mode treating a single vertex.
 */
public enum AnalyseTypeSingle {
    //da muss immer eins ausgewählt sein
    /**
     * Filter-type which will filter edge-chains of the graph.
     */
    EDGE_CHAINS,
    /**
     * Filter-type which will filter convergent-branches of the graph.
     */
    CONVERGENT_BRANCHES,
    /**
     * Filter-type which will filter divergent-branches of the graph.
     */
    DIVERGENT_BRANCHES,
    /**
     * Filter-type which will filter branches of the graph.
     */
    BRANCHES,
    /**
     * Filter-type which will filter cycles of the graph.
     */
    CYCLEN,
    /**
     * Filter-type which will filter the reinforced edges.
     */
    REINFORCED,
    /**
     * Filter-type which will filter the extenuating edges.
     */
    EXTENUATING,
    /**
     * Filter-type which will filter the neutral edges.
     */
    NEUTRAL
}
