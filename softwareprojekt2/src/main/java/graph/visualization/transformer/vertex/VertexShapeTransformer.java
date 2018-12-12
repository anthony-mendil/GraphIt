package graph.visualization.transformer.vertex;

import edu.uci.ics.jung.visualization.decorators.AbstractVertexShapeTransformer;
import org.apache.commons.collections15.Transformer;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.awt.*;

/**
 * Defines a functor that transform a vertex into its shape. The input vertex left unchanged. Its
 * extracting the shape of a vertex.
 * @param <V> the vertex type
 */
public class VertexShapeTransformer<V> extends AbstractVertexShapeTransformer<V> implements Transformer<V, Shape> {
    /**
     * creates a vertex shape transformer with with the specified
     * vertex size
     *
     * @param pSizeTransformer the size transformer
     */
    public VertexShapeTransformer(Transformer<V, Integer> pSizeTransformer) {
        throw new NotImplementedException();
    }

    @Override
    public Shape transform(V v) {
        return null;
    }
}
