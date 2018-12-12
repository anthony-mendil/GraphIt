package graph.visualization.transformer.sphere;

import org.apache.commons.collections15.Transformer;

/**
 * Defines a functor that transform a sphere into its annotation. The input sphere left unchanged. Its extracting the
 * annotation of a sphere.
 *
 * @param <S> the sphere type
 */
public class SphereLabelTransformer<S> implements Transformer<S, String> {
    @Override
    public String transform(S s) {
        throw new UnsupportedOperationException();
    }
}
