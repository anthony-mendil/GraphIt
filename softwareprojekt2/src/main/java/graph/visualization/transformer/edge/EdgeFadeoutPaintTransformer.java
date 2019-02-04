package graph.visualization.transformer.edge;

import edu.uci.ics.jung.graph.util.Pair;
import graph.graph.Edge;
import graph.graph.Syndrom;
import graph.graph.SyndromGraph;
import graph.graph.Vertex;
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
    private FadeOutElementsTransition animation;
    private Transformer<E, Paint> transformer;
    public EdgeFadeoutPaintTransformer(FadeOutElementsTransition animation, Transformer<E, Paint> transformer){
        this.animation  = animation;
        this.transformer = transformer;
    }

    @Override
    public Paint transform(E e) {
        Edge edge = (Edge) e;
        Color color = (Color)transformer.transform(e);

        SyndromGraph<Vertex, Edge> g = (SyndromGraph<Vertex, Edge>) Syndrom.getInstance().getVv().getGraphLayout().getGraph();
        Pair<Vertex> pair = g.getEndpoints(edge);
        if (!pair.getSecond().isVisible() || !pair.getFirst().isVisible()){
            return new Color(color.getRed(), color.getGreen(), color.getBlue(), (int) (255 - (animation.frac*255)));
        }
        return (!edge.isVisible()) ? new Color(color.getRed(), color.getGreen(), color.getBlue(), (int) (255 - (animation.frac*255))) : color;
    }
}
