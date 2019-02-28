package graph.visualization.transformer.vertex;

import graph.graph.FadeType;
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
    private final FadeOutElementsTransition animation;
    private final Transformer<V, Paint> transformer;
    private final FadeType fadeType;

    public VertexFadeoutPaintTransformer(FadeOutElementsTransition animation, Transformer<V, Paint> transformer, FadeType fadeType) {
        this.animation = animation;
        this.transformer = transformer;
        this.fadeType = fadeType;
    }

    @Override
    public Paint transform(V v) {
        Vertex vertex = (Vertex) v;
        Color color = (Color) transformer.transform(v);
        double fracValue = (fadeType == FadeType.ACTIVATE) ? 255 - (animation.getFrac() * 255) : animation.getFrac() * 255;
        return (!vertex.isVisible()) ? new Color(color.getRed(), color.getGreen(), color.getBlue(), (int) fracValue) : color;
    }
}
