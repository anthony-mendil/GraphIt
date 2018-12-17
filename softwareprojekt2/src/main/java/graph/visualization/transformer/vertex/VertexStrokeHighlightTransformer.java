package graph.visualization.transformer.vertex;

import org.apache.commons.collections15.Transformer;

import java.awt.*;

/**
 * Defines a functor that transform a vertex into its highlight stroke. The input vertex left unchanged. Its extracting
 * the highlight stroke of a vertex.
 *
 * @param <V> the vertex type
 */
public class VertexStrokeHighlightTransformer<V> implements Transformer<V, Stroke> {
    @Override
    public Stroke transform(V v) {
        return null;
    }
}