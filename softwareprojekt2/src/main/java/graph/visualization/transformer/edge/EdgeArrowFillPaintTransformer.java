package graph.visualization.transformer.edge;

import org.apache.commons.collections15.Transformer;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.awt.*;

/**
 * Defines a functor that transform a edge into its edge arrow fill color. The input edge left unchanged. Its extracting
 * the arrow fill color of an edge.
 */
public class EdgeArrowFillPaintTransformer<E> implements Transformer<E, Paint> {
    @Override
    public Paint transform(E e) {
        throw new NotImplementedException();
    }
}
