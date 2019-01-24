package graph.visualization.transformer.vertex;

import graph.graph.Vertex;
import org.apache.commons.collections15.Transformer;

import java.awt.*;

/**
 * Defines a functor that transform a vertex into its draw color. The input vertex is left unchanged.
 * Its extracting the draw color of a vertex.
 *
 * @param <V> The vertex type.
 */
public class VertexDrawPaintTransformer<V> implements Transformer<V, Paint> {
    @Override
    public Paint transform(V v){
        Vertex vertex = (Vertex) v;
        return vertex.getDrawColor();
    }
}
