package graph.visualization.transformer.edge;

import org.apache.commons.collections15.Transformer;

import java.awt.*;

/**
 * Defines a functor that transform an edge into its arrow draw color. The input edge is left unchanged.
 * Its extracting the arrow draw color of an edge.
 *
 * @param <E> The edge type.
 */
public class EdgeArrowFillPaintTransformer<E> implements Transformer<E, Paint> {
    @Override
    public Paint transform(E e) {
        throw new UnsupportedOperationException();
    }
}
