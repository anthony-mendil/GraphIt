package graph.visualization.renderers;

import edu.uci.ics.jung.visualization.RenderContext;
import edu.uci.ics.jung.visualization.transform.shape.GraphicsDecorator;
import graph.graph.Sphere;
import graph.visualization.transformer.sphere.*;

import java.awt.*;

/**
 * The SphereRenderer renders all spheres from the syndrom graph.
 */
public class SphereRenderer {
    private SphereDrawPaintTransformer<Sphere> sphereDrawPaintTransformer =  new SphereDrawPaintTransformer();
    private SphereFillPaintTransformer<Sphere> sphereFillPaintTransformer =  new SphereFillPaintTransformer();
    private SphereFontTransformer sphereFontTransformer = new SphereFontTransformer();
    private SphereLabelTransformer sphereLabelTransformer = new SphereLabelTransformer();
    private SphereShapeTransformer<Sphere> sphereShapeTransformer =  new SphereShapeTransformer<>();

    /**
     * Renders the given sphere.
     * @param pRc The renderContext implemented in JUNG.
     * @param pSphere The sphere that should be rendered.
     */
    public void paintSphere(RenderContext pRc, Sphere pSphere) {
        GraphicsDecorator g2d = pRc.getGraphicsContext();
        Shape sphereShape = sphereShapeTransformer.transform(pSphere);
        g2d.setPaint(sphereFillPaintTransformer.transform(pSphere));
        g2d.fill(sphereShape);
        g2d.setPaint(sphereDrawPaintTransformer.transform(pSphere));
        g2d.draw(sphereShape);

    }
}
