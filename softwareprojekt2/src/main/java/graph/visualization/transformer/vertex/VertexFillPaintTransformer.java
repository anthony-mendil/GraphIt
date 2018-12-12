package graph.visualization.transformer.vertex;

import org.apache.commons.collections15.Transformer;

import java.awt.*;

/**
 * Defines a functor that transform a vertex into its fill color. The input vertex left unchanged. Its extracting the
 * fill color of a vertex.
 *
 * @param <V> the vertex type
 */
public class VertexFillPaintTransformer<V> implements Transformer<V, Paint> {
    @Override
    public Paint transform(V v) {
        return null;
    }
}
