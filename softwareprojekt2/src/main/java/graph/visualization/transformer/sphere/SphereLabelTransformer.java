package graph.visualization.transformer.sphere;

import org.apache.commons.collections15.Transformer;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Defines a functor that transform a sphere into its annotation. The input sphere left unchanged. Its extracting the
 * annotation of a sphere.
 */
public class SphereLabelTransformer<S> implements Transformer<S, String> {
    @Override
    public String transform(S s) {
        throw new NotImplementedException();
    }
}
