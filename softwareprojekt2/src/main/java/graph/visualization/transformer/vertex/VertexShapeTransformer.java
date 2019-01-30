package graph.visualization.transformer.vertex;

import edu.uci.ics.jung.visualization.decorators.AbstractVertexShapeTransformer;
import graph.graph.Syndrom;
import graph.graph.Vertex;
import graph.graph.VertexShapeType;
import graph.visualization.renderers.VertexLabelRenderer;
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

        double width;

        double minWidth;
        minWidth = (metrics.getWidth() < 160) ? metrics.getWidth() : 160;
        width = (vsf.transform(v) < minWidth + 15) ?  minWidth + 15 : vsf.transform(v);

        double height = metrics.getHeight() + 5;
        VertexLabelRenderer vertexLabelRenderer = new VertexLabelRenderer();
        String title = vertexLabelRenderer.splitAnnotation(label, fontMetrics);

        int stringsLenght = title.split("\n").length;
        double minHeight = stringsLenght* height;
        height = (1 + (vsf.transform(v) * 0.01)) * height;

        if (height < minHeight) {
            height = minHeight;
        }

        if (shapeType == VertexShapeType.RECTANGLE) {
            float arcSize = (float) Math.min(height, width) / 2;
            RoundRectangle2D round = new RoundRectangle2D.Float();
            round.setRoundRect(-(width / 2), -(height / 2),
                    width+font.getSize(), height, arcSize, arcSize);
            return round;


        } else {
            height = height + stringsLenght*(font.getSize()*(0.5));
            width = width + stringsLenght*(font.getSize()*0.5);
            double hOffset = -(width / 2);
            double vOffset = -(height / 2);
            return new Ellipse2D.Double(hOffset, vOffset, width, height);
        }
    }
}
