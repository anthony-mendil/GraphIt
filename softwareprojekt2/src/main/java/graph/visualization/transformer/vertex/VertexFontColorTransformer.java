package graph.visualization.transformer.vertex;

import graph.graph.Syndrom;
import graph.graph.Vertex;
import graph.visualization.renderers.RenderHelperFunction;
import org.apache.commons.collections15.Transformer;

import java.awt.*;

/**
 * Defines a functor that transform a vertex into its fill color. The input vertex is left unchanged.
 * Its extracting the fill color of a vertex.
 *
 * @param <V> The vertex type.
 */
public class VertexFontColorTransformer<V> implements Transformer<V, Paint> {
    /**
     * The RenderHelperFunction object needed to determine the vertex font color.
     */
    private RenderHelperFunction renderHelperFunction = new RenderHelperFunction();

    @Override
    public Paint transform(V v) {
        Vertex vertex = (Vertex) v;
        Color color = (Color) Syndrom.getInstance().getVv().getRenderContext().getVertexFillPaintTransformer().transform(vertex);
        return renderHelperFunction.getLuminanceColor(color);
    }
}
