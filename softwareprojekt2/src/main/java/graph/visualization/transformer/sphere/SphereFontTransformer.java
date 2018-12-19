package graph.visualization.transformer.sphere;

import org.apache.commons.collections15.Transformer;

import java.awt.*;

/**
 * Defines a functor that transform a sphere into its annotation font. The input sphere is left unchanged.
 * Its extracting the font annotation of a sphere.
 *
 * @param <S> The sphere type.
 */
public class SphereFontTransformer<S> implements Transformer<S, Font> {
    @Override
    public Font transform(S s) {
        throw new UnsupportedOperationException();
    }
}
