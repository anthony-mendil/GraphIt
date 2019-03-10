package graph.graph;

/**
 * The stroke type of an edge. It can be dashed, dotted and basic. All edges can also be shown in bold (weighted).
 *
 * @author Nina Unterberg
 */
public enum StrokeType {
    /**
     * Dashed stroke type of the edge.
     */
    DASHED,
    /**
     * Dotted stroke type of the edge.
     */
    DOTTED,
    /**
     * Dashed stroke type in the bold of the edge.
     */
    DASHED_WEIGHT,
    /**
     * Dotted stroke type in the bold of the edge.
     */
    DOTTED_WEIGHT,
    /**
     * Basic stroke type of the edge.
     */
    BASIC,
    /**
     * Basic stroke type in the bold of the edge.
     */
    BASIC_WEIGHT
}
