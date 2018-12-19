package graph.algorithmen.predicates;

import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.util.Context;
import lombok.NonNull;
import lombok.Setter;
import org.apache.commons.collections15.Predicate;

/**
 * Defines a functor that performs a predicates test on vertices for filtering the vertex annotations for a regular
 * expression.
 *
 * @param <V> The vertex type.
 * @param <E> The edge type.
 */
public class VertexAnnotationPredicate<V, E> implements Predicate<Context<Graph<V, E>, V>> {
    /**
     * Defines the regular expression after the vertex annotation is filtered.
     */
    @Setter
    @NonNull
    private String regularExpression;

    /**
     * Creates a new VertexAnnotationPredicate object with a regular expression.
     *
     * @param pRegularExpression The regular expression to filter for.
     */
    public VertexAnnotationPredicate(String pRegularExpression) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean evaluate(Context<Graph<V, E>, V> graphVContext) {
        return false;
    }
}
