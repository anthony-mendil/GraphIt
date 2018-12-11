package graph.visualization.predicate;

import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.util.Context;
import org.apache.commons.collections15.Predicate;

/**
 * Defines a functor that performs a predicate test on vertices for filtering the edge arrow types of the edges.
 * @param <V> the vertix type
 * @param <E>
 */
public class EdgeArrowPredicate<V,E> implements Predicate<Context<Graph<V,E>,E>> {
    @Override
    public boolean evaluate(Context<Graph<V, E>, E> graphEContext) {
        return false;
    }
}
