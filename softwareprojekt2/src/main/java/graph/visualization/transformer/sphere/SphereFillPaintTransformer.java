package graph.visualization.transformer.sphere;

import org.apache.commons.collections15.Transformer;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.awt.*;

/**
 * Defines a functor that transform a sphere into its fill color. The input sphere left unchanged. Its extracting the
 * fill color of a sphere.
 */
public class SphereFillPaintTransformer<S> implements Transformer<S, Paint> {
    @Override
    public Paint transform(S s) {
        throw new NotImplementedException();
    }
}
