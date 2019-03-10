package graph.visualization.transformer.sphere;

import org.apache.commons.collections15.Transformer;

import java.awt.*;

/**
 * Defines a functor that transform a sphere into its draw color. The input sphere is left unchanged.
 * Its extracting the draw color of a sphere.
 *
 * @author Nina Unterberg
 *
 * @param <S> The sphere type.
 */
public class SphereDrawPaintTransformer<S> implements Transformer<S, Paint> {
    @Override
    public Paint transform(S s) {
        return Color.BLACK;

    }
}
