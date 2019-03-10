package graph.graph;

/**
 * An edge have three relation types: extenuating, neutral, reinforcing.
 *
 * @author Nina Unterberg
 */
public enum EdgeArrowType {
    /**
     * The reinforced arrow type, defining the edge type.
     */
    REINFORCED,
    /**
     * The extenuating arrow type, defining the edge type.
     */
    EXTENUATING,
    /**
     * The neutral/undefined arrow type, defining the edge type.
     */
    NEUTRAL
}
