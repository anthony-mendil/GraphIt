package graph.visualization.transformer.vertex;

import org.apache.commons.collections15.Transformer;

/**
 * Defines a functor that transform a vertex into its annotation. The input vertex left unchanged. Its
 * extracting the annotation of a vertex.
 * @param <V> the vertex type
 */
public class VertexLabelTransformer<V> implements Transformer<V, String> {

    @Override
    public String transform(V v) {
        return null;
    }
}
