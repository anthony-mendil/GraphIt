package graph.visualization.transformer.vertex;

import graph.graph.Vertex;
import graph.visualization.control.HelperFunctions;
import org.apache.commons.collections15.Transformer;

import java.awt.*;

/**
 * Defines a functor that transform a vertex into its annotation font. The input vertex is left unchanged.
 * Its extracting the annotation font of a vertex.
 *
 * @author Nina Unterberg
 *
 * @param <V> The vertex type.
 */
public class VertexFontTransformer<V> implements Transformer<V, Font> {
    /**
     * A HelperFunctions object needed to transform into a Font.
     */
    private HelperFunctions helperFunctions = new HelperFunctions();

    @Override
    public Font transform(V v) {
        Vertex vertex = (Vertex) v;
        Font newFont = helperFunctions.returnFont(vertex.getFont());
        return newFont.deriveFont(Font.PLAIN, vertex.getFontSize());
    }
}
