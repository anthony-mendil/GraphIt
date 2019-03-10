package graph.visualization.transformer.edge;

import graph.graph.Edge;
import org.apache.commons.collections15.Transformer;

import java.awt.*;

/**
 * Defines a functor that transform an edge into its edge arrow fill color. The input edge is left unchanged.
 * Its extracting the arrow fill color of an edge.
 *
 * @author Nina Unterberg
 *
 * @param <E> The edge type.
 */
public class EdgeFillPaintTransformer<E> implements Transformer<E, Paint> {
    @Override
    public Paint transform(E e) {
        Edge edge = (Edge) e;
        return edge.getColor();
    }
}
