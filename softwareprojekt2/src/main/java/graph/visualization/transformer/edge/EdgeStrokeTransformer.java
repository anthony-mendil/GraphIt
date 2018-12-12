package graph.visualization.transformer.edge;

import org.apache.commons.collections15.Transformer;

import java.awt.*;

/**
 * Defines a functor that transform a edge into its stroke type. The input edge left unchanged. Its extracting the
 * stroke type of an edge.
 *
 * @param <E> the edge type
 */
public class EdgeStrokeTransformer<E> implements Transformer<E, Stroke> {

    @Override
    public Stroke transform(E e) {
        throw new UnsupportedOperationException();
    }
}
