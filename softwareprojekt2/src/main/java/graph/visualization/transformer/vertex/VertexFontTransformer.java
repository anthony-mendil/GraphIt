package graph.visualization.transformer.vertex;

import graph.graph.Vertex;
import org.apache.commons.collections15.Transformer;

import java.awt.*;

/**
 * Defines a functor that transform a vertex into its annotation font. The input vertex is left unchanged.
 * Its extracting the annotation font of a vertex.
 *
 * @param <V> The vertex type.
 */
public class VertexFontTransformer<V> implements Transformer<V, Font> {
    @Override
    public Font transform(V v) {
        Vertex vertex = (Vertex) v;
        try{
            return new Font(vertex.getFont(), Font.PLAIN, vertex.getFontSize());
        } catch (Exception e){
            throw new IllegalArgumentException();
        }
    }
}
