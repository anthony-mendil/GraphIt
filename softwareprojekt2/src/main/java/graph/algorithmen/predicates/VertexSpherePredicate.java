package graph.algorithmen.predicates;

import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.util.Context;
import graph.graph.Sphere;
import lombok.NonNull;
import lombok.Setter;
import org.apache.commons.collections15.Predicate;

/**
 * Defines a functor that performs a predicates test on vertices for filtering the vertex for a assigned sphere
 *
 * @param <V> the vertex type
 * @param <E> the edge type
 */
public class VertexSpherePredicate<V, E> implements Predicate<Context<Graph<V, E>, V>> {
    /**
     * the sphere to filter for
     */
    @Setter
    @NonNull
    private Sphere sphere;

    /**
     * created a new VertexSpherePredicate with a specific sphere
     *
     * @param sphere the sphere to filter the vertices for
     */
    public VertexSpherePredicate(Sphere sphere) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean evaluate(Context<Graph<V, E>, V> graphVContext) {
        return false;
    }
}
