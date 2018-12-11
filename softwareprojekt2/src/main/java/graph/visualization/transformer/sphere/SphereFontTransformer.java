package graph.visualization.transformer.sphere;

import org.apache.commons.collections15.Transformer;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.awt.*;

/**
 * Defines a functor that transform a sphere into its annotation font. The input sphere left unchanged. Its extracting
 * the font annotation of a sphere.
 * @param <S> the sphere type
 */
public class SphereFontTransformer<S> implements Transformer<S, Font> {
    @Override
    public Font transform(S s) {
        throw new NotImplementedException();
    }
}
