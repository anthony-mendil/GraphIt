package graph.visualization.transformer.vertex;

import graph.graph.FadeType;
import graph.graph.Vertex;
import graph.visualization.transformer.FadeOutElementsTransition;
import org.apache.commons.collections15.Transformer;

import java.awt.*;

/**
 * Defines a functor that transform a vertex into its fade out color. The input vertex is left unchanged.
 * Its extracting the fade out color of a vertex.
 *
 * @param <V> The vertex type.
 */
public class VertexFadeoutPaintTransformer<V> implements Transformer<V, Paint> {
    /**
     * The FadeOutElementsTransition providing animation details.
     */
    private final FadeOutElementsTransition animation;
    /**
     * The Transformer needed to determine the paint of the vertex.
     */
    private final Transformer<V, Paint> transformer;
    /**
     * The FadeType determining which kind of fading gets used.
     */
    private final FadeType fadeType;

    /**
     * Creates a new VertexFadeoutPaintTransformer that sets this FadeOutElementsTransition, Transformer and FadeType.
     *
     * @param animation   The animation, defining the transparency of the color.
     * @param transformer The transformer returning the right edge color.
     * @param fadeType    The fade type.
     */
    public VertexFadeoutPaintTransformer(FadeOutElementsTransition animation, Transformer<V, Paint> transformer, FadeType fadeType) {
        this.animation = animation;
        this.transformer = transformer;
        this.fadeType = fadeType;
    }

    @Override
    public Paint transform(V v) {
        Vertex vertex = (Vertex) v;
        Color color = (Color) transformer.transform(v);
        double value = (fadeType == FadeType.ACTIVATE) ? 255 - (animation.getValue() * 255) : animation.getValue() * 255;
        return (!vertex.isVisible()) ? new Color(color.getRed(), color.getGreen(), color.getBlue(), (int) value) : color;
    }
}
