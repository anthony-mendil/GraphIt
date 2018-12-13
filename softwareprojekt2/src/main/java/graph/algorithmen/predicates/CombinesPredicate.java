package graph.algorithmen.predicates;

import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.util.Context;
import org.apache.commons.collections15.Predicate;

import java.util.List;

/**
 * combines all predicates to filter for
 * @param <V> The vertex type
 * @param <E> The edge type
 */
public class CombinesPredicate<V, E> implements Predicate<Context<Graph<V, E>, V>> {
    /**
     * the list of predicates to filter for
     */
    private List<Predicate> predicates;

    /**
     * creates the CombinesPredicate for filtering
     * @param pPredicates the list of predicates
     */
    public CombinesPredicate(List<Predicate> pPredicates){
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean evaluate(Context<Graph<V, E>, V> graphVContext) {
        throw new UnsupportedOperationException();
    }
}
