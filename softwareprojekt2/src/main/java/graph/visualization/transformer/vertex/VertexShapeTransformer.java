package graph.visualization.transformer.vertex;

import edu.uci.ics.jung.visualization.decorators.AbstractVertexShapeTransformer;
import graph.graph.Syndrom;
import graph.graph.Vertex;
import graph.graph.VertexShapeType;
import org.apache.commons.collections15.Transformer;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

/**
 * Defines a functor that transform a vertex into its shape. The input vertex is left unchanged.
 * Its extracting the shape of a vertex.
 *
 * @param <V> The vertex type.
 */
public class VertexShapeTransformer<V> extends AbstractVertexShapeTransformer<V> implements Transformer<V, Shape> {
    private VertexLabelTransformer<V> vertexLabelTransformer = new VertexLabelTransformer();
    private VertexFontTransformer<V> vertexFontTransformer = new VertexFontTransformer<>();

    /**
     * Creates a vertex shape transformer with the specified vertex size.
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

        String label = vertexLabelTransformer.transform(v);
        Font font = vertexFontTransformer.transform(v);
        FontMetrics fontMetrics = Syndrom.getInstance().getVv().getFontMetrics(font);
        Graphics graphics = Syndrom.getInstance().getVv().getGraphics();

        Rectangle2D metrics = fontMetrics.getStringBounds(label, graphics);

        if (shapeType == VertexShapeType.RECTANGLE) {
            double height = metrics.getHeight()+5;
            height = (1+(((Vertex) v).getSize())*0.01)*height;
            double width;
            if (vsf.transform(v) < metrics.getWidth() + 15) {
                width = metrics.getWidth() + 15;
            } else {
                width = vsf.transform(v);
            }
            float arc_size = (float) Math.min(height, width) / 2;
            RoundRectangle2D round = new RoundRectangle2D.Float();
            round.setRoundRect( -(width / 2), -(height / 2),
                    width, height, arc_size, arc_size);
            return round;


        } else if (shapeType == VertexShapeType.ELLIPSE) {
            return factory.getEllipse(v);
        } else {
            float width = vsf.transform(v);
            float height = width * 0.7F;

            float h_offset = -(width / 2);
            float v_offset = -(height / 2);
            return new Ellipse2D.Float(h_offset, v_offset, width, height);
        }
    }
}
