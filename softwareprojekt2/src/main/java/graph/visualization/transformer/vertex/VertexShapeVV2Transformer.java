package graph.visualization.transformer.vertex;

import edu.uci.ics.jung.visualization.Layer;
import edu.uci.ics.jung.visualization.decorators.AbstractVertexShapeTransformer;
import graph.graph.Syndrom;
import graph.graph.Vertex;
import graph.graph.VertexShapeType;
import graph.visualization.renderers.RenderHelperFunction;
import org.apache.commons.collections15.Transformer;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

/**
 * Defines a functor that transform a vertex into its shape. The input vertex is left unchanged.
 * Its extracting the shape of a vertex.
 *
 * @param <V> The vertex type.
 */
public class VertexShapeVV2Transformer<V> extends AbstractVertexShapeTransformer<V> implements Transformer<V, Shape> {
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
