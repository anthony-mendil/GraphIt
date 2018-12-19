package graph.visualization.transformer.vertex;

import org.apache.commons.collections15.Transformer;

import java.awt.*;

/**
 * Defines a functor that transform a vertex into its stroke. The input vertex is left unchanged.
 * Its extracting the stroke of a vertex.
 *
 * @param <V> The vertex type.
 */
public class VertexStrokeTransformer<V> implements Transformer<V, Stroke> {

    @Override
    public Stroke transform(V v) {
        return null;
    }
}
