package graph.algorithmen.predicates;

import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.util.Context;
import graph.graph.Vertex;
import gui.Values;
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
    private String annotation;

    /**
     * Creates a new VertexAnnotationPredicate object with a regular expression.
     *
     * @param annotation The regular expression to filter for.
     */
    public VertexAnnotationPredicate(String annotation) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean evaluate(Context<Graph<V, E>, V> graphVContext) {
        Vertex v = (Vertex) graphVContext.element;
        return v.getAnnotation().get(Values.getInstance().getGuiLanguage()).contains(annotation);
    }
}
