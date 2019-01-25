package graph.visualization.transformer.sphere;

import graph.graph.Sphere;
import org.apache.commons.collections15.Transformer;

import java.awt.*;

/**
 * Defines a functor that transform a sphere into its fill color. The input sphere is left unchanged.
 * Its extracting the fill color of a sphere.
 *
 * @param <S> The sphere type.
 */
public class SphereFillPaintTransformer<S> implements Transformer<S, Paint> {
    @Override
    public Paint transform(S s){
        try{
            Sphere sphere = (Sphere) s;
            return sphere.getColor();
        } catch (Exception e){
            throw new IllegalArgumentException();
        }
    }
}
