package graph.visualization.transformer.vertex;

import graph.graph.Vertex;
import graph.visualization.transformer.FadeOutElementsTransition;
import org.apache.commons.collections15.Transformer;

import java.awt.*;

/**
 * Defines a functor that transform a vertex into its fill color. The input vertex is left unchanged.
 * Its extracting the fill color of a vertex.
 *
 * @param <V> The vertex type.
 */
public class VertexFadeoutPaintTransformer<V> implements Transformer<V, Paint> {
    private FadeOutElementsTransition animation;
    private Transformer<V, Paint> transformer;
    public VertexFadeoutPaintTransformer(FadeOutElementsTransition animation, Transformer<V, Paint> transformer){
        this.animation  = animation;
        this.transformer = transformer;
    }

    @Override
    public Paint transform(V v) {
        Vertex vertex = (Vertex) v;
        Color color = (Color)transformer.transform(v);
        return (!vertex.isVisible()) ? new Color(color.getRed(), color.getGreen(), color.getBlue(), (int) (255 - (animation.frac*255))) : color;
    }
}
