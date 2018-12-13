package graph.evaluate_algorithms;

import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.util.Context;
import graph.graph.EdgeArrowType;
import graph.graph.Vertex;
import lombok.NonNull;
import lombok.Setter;
import org.apache.commons.collections15.Predicate;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

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
            throw new NotImplementedException();
        }

        public Set<Vertex> evaluate(Graph pGraph) {
            throw new NotImplementedException();
        }
    }
