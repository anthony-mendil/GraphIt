package graph.visualization.renderers;

import edu.uci.ics.jung.visualization.Layer;
import edu.uci.ics.jung.visualization.RenderContext;
import edu.uci.ics.jung.visualization.transform.shape.GraphicsDecorator;
import graph.graph.Sphere;
import graph.graph.Syndrom;
import graph.visualization.transformer.sphere.*;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 * The SphereRenderer renders all spheres from the syndrom graph.
 */
public class SphereRenderer {
    private SphereDrawPaintTransformer<Sphere> sphereDrawPaintTransformer =  new SphereDrawPaintTransformer<>();
    private SphereFillPaintTransformer<Sphere> sphereFillPaintTransformer =  new SphereFillPaintTransformer<>();
    private SphereFontTransformer<Sphere> sphereFontTransformer = new SphereFontTransformer<>();
    private SphereLabelTransformer<Sphere> sphereLabelTransformer = new SphereLabelTransformer<>();
    private SphereShapeTransformer<Sphere> sphereShapeTransformer =  new SphereShapeTransformer<>();
    private SphereFontSizeTransformer<Sphere> sphereSphereFontSizeTransformer = new SphereFontSizeTransformer<>();

    /**
     * Renders the given sphere.
     * @param pRc The renderContext implemented in JUNG.
     * @param pSphere The sphere that should be rendered.
     */
    public void paintSphere(RenderContext pRc, Sphere pSphere) {
        GraphicsDecorator g2d = pRc.getGraphicsContext();
        Point2D p = pSphere.getCoordinates();
        p = pRc.getMultiLayerTransformer().transform(Layer.LAYOUT, p);
        Shape sphereShape =  new Rectangle2D.Double(p.getX(), p.getY(), pSphere
                .getWidth(), pSphere.getHeight());
        g2d.setPaint(sphereFillPaintTransformer.transform(pSphere));
        g2d.fill(sphereShape);
        g2d.setPaint(sphereDrawPaintTransformer.transform(pSphere));
        SphereStrokeTransformer<Sphere> sphereStrokeTransformer = new SphereStrokeTransformer<>(Syndrom
                .getInstance().getVv());
        g2d.setStroke(sphereStrokeTransformer.transform(pSphere));

        g2d.draw(sphereShape);
        g2d.setFont(sphereFontTransformer.transform(pSphere));
        FontMetrics fontMetrics = g2d.getFontMetrics();
        String annotation = sphereLabelTransformer.transform(pSphere);
        int width = fontMetrics.stringWidth(annotation);
        double sphereWidth =  pSphere.getWidth();

        if (width+10 > sphereWidth){
            if ((width/2)+10 > sphereWidth){
                annotation = shrinkAnnotation(sphereWidth,pSphere.getHeight(), annotation, fontMetrics);
            } else {
                annotation = breakAnnotation(sphereWidth, annotation, fontMetrics);
            }
        }
        int i =1;
        for (String line : annotation.split("\n")){
            Point2D point2D = getAnchorPoint(pSphere, p, fontMetrics.stringWidth(line));
            g2d.drawString(line, (float) point2D.getX(), (float) point2D.getY()+sphereSphereFontSizeTransformer.transform
                    (pSphere)*i++);
        }
    }

    private String shrinkAnnotation(double sphereWidth, double sphereHeight, String annotation, FontMetrics
            fontMetrics){
        String[] lines = breakAnnotation(sphereWidth, annotation, fontMetrics).split("\n");
        int count = (int) sphereHeight / fontMetrics.getHeight();
        StringBuilder label = new StringBuilder();
        for (int i = 0; i < lines.length && i < count; i++){
            if (!lines[i].isEmpty()){
                int c = count -1;
                if (i == c){
                    if (lines[c].length()>=3){
                        lines[c] = lines[c].substring(0, lines[c].length()-3);
                        lines[c] += "...";
                    } else {
                        lines[c] = "...";
                    }
                }
                label.append(lines[i]).append("\n");
            }
        }
        return label.toString();
    }

    private String breakAnnotation(double sphereWidth, String annotation, FontMetrics fontMetrics){
        StringBuilder label = new StringBuilder();
        StringBuilder lengthLabel = new StringBuilder();
        int i = 0;
        for (String line : annotation.split("\\s+")){
            if (fontMetrics.stringWidth(lengthLabel.toString()+line) +10 < sphereWidth){
                lengthLabel.append(line);
            } else {
                if (fontMetrics.stringWidth(line) + 10 >= sphereWidth){
                    char[] chars = line.toCharArray();
                    if (i != 0){
                        lengthLabel.append(" ");
                    }
                    for (char c : chars){
                        if (fontMetrics.stringWidth(lengthLabel.toString()+c) +10 < sphereWidth){
                            lengthLabel.append(c);
                        } else {
                            label.append(lengthLabel).append("\n");
                            lengthLabel.delete(0, lengthLabel.length()).append(c);
                        }
                    }
                } else {
                    lengthLabel.append("\n").append(line);
                }
            }
            i++;
        }
        label.append(lengthLabel);
        return label.toString();
    }

    private Point2D getAnchorPoint(Sphere sphere, Point2D p, int width){
        double sWidth = sphere.getWidth();
        double x = p.getX();
        double labelX;

        if (width+10 < (sWidth)){
            labelX = (x + sWidth/2)-(width/2);
        } else {
            labelX = x+5;
        }
        return new Point2D.Double(labelX, p.getY()+3);
    }
}
