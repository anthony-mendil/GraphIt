package graph.evaluate.algorithms;

import edu.uci.ics.jung.graph.Graph;
import graph.graph.Vertex;
import lombok.NonNull;
import lombok.Setter;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Set;

/**
 * Defines a functor that performs a predicates test on vertices for filtering the edge arrow types of the edges.
 *
 * @param <V> the vertex type
 * @param <E> the edge type
 */
public class NeighborDetectPredicate<V, E> {
    /**
     * the arrow type to filter for
     */
    @Setter
    @NonNull
    private Graph graph;

    /**
     * Creates a new CycleDetectPredicate.
     *
     * @param pGraph The graph to detect cycles on.
     */
    public NeighborDetectPredicate(Graph pGraph) {
        throw new NotImplementedException();
    }
    /**
     * Evaluates the graph on the given condition.
     * @param pGraph The given graph.
     * @return The set of vertices matching the condition.
     */
    public Set<Vertex> evaluate(Graph pGraph) {
        throw new NotImplementedException();
    }
}
