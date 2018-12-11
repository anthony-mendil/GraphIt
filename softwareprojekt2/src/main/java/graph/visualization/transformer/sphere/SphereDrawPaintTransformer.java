package graph.visualization.transformer.sphere;

import org.apache.commons.collections15.Transformer;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.awt.*;

/**
 * Defines a functor that transform a sphere into its draw color. The input sphere left unchanged. Its extracting the
 * draw color of a sphere.
 * @param <S> the sphere type
 */
public class SphereDrawPaintTransformer<S> implements Transformer<S, Paint> {
    @Override
    public Paint transform(S s) {
        throw new NotImplementedException();
    }
}
