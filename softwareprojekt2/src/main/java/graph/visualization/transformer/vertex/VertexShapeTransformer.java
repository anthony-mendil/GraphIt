package graph.visualization.transformer.vertex;

import edu.uci.ics.jung.visualization.decorators.AbstractVertexShapeTransformer;
import org.apache.commons.collections15.Transformer;

import java.awt.*;

/**
 * Defines a functor that transform a vertex into its shape. The input vertex left unchanged. Its
 * extracting the shape of a vertex.
 * @param <V> the vertex type
 */
public class VertexShapeTransformer<V> extends AbstractVertexShapeTransformer<V> implements Transformer<V, Shape> {
    /**
     *
     * @param vsf
     * @param varf
     */
    public VertexShapeTransformer(Transformer<V, Integer> vsf, Transformer<V, Float> varf) {
        super(vsf, varf);
    }

    @Override
    public Shape transform(V v) {
        return null;
    }
}
