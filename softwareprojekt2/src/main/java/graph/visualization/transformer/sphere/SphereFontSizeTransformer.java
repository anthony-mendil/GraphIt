package graph.visualization.transformer.sphere;

import graph.graph.Sphere;
import org.apache.commons.collections15.Transformer;

/**
 * Defines a functor that transform a sphere into its font size. The input sphere is left unchanged.
 * Its extracting the font size of a sphere.
 *
 * @author Nina Unterberg
 *
 * @param <S> The type of the sphere
 */
public class SphereFontSizeTransformer<S> implements Transformer<S, Integer> {
    @Override
    public Integer transform(S s) {
        try {
            Sphere sphere = (Sphere) s;
            return sphere.getFontSize();
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }
}
