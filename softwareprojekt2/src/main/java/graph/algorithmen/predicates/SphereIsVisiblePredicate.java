package graph.algorithmen.predicates;

import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.util.Context;
import org.apache.commons.collections15.Predicate;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Defines a functor that performs a predicates test on spheres for filtering the sphere for visibility.
 */
public class SphereIsVisiblePredicate<S>  implements Predicate<S> {

    @Override
    public boolean evaluate(S sphere) {
        return false;
    }
}
