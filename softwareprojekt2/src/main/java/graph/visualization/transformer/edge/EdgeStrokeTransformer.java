package graph.visualization.transformer.edge;

import edu.uci.ics.jung.visualization.RenderContext;
import graph.graph.Edge;
import graph.graph.StrokeType;
import org.apache.commons.collections15.Transformer;

import java.awt.*;

/**
 * Defines a functor that transform an edge into its stroke type. The input edge is left unchanged.
 * Its extracting the stroke type of an edge.
 *
 * @param <E> The edge type.
 */
public class EdgeStrokeTransformer<E> implements Transformer<E, Stroke> {

    float[] dotting = {1.0f, 4.0f};
    float[] dashing = {5.0f};

    @Override
    public Stroke transform(E e) {
         Edge edge = (Edge) e;
         Stroke stroke;
        switch (edge.getStroke()) {
            case BASIC: stroke = new BasicStroke(1);
                break;
            case BASIC_WEIGHT:  stroke = new BasicStroke(3);
                break;
            case DASHED:  stroke = RenderContext.DASHED;
                break;
            case DASHED_WEIGHT:  stroke = new BasicStroke(3.0f,
                    BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 1.0f, RenderContext.dashing, 0f);
                break;
            case DOTTED:  stroke = RenderContext.DOTTED;
                break;
            case DOTTED_WEIGHT:  stroke =  new BasicStroke(3.0f,
                    BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 1.0f, dotting, 0f);
                break;
            default: stroke = new BasicStroke(1);
                break;
        }
        return stroke;
    }
}
