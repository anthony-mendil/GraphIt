package graph.visualization.transformer.edge;

import graph.graph.Edge;
import gui.Values;
import org.apache.commons.collections15.Transformer;

import java.awt.*;

/**
 * Defines a functor that transform an edge into its highlight color. The input edge is left unchanged.
 * Its extracting the highlight color of an edge.
 * @param <E> the type of the edge
 */
public class EdgeHighlightTransformer<E> implements Transformer<E, Paint> {
    @Override
    public Paint transform(E e) {
        Edge edge = (Edge) e;
        return edge.isHighlighted() ? Values.getInstance().getHighlightPaint() : edge.getColor();
    }
}
