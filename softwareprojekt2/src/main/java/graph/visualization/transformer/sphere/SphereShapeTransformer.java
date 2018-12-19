package graph.visualization.transformer.sphere;

import org.apache.commons.collections15.Transformer;

import java.awt.*;

/**
 * Defines a functor that transform a sphere into its shape. The input sphere is left unchanged.
 * Its extracting the shape of a sphere.
 *
 * @param <S> The sphere type.
 */
public class SphereShapeTransformer<S> implements Transformer<S, Shape> {
    @Override
    public Shape transform(S s) {
        throw new UnsupportedOperationException();
    }
}
