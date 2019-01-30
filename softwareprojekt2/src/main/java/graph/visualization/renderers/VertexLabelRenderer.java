package graph.visualization.renderers;

import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.visualization.Layer;
import edu.uci.ics.jung.visualization.RenderContext;
import edu.uci.ics.jung.visualization.renderers.BasicVertexLabelRenderer;
import edu.uci.ics.jung.visualization.transform.shape.GraphicsDecorator;
import graph.graph.Syndrom;
import graph.graph.Vertex;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

public class VertexLabelRenderer<V,E> extends BasicVertexLabelRenderer<V,E> {
    private int maxLength = 160;

    public VertexLabelRenderer() {
        super(Position.CNTR);
    }

    @Override
    public void labelVertex(RenderContext<V, E> rc, Layout<V, E> layout, V v, String label) {
        GraphicsDecorator gD = rc.getGraphicsContext();
        Font font = rc.getVertexFontTransformer().transform(v);
        gD.setFont(font);
        gD.setPaint(Color.BLACK);
        Vertex vertex = (Vertex) v;

        FontMetrics fontMetrics = gD.getFontMetrics();
        String title = rc.getVertexLabelTransformer().transform(v);
        Shape vertexShape = rc.getVertexShapeTransformer().transform(v);

        Point2D vertexCord = rc.getMultiLayerTransformer().transform(Layer.LAYOUT,vertex.getCoordinates());
        AffineTransform xform = AffineTransform.getTranslateInstance(vertexCord.getX(), vertexCord.getY());
        vertexShape = xform.createTransformedShape(vertexShape);

        int width = fontMetrics.stringWidth(title);
        Graphics graphics = Syndrom.getInstance().getVv().getGraphics();
        double height = fontMetrics.getStringBounds(label, graphics).getHeight();

        if (width > maxLength){
            title =  splitAnnotation(title, fontMetrics);
        }

        int i =0;
        double sumHeight =  title.split("\n").length * height;
        for (String line : title.split("\n")){
            Point2D anchor = getAnchorPoint(new Point2D.Double(vertexShape.getBounds2D().getCenterX(), vertexShape.getBounds2D().getCenterY()), fontMetrics.stringWidth(line), sumHeight);
            gD.drawString(line, (float) anchor.getX(), (float) (anchor.getY()+(height*i++)+font.getSize()));
        }
    }

    public Point2D getAnchorPoint(Point2D p, int width, double height){
        double labelX = p.getX() - ((double) width/2);
        double y = p.getY()-height/2;
        return new Point2D.Double(labelX, y);
    }


    public String splitAnnotation(String string, FontMetrics fontMetrics){
        StringBuilder title = new StringBuilder();
        StringBuilder subSequence = new StringBuilder();
        for(int i = 0; i < string.length(); i++){
            if (fontMetrics.stringWidth(subSequence.toString()) > maxLength){
                title.append(subSequence);
                title.append("\n");
                subSequence.delete(0, subSequence.length());
            }
            subSequence.append(string.charAt(i));
        }
        if (subSequence.length() > 0){
            title.append(subSequence);
        }
        return title.toString();
    }
}
