package graph.algorithmen.predicates;

import com.sun.istack.internal.NotNull;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.util.Context;
import lombok.Setter;
import org.apache.commons.collections15.Predicate;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Defines a functor that performs a predicates test on vertices for filtering the vertex annotations for a regular
 * expression.
 *
 * @param <V> the vertex type
 * @param <E> the edge type
 */
public class VertexAnnotationPredicate<V, E> implements Predicate<Context<Graph<V, E>, V>> {
    /**
     * Defines the regular expression after the vertex annotation is filtered.
     */
    @Setter
    @NotNull
    private String regularExpression;

    /**
     * creates a new VertexAnnotationPredicate with a regular expression
     * @param pRegularExpression the regular expression to filter for
     */
    public VertexAnnotationPredicate(String pRegularExpression){
        throw new NotImplementedException();
    }

    @Override
    public boolean evaluate(Context<Graph<V, E>, V> graphVContext) {
        return false;
    }
}
