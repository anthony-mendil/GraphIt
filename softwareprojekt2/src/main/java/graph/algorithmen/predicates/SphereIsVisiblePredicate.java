package graph.algorithmen.predicates;

import org.apache.commons.collections15.Predicate;

/**
 * Defines a functor that performs a predicates test on spheres for filtering the sphere for visibility.
 * @param <S> the sphere type
 */
public class SphereIsVisiblePredicate<S> implements Predicate<S> {

    @Override
    public boolean evaluate(S sphere) {
        return false;
    }
}
