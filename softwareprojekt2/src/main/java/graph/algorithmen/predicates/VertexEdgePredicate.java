package graph.algorithmen.predicates;

import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.util.Context;
import graph.graph.EdgeArrowType;
import lombok.NonNull;
import lombok.Setter;
import org.apache.commons.collections15.Predicate;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Defines a functor that performs a predicates test on vertices for filtering the vertex for specific edge type.
 * @param <V>
 * @param <E>
 */
public class VertexEdgePredicate<V,E> implements Predicate<Context<Graph<V, E>, V>> {
    /**
     * the arrow type to filter for
     */
    @Setter
    @NonNull
    private EdgeArrowType edgeArrowType;

    /**
     * creates a new EdgeArrowPredicate
     *
     * @param edgeArrowType the edge arrow type to filter for
     */
    public VertexEdgePredicate(EdgeArrowType edgeArrowType){
        throw new NotImplementedException();
    }
    @Override
    public boolean evaluate(Context<Graph<V, E>, V> graphVContext) {
        throw new NotImplementedException();
    }
}
