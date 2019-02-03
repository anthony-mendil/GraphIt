package graph.algorithmen.predicates;

import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.util.Context;
import graph.graph.Edge;
import graph.graph.Vertex;
import org.apache.commons.collections15.Predicate;

/**
 * Defines a functor that performs a predicates test on vertices for filtering the vertex for visibility.
 *
 * @param <V> The vertex type.
 * @param <E> The edge type.
 */
public class VertexIsVisiblePredicate<V, E> implements Predicate<Context<Graph<V, E>, V>> {
    @Override
    public boolean evaluate(Context<Graph<V, E>, V> graphVContext) {
        V v = graphVContext.element;
        Vertex vertex = (Vertex) v;
        return vertex.isVisible();
    }
}
