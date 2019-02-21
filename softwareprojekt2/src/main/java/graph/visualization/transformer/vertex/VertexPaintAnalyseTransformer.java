package graph.visualization.transformer.vertex;

import graph.graph.Vertex;
import gui.Values;
import org.apache.commons.collections15.Transformer;

import java.awt.*;

/**
 * Defines a functor that transform a vertex into highlight color. The input vertex is left unchanged.
 * Its extracting the highlight color of a vertex.
 *
 * @param <V> The vertex type
 */
public class VertexPaintHighlightTransformer<V> implements Transformer<V, Paint> {
    @Override
    public Paint transform(V v) {
        Vertex vertex = (Vertex) v;
        return vertex.isHighlighted() ? Values.getInstance().getHighlightPaint() : vertex.getFillColor();
    }
}
