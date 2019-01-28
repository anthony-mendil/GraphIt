package graph.visualization.transformer.edge;

import graph.graph.Edge;
import org.apache.commons.collections15.Transformer;

import java.awt.*;

public class EdgeHighlightTransformer<E> implements Transformer<E, Paint> {
    @Override
    public Paint transform(E e) {
        Edge edge = (Edge) e;

        return edge.isHasAnchor() ? new Color(204, 0,0) : edge.getColor();
    }
}
