package graph.graph;

/**
 * The stroke type of an edge. It can be dashed, dotted and basic. All edges can also be shown in bold (weighted).
 */
public enum StrokeType {
    /**
     * dashed stroke type of the edge
     */
    DASHED,
    /**
     * dotted stroke type of the edge
     */
    DOTTED,
    /**
     * dashed stroke type in the bold of the edge
     */
    DASHED_WEIGHT,
    /**
     * dotted stroke type in the bold of the edge
     */
    DOTTED_WEIGHT,
    /**
     * basic stroke type of the edge
     */
    BASIC,
    /**
     * basic stroke type in the bold of the edge
     */
    BASIC_WEIGHT
}
