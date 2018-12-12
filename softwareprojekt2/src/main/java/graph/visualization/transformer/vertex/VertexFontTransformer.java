package graph.visualization.transformer.vertex;

import org.apache.commons.collections15.Transformer;

import java.awt.*;

/**
 * Defines a functor that transform a vertex into its annotation font. The input vertex left unchanged. Its extracting
 * the annotation font of a vertex.
 *
 * @param <V> the vertex type
 */
public class VertexFontTransformer<V> implements Transformer<V, Font> {
    @Override
    public Font transform(V v) {
        return null;
    }
}
