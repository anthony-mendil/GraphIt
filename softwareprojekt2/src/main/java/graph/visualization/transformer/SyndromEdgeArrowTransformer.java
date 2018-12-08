package graph.visualization.transformer;

import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.util.Context;
import org.apache.commons.collections15.Transformer;

import java.awt.*;

/**
 * TODO
 */
public class SyndromEdgeArrowTransformer<V, E> implements Transformer<Context<Graph<V, E>, E>, Shape> {
    private Shape reinforcedArrow;
    private Shape extenuatingArrow;
    private Shape neutralArrow;

    /**
     * TODO
     *
     * @param radius TODO
     * @param width TODO
     * @param length TODO
     * @param notchDepth TODO
     */
    public SyndromEdgeArrowTransformer(int radius, float width, float length, float notchDepth) {
        throw new UnsupportedOperationException();
    }

    /**
     * TODO
     *
     * @param context TODO
     * @return Shape
     */
    public Shape transform(Context<Graph<V, E>, E> context) {
        throw new UnsupportedOperationException();
    }
}
