package graph.visualization.transformer.vertex;

import edu.uci.ics.jung.visualization.decorators.AbstractVertexShapeTransformer;
import graph.graph.Vertex;
import graph.graph.VertexShapeType;
import org.apache.commons.collections15.Transformer;

import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * Defines a functor that transform a vertex into its shape. The input vertex is left unchanged.
 * Its extracting the shape of a vertex.
 *
 * @param <V> The vertex type.
 */
public class VertexShapeTransformer<V> extends AbstractVertexShapeTransformer<V> implements Transformer<V, Shape> {
    /**
     * Creates a vertex shape transformer with the specified vertex size.
     *
     */
    public VertexShapeTransformer() {
        setSizeTransformer(v -> {
            Vertex vertex = (Vertex) v;
            return vertex.getSize();
        });
    }

    @Override
    public Shape transform(V v) {
        Vertex vertex = (Vertex) v;
        VertexShapeType shapeType = vertex.getShape();
        if (shapeType == VertexShapeType.RECTANGLE){
           return factory.getRoundRectangle(v);
        } else if (shapeType == VertexShapeType.ELLIPSE){
            return factory.getEllipse(v);
        } else {
            float width = vsf.transform(v);
            float height = width * 0.7F;

            float h_offset = -(width / 2);
            float v_offset = -(height / 2);
            return new  Ellipse2D.Float(h_offset, v_offset, width, height);
        }
    }
}
