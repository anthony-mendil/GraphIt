package graph.visualization.transformer.edge;

import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.util.Context;
import graph.graph.EdgeArrowType;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.collections15.Transformer;

import java.awt.*;

/**
 * Defines a functor that transform a edge into its edge arrow type. The input edge left unchanged. Its
 * extracting the edge arrow type.
 * @param <E> the edge type
 */
public class EdgeArrowTransformer<V, E> implements Transformer<Context<Graph<V, E>, E>, Shape> {
    /**
     * the edge arrow type which is currently selected
     */
    private static EdgeArrowType edgeArrowType;

    /**
     * the shape of the reinforced arrow
     */
    private Shape reinforcedArrow;

    /**
     * the shape of the extenuating arrow
     */
    private Shape extenuatingArrow;

    /**
     * the shape of the neutral arrow
     */
    private Shape neutralArrow;

    /**
     * Creates a edge arrow transformer. The radius, width, length and notch depth are set.
     *
     * @param radius     the radius of the neutral arrow
     * @param width      the width of the arrow
     * @param length     the height of the arrow
     * @param notchDepth the notch depth of the reinforced arrow
     */
    public EdgeArrowTransformer(int radius, float width, float length, float notchDepth) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Shape transform(Context<Graph<V, E>, E> context) {
        throw new UnsupportedOperationException();
    }
}
