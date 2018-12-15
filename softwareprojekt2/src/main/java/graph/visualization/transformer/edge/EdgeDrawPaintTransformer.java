package graph.visualization.transformer.edge;

import org.apache.commons.collections15.Transformer;

import java.awt.*;

/**
 * Defines a functor that transform a edge into its edge draw color. The input edge left unchanged. Its extracting the
 * draw color of an edge.
 *
 * @param <E> the edge type
 */
public class EdgeDrawPaintTransformer<E> implements Transformer<E, Paint> {
    @Override
    public Paint transform(E e) {
        throw new UnsupportedOperationException();
    }
}