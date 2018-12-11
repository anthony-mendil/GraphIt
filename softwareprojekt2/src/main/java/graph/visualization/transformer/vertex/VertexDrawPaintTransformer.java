package graph.visualization.transformer.vertex;

import org.apache.commons.collections15.Transformer;

import java.awt.*;

/**
 * Defines a functor that transform a vertex into its draw color. The input vertex left unchanged. Its
 * extracting the draw color of a vertex.
 */
public class VertexDrawPaintTransformer<V> implements Transformer<V, Paint> {
    @Override
    public Paint transform(V v) {
        return null;
    }
}
