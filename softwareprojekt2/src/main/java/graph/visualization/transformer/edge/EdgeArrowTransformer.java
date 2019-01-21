package graph.visualization.transformer.edge;

import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.util.Context;
import graph.graph.Edge;
import graph.graph.EdgeArrowType;
import graph.visualization.util.SyndromArrowFactory;
import org.apache.commons.collections15.Transformer;

import java.awt.*;

/**
 * Defines a functor that transform an edge into its edge arrow type. The input edge is left unchanged.
 * Its extracting the edge arrow type.
 *
 * @param <E> The edge type.
 */
public class EdgeArrowTransformer<V, E> implements Transformer<Context<Graph<V, E>, E>, Shape> {

    /**
     * The shape of the reinforced arrow.
     */
    private Shape reinforcedArrow;

    /**
     * The shape of the extenuating arrow.
     */
    private Shape extenuatingArrow;

    /**
     * The shape of the neutral arrow.
     */
    private Shape neutralArrow;

    /**
     * The SyndromArrowFactory to get the right arrow shapes.
     */
    private SyndromArrowFactory factory;

    /**
     * Creates an edge arrow transformer. The radius, width, length and notch depth will be set.
     *
     * @param radius     The radius of the neutral arrow.
     * @param width      The width of the arrow.
     * @param length     The height of the arrow.
     * @param notchDepth The notch depth of the reinforced arrow.
     */
    public EdgeArrowTransformer(int radius, float width, float length, float notchDepth) {
        factory = new SyndromArrowFactory();
        this.reinforcedArrow = factory.getReinforcingEdgeArrow(width, length, notchDepth);
        this.extenuatingArrow = factory.getExtenuatingEdgeArrow(radius);
        this.neutralArrow = factory.getNeutralEdgeArrow();
    }

    @Override
    public Shape transform(Context<Graph<V, E>, E> context) {
        Edge edge = (Edge) context.element;
        if (edge.getArrowType() == EdgeArrowType.EXTENUATING){
            return extenuatingArrow;
        } else if (edge.getArrowType() == EdgeArrowType.REINFORCED){
            return reinforcedArrow;
        } else {
            return  neutralArrow;
        }
    }
}
