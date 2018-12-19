package graph.algorithmen;

/**
 * Filtertypes treating the flow of the vertices.
 */
public enum SelectionType {
    /**
     * Filter-type which will filter all reahcable vertices starting from the selected vertex.
     */
    ALL_REACHABLE_VERTICES,
    /**
     * Filter-type which will filter all vertices inbetween two veritces.
     */
    VERTICES_INBETWEEN
}
