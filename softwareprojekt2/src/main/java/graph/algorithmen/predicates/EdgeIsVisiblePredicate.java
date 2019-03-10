package graph.algorithmen.predicates;

import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.util.Context;
import graph.graph.Edge;
import org.apache.commons.collections15.Predicate;

/**
 * Defines a functor that performs a predicates test on vertices for filtering the vertex for visibility.
 *
 * @author Nina Unterberg
 * @param <V> The vertex type.
 * @param <E> The edge type.
 */
public class EdgeIsVisiblePredicate<V, E> implements Predicate<Context<Graph<V, E>, E>> {
    @Override
    public boolean evaluate(Context<Graph<V, E>, E> graphVContext) {
        E e = graphVContext.element;
        Edge edge = (Edge) e;
        return edge.isVisible();
    }
}
