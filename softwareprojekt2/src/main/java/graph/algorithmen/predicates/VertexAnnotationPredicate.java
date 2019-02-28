package graph.algorithmen.predicates;

import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.util.Context;
import graph.graph.Vertex;
import gui.Values;
import gui.properties.Language;
import lombok.NonNull;
import lombok.Setter;
import org.apache.commons.collections15.Predicate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Defines a functor that performs a predicates test on vertices for filtering the vertex annotations for a regular
 * expression.
 *
 * @param <V> The vertex type.
 * @param <E> The edge type.
 */
public class VertexAnnotationPredicate<V, E> implements Predicate<Context<Graph<V, E>, V>> {
    /**
     * Defines the regular expression after the vertex regex is filtered.
     */
    @Setter
    @NonNull
    private String regex;

    /**
     * Creates a new VertexAnnotationPredicate object with a regular expression.
     *
     * @param regex The regular expression to filter for.
     */
    public VertexAnnotationPredicate(String regex) {
        this.regex = "(.*)" + regex + "(.*)";
    }

    @Override
    public boolean evaluate(Context<Graph<V, E>, V> graphVContext) {
        Vertex vertex = (Vertex) graphVContext.element;
        Pattern pattern = Pattern.compile(regex);
        Language lang = Values.getInstance().getGraphLanguage();
        String annotation = vertex.getAnnotation().get(lang.toString());
        Matcher matcher = pattern.matcher(annotation);
        return matcher.matches();
    }
}
