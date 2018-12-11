package graph.visualization.transformer.sphere;

import org.apache.commons.collections15.Transformer;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.awt.*;

/**
 * Defines a functor that transform a sphere into its shape. The input sphere left unchanged. Its extracting the shape
 * of a sphere.
 */
public class SphereShapeTransformer<S> implements Transformer<S, Shape> {
    @Override
    public Shape transform(S s) {
        throw new NotImplementedException();
    }
}
