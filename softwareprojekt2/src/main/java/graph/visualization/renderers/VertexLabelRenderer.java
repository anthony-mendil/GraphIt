package graph.visualization.renderers;

import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.util.Context;
import edu.uci.ics.jung.visualization.Layer;
import edu.uci.ics.jung.visualization.RenderContext;
import edu.uci.ics.jung.visualization.renderers.BasicVertexLabelRenderer;
import edu.uci.ics.jung.visualization.transform.shape.GraphicsDecorator;
import graph.graph.Edge;
import graph.graph.Syndrom;
import graph.graph.Vertex;
import graph.visualization.SyndromVisualisationViewer;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

public class VertexLabelRenderer<V, E> extends BasicVertexLabelRenderer<V, E> {
    private int maxLength = 160;
    private RenderHelperFunction renderHelperFunction = new RenderHelperFunction();

    public VertexLabelRenderer() {
        super(Position.CNTR);
    }

    @Override
    public void labelVertex(RenderContext<V, E> rc, Layout<V, E> layout, V v, String label) {
        if (!rc.getVertexIncludePredicate().evaluate(Context.<Graph<V, E>, V>getInstance(layout.getGraph(), v))) {
            return;
        }
        GraphicsDecorator gD = rc.getGraphicsContext();
        Font font = rc.getVertexFontTransformer().transform(v);
        gD.setFont(font);
        gD.setPaint(Color.BLACK);
        Vertex vertex = (Vertex) v;
        Shape vertexShape = rc.getVertexShapeTransformer().transform(v);
        FontMetrics fontMetrics = gD.getFontMetrics();
        String title = rc.getVertexLabelTransformer().transform(v);
        int stringWidth = fontMetrics.stringWidth(title);

        // falls die größe kleiner als die länge des textes, dann text auf 160 breite und größe passt sich an
        //
        // falls die Größe größer als die länge des textes, dann text auf die breite des shape -20
        // moin


        String annotation;
        if (vertexShape.getBounds2D().getWidth() < stringWidth) {
            annotation = renderHelperFunction.shrinkAnnotation(160, vertexShape.getBounds2D().getHeight(), title, fontMetrics);
        } else {
            annotation = renderHelperFunction.shrinkAnnotation(vertexShape.getBounds2D().getWidth(), vertexShape.getBounds2D().getHeight(), title, fontMetrics);
        }

        Graphics graphics = Syndrom.getInstance().getVv().getGraphics();
        double height = fontMetrics.getStringBounds(annotation, graphics).getHeight();
        Point2D vertexCord = rc.getMultiLayerTransformer().transform(Layer.LAYOUT, vertex.getCoordinates());
        AffineTransform xform = AffineTransform.getTranslateInstance(vertexCord.getX(), vertexCord.getY());
        vertexShape = xform.createTransformedShape(vertexShape);
        double sumHeight = annotation.split("\n").length * height;


        int i = 0;
        for (String line : annotation.split("\n")) {
            Point2D anchor = getAnchorPoint(new Point2D.Double(vertexShape.getBounds2D().getCenterX(), vertexShape.getBounds2D().getCenterY()), fontMetrics.stringWidth(line), sumHeight);
            SyndromVisualisationViewer<Vertex, Edge> vv = Syndrom.getInstance().getVv();
            Color oldColor = gD.getColor();
            gD.setPaint(vv.getVertexFontColorTransformer().transform(vertex));
            gD.drawString(line, (float) anchor.getX(), (float) (anchor.getY() + (height * i++) + font.getSize()));
            gD.setPaint(oldColor);
        }

    }

    public Point2D getAnchorPoint(Point2D p, int width, double height) {
        double labelX = p.getX() - ((double) width / 2);
        double y = p.getY() - height / 2;
        return new Point2D.Double(labelX, y);
    }
}
