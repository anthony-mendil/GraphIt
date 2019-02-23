package graph.algorithmen.predicates;

import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.util.Context;
import org.apache.commons.collections15.Predicate;

import java.util.List;

/**
 * Combines all predicates to filter for.
 *
 * @param <V> The vertex type.
 * @param <E> The edge type.
 */
public class CombinesPredicate<V, E> implements Predicate<Context<Graph<V, E>, V>> {
    /**
     * The list of predicates to filter for.
     */
    private List<Predicate> predicates;

    /**
     * Creates the CombinesPredicate object for filtering.
     *
     * @param pPredicates The list of predicates.
     */
    public CombinesPredicate(List<Predicate> pPredicates) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean evaluate(Context<Graph<V, E>, V> graphVContext) {
        throw new UnsupportedOperationException();
    }
}
