package graph.visualization.transformer.edge;

import edu.uci.ics.jung.graph.util.Pair;
import graph.graph.*;
import graph.visualization.transformer.FadeOutElementsTransition;
import org.apache.commons.collections15.Transformer;

import java.awt.*;

/**
 * Defines a functor that transform a vertex into its fill color. The input vertex is left unchanged.
 * Its extracting the fill color of a vertex.
 *
 * @param <E> The edge type.
 */
public class EdgeFadeoutPaintTransformer<E> implements Transformer<E, Paint> {
    private final FadeOutElementsTransition animation;
    private final Transformer<E, Paint> transformer;
    private final FadeType fadeType;
    public EdgeFadeoutPaintTransformer(FadeOutElementsTransition animation, Transformer<E, Paint> transformer, FadeType fadeType){
        this.animation  = animation;
        this.transformer = transformer;
        this.fadeType = fadeType;
    }

    @Override
    public Paint transform(E e) {
        Edge edge = (Edge) e;
        Color color = (Color)transformer.transform(e);
        double fracValue  = (fadeType == FadeType.ACTIVATE) ?  255 - (animation.getFrac()*255) : animation.getFrac()*255;

        SyndromGraph<Vertex, Edge> g = (SyndromGraph<Vertex, Edge>) Syndrom.getInstance().getVv().getGraphLayout().getGraph();
        Pair<Vertex> pair = g.getEndpoints(edge);
        if (!pair.getSecond().isVisible() || !pair.getFirst().isVisible()){
            return new Color(color.getRed(), color.getGreen(), color.getBlue(), (int) fracValue);
        }
        return (!edge.isVisible()) ? new Color(color.getRed(), color.getGreen(), color.getBlue(), (int) fracValue) : color;
    }
}
