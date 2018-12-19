package graph.visualization.transformer.vertex;

import edu.uci.ics.jung.visualization.decorators.AbstractVertexShapeTransformer;
import org.apache.commons.collections15.Transformer;

import java.awt.*;

/**
 * Defines a functor that transform a vertex into its shape. The input vertex is left unchanged.
 * Its extracting the shape of a vertex.
 *
 * @param <V> The vertex type.
 */
public class VertexShapeTransformer<V> extends AbstractVertexShapeTransformer<V> implements Transformer<V, Shape> {
    /**
     * Creates a vertex shape transformer with the specified vertex size.
     *
     * @param pSizeTransformer The size transformer.
     */
    public VertexShapeTransformer(Transformer<V, Integer> pSizeTransformer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Shape transform(V v) {
        return null;
    }
}
