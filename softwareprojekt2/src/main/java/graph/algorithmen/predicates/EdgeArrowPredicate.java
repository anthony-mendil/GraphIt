package graph.algorithmen.predicates;

import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.util.Context;
import graph.graph.EdgeArrowType;
import lombok.NonNull;
import lombok.Setter;
import org.apache.commons.collections15.Predicate;

/**
 * Defines a functor that performs a predicates test on vertices for filtering the edge arrow types of the edges.
 *
 * @param <V> The vertex type.
 * @param <E> The edge type.
 */
public class EdgeArrowPredicate<V, E> implements Predicate<Context<Graph<V, E>, E>> {
    /**
     * The arrow type to filter for.
     */
    @Setter
    @NonNull
    private EdgeArrowType edgeArrowType;

    /**
     * Creates a new EdgeArrowPredicate object.
     *
     * @param edgeArrowType The edge arrow type to filter for.
     */
    public EdgeArrowPredicate(EdgeArrowType edgeArrowType) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean evaluate(Context<Graph<V, E>, E> graphEContext) {
        return false;
    }
}
