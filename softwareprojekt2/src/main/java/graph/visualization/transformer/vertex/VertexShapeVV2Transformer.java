package graph.visualization.transformer.vertex;

import edu.uci.ics.jung.visualization.Layer;
import edu.uci.ics.jung.visualization.decorators.AbstractVertexShapeTransformer;
import graph.graph.Syndrom;
import org.apache.commons.collections15.Transformer;

import java.awt.*;
import java.awt.geom.AffineTransform;

/**
 * Defines a functor that transform a vertex into its shape. The input vertex is left unchanged.
 * Its extracting the shape of a vertex.
 * <p>
 * its necessary to have a different shape transformer for the satellite (zoom) view, because the shape as to get scaled
 * as well... if we don't do this, the shape is displayed in a wrong way.
 *
 * @author Nina Unterberg
 *
 * @param <V> The vertex type.
 */
public class VertexShapeVV2Transformer<V> extends AbstractVertexShapeTransformer<V> implements Transformer<V, Shape> {
    /**
     * The VertexShapeTransformer which is needed to transform the shape of the vertex.
     */
    private VertexShapeTransformer<V> vertexShapeTransformer = new VertexShapeTransformer<>();

    @Override
    public Shape transform(V v) {
        AffineTransform transform = Syndrom.getInstance().getVv2().getRenderContext().getMultiLayerTransformer().getTransformer(Layer.LAYOUT).getTransform();
        AffineTransform scaler = new AffineTransform();
        scaler.scale(transform.getScaleX(), transform.getScaleY());
        Shape shape = vertexShapeTransformer.transform(v);
        shape = scaler.createTransformedShape(shape);
        return shape;
    }
}
