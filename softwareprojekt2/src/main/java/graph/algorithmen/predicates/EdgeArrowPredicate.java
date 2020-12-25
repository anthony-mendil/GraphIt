package graph.algorithmen.predicates;

import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.util.Context;
import graph.graph.Edge;
import graph.graph.EdgeArrowType;
import lombok.NonNull;
import lombok.Setter;
import org.apache.commons.collections15.Predicate;

import java.util.LinkedList;
import java.util.List;

/**
 * Defines a functor that performs a predicates test on vertices for filtering the edge arrow types of the edges.
 *
 * @author Nina Unterberg
 * @param <V> The vertex type.
 * @param <E> The edge type.
 */
public class EdgeArrowPredicate<V, E> implements Predicate<Context<Graph<V, E>, E>> {
    /**
     * The arrow type to filter for.
     */
    @Setter
    @NonNull
    private LinkedList<EdgeArrowType> edgeArrowType;

    /**
     * Creates a new EdgeArrowPredicate object.
     *
     * @param edgeArrowType The edge arrow type to filter for.
     */
    public EdgeArrowPredicate(LinkedList<EdgeArrowType> edgeArrowType) {
        this.edgeArrowType = edgeArrowType;
    }

    @Override
    public boolean evaluate(Context<Graph<V, E>, E> graphEContext) {
        Edge e = (Edge) graphEContext.element;
        return edgeArrowType.contains(e.getArrowType());
    }
}
