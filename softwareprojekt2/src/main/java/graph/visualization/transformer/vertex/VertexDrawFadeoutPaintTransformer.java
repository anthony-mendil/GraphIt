package graph.visualization.transformer.vertex;

import graph.graph.Vertex;
import graph.visualization.transformer.FadeOutElementsTransition;
import org.apache.commons.collections15.Transformer;

import java.awt.*;

/**
 * Defines a functor that transform a vertex into its draw color. The input vertex is left unchanged.
 * Its extracting the draw color of a vertex.
 *
 * @param <V> The vertex type.
 */
public class VertexDrawFadeoutPaintTransformer<V> implements Transformer<V, Paint> {
    private FadeOutElementsTransition animation;
    private Transformer<V, Paint> transformer;

    public VertexDrawFadeoutPaintTransformer(FadeOutElementsTransition animation, Transformer<V, Paint> transformer) {
        this.animation = animation;
        this.transformer = transformer;
    }

    @Override
    public Paint transform(V v) {
        Vertex vertex = (Vertex) v;
        Color color = (Color) transformer.transform(v);
        return (!vertex.isVisible()) ? new Color(color.getRed(), color.getGreen(), color.getBlue(), (int) (255 - (animation.getFrac() * 255))) : color;
    }
}
