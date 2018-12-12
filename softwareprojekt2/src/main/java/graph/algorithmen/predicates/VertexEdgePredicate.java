package graph.algorithmen.predicates;

import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.util.Context;
import graph.graph.EdgeArrowType;
import lombok.NonNull;
import lombok.Setter;
import org.apache.commons.collections15.Predicate;

/**
 * Defines a functor that performs a predicates test on vertices for filtering the vertex for specific edge type.
 *
 * @param <V> the vertex type
 * @param <E> the edge type
 */
public class VertexEdgePredicate<V, E> implements Predicate<Context<Graph<V, E>, V>> {
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
    public VertexEdgePredicate(EdgeArrowType edgeArrowType) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean evaluate(Context<Graph<V, E>, V> graphVContext) {
        throw new UnsupportedOperationException();
    }
}
