package graph.visualization.transformer.edge;

import org.apache.commons.collections15.Transformer;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.awt.*;

public class EdgeStrokeTransformer<E> implements Transformer<E, Stroke> {
    /**
     * Defines a functor that transform a edge into its stroke type. The input edge left unchanged. Its
     * extracting the stroke type of an edge.
     */
    @Override
    public Stroke transform(E e) {
        throw new NotImplementedException();
    }
}
