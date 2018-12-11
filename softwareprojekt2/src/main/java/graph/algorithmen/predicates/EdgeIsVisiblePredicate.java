package graph.algorithmen.predicates;

import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.util.Context;
import org.apache.commons.collections15.Predicate;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Defines a functor that performs a predicates test on edges for filtering the edge for visibility.
 * @param <V> the vertex type
 * @param <E> the edge type
 */
public class EdgeIsVisiblePredicate<V,E>  implements Predicate<Context<Graph<V,E>,V>> {
    @Override
    public boolean evaluate(Context<Graph<V, E>, V> graphVContext) {
        throw new NotImplementedException();
    }
}