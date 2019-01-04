package graph.algorithmen;

/**
 * Filter-types treating the flow of the vertices.
 */
public enum SelectionType {
    /**
     * Filter-type which will filter all reachable vertices starting from the selected vertex.
     */
    ALL_REACHABLE_VERTICES,
    /**
     * Filter-type which will filter all vertices in between two vertices.
     */
    VERTICES_INBETWEEN
}
