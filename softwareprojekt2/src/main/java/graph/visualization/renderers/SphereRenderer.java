package graph.visualization.renderers;

import edu.uci.ics.jung.visualization.Layer;
import edu.uci.ics.jung.visualization.RenderContext;
import edu.uci.ics.jung.visualization.transform.shape.GraphicsDecorator;
import graph.graph.Sphere;
import graph.graph.Syndrom;
import graph.visualization.transformer.sphere.*;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

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
    private RenderHelperFunction renderHelperFunction = new RenderHelperFunction();

    /**
     * Renders the given sphere.
     * @param pRc The renderContext implemented in JUNG.
     * @param pSphere The sphere that should be rendered.
     */
    public void paintSphere(RenderContext pRc, Sphere pSphere) {
        GraphicsDecorator g2d = pRc.getGraphicsContext();

        AffineTransform transform = pRc.getMultiLayerTransformer().getTransformer(Layer.LAYOUT).getTransform();

        Shape sphereShape = sphereShapeTransformer.transform(pSphere);
        sphereShape = transform.createTransformedShape(sphereShape);

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
        double sphereWidth =  sphereShape.getBounds().getWidth();

        if (width+10 > sphereWidth){
            if ((width/2)+10 > sphereWidth){
                annotation = renderHelperFunction.shrinkAnnotation(sphereWidth,pSphere.getHeight(), annotation, fontMetrics);
            } else {
                annotation = renderHelperFunction.breakAnnotation(sphereWidth, annotation, fontMetrics);
            }
        }
        int i =1;
        for (String line : annotation.split("\n")){
            Point2D point2D = getAnchorPoint(sphereShape, sphereShape.getBounds().getLocation(), fontMetrics.stringWidth(line));
            g2d.drawString(line, (float) point2D.getX(), (float) point2D.getY()+sphereSphereFontSizeTransformer.transform
                    (pSphere)*i++);
        }
    }



    public Point2D getAnchorPoint(Shape sphereShape, Point2D p, int width){
        double sWidth = sphereShape.getBounds().getWidth();
        double x = p.getX();
        double labelX;

        if (width+10 < (sWidth)){
            labelX = (x + sWidth/2) - ((double) width/2);
        } else {
            labelX = x+5;
        }
        return new Point2D.Double(labelX, p.getY()+3);
    }
}
