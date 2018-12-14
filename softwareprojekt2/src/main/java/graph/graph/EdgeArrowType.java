package graph.graph;

/**
 * An edge can be have three relation types: extenuating, neutral, reinforcing.
 */
public enum EdgeArrowType {
    /**
     * the reinforced arrow type, defining the edge type
     */
    REINFORCED,
    /**
     * the extenuating arrow type, defining the edge type
     */
    EXTENUATING,
    /**
     * the neutral/ undefined arrow type, defining the edge type
     */
    NEUTRAL
}
