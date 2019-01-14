package graph.visualization.transformer.sphere;

import edu.uci.ics.jung.visualization.Layer;
import graph.graph.Edge;
import graph.graph.Sphere;
import graph.graph.Syndrom;
import graph.graph.Vertex;
import org.apache.commons.collections15.Transformer;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.renderable.RenderContext;

/**
 * Defines a functor that transform a sphere into its shape. The input sphere is left unchanged.
 * Its extracting the shape of a sphere.
 *
 * @param <S> The sphere type.
 */
public class SphereShapeTransformer<S> implements Transformer<S, Shape> {
    @Override
    public Shape transform(S s) {
        try{
            Sphere sphere = (Sphere) s;
            return new Rectangle2D.Double(sphere.getCoordinates().getX(), sphere.getCoordinates().getY(), sphere
                    .getWidth(), sphere.getHeight());
        } catch (Exception e){
            throw new IllegalArgumentException();
        }
    }
}
