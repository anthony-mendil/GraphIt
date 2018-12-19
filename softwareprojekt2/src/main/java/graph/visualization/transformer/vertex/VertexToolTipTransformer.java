package graph.visualization.transformer.vertex;

import org.apache.commons.collections15.Transformer;

/**
 * Defines a functor that transform a vertex into tooltip. The input vertex is left unchanged.
 * Its extracting the tooltip of a vertex.
 *
 * @param <V> The vertex type.
 */
public class VertexToolTipTransformer<V> implements Transformer<V, String> {
    @Override
    public String transform(V v) {
        return null;
    }
}
