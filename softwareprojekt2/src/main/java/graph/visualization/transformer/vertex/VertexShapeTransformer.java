package graph.visualization.transformer.vertex;

import edu.uci.ics.jung.visualization.decorators.AbstractVertexShapeTransformer;
import org.apache.commons.collections15.Transformer;

import java.awt.*;

public class VertexShapeTransformer<V> extends AbstractVertexShapeTransformer<V> implements Transformer<V, Shape> {
    private Shape defaultShapeVertex;
    private int defaultSizeVertex;
    private int defaultRatioVertex;

    public VertexShapeTransformer(Transformer<V, Integer> vsf, Transformer<V, Float> varf) {
        super(vsf, varf);
    }

    @Override
    public Shape transform(V v) {
        return null;
    }
}
