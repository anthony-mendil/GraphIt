package graph.algorithmen.predicates;

import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.util.Context;
import graph.graph.EdgeArrowType;
import graph.graph.Vertex;
import lombok.NonNull;
import lombok.Setter;
import org.apache.commons.collections15.Predicate;
import org.jgrapht.alg.cycle.CycleDetector;

import java.util.Set;

/**
     * Defines a functor that performs a predicates test on vertices for filtering the edge arrow types of the edges.
     *
     * @param <V> the vertex type
     * @param <E> the edge type
     */
    public class CycleDetectPredicate<V, E> {
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
        public CycleDetectPredicate(Graph pGraph) {
            throw new UnsupportedOperationException();
        }

    /**
     * Evaluates the graph on the given condition.
     * @param pGraph The given graph.
     * @return The set of vertices matching the condition.
     */
    public Set<Vertex> evaluate(Graph pGraph) {
        CycleDetector cycleDetector= new CycleDetector(null);
            throw new UnsupportedOperationException();
        }
    }
