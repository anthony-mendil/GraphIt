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
 * @param <V> the vertix type
 * @param <E> the edge type
 */
public class EdgeArrowPredicate<V, E> implements Predicate<Context<Graph<V, E>, E>> {
    /**
     * the arrow type to filter for
     */
    @Setter
    @NonNull
    private EdgeArrowType edgeArrowType;

    /**
     * creates a new EdgeArrowPredicate
     *
     * @param edgeArrowType the edge arrow type to filter for
     */
    public EdgeArrowPredicate(EdgeArrowType edgeArrowType) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean evaluate(Context<Graph<V, E>, E> graphEContext) {
        return false;
    }
}
