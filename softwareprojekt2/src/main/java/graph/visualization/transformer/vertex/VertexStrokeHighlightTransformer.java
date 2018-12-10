package graph.visualization.transformer.vertex;

import org.apache.commons.collections15.Transformer;

import java.awt.*;

public class VertexStrokeHighlightTransformer<V> implements Transformer<V, Stroke> {
    private Stroke defaultHighlightStrokeVertex;
    @Override
    public Stroke transform(V v) {
        return null;
    }
}
