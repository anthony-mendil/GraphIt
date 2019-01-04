package graph.visualization.picking;

import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.visualization.Layer;
import edu.uci.ics.jung.visualization.VisualizationServer;
import edu.uci.ics.jung.visualization.picking.ShapePickSupport;
import graph.graph.Edge;
import graph.graph.Sphere;
import graph.graph.SyndromGraph;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * SyndromPickSupport extends the ShapePickSupport with the option to pick spheres and arrows from edges.
 */
public class SyndromPickSupport<V, E> extends ShapePickSupport {
    /**
     * The visualisation server.
     */
    private VisualizationServer<V,E> pVisualizationServer;

    /**
     * Creates a <code>SyndromPickSupport</code> for the <code>vv</code> VisualizationServer. The
     * <code>VisualizationServer</code> is used to access properties of the current visualization (layout, renderer,
     * coordinate transformations, vertex/edge shapes, etc.).
     *
     * @param vv Source of the current <code>Layout</code>.
     */
    public SyndromPickSupport(VisualizationServer<V, E> vv) {
        super(vv);
    }

    /**
     * Iterates over spheres, checking to see if x,y is contained in the spheres shape.
     *
     * @param x      The x- coordinate.
     * @param y      The y- coordinate.
     * @return The picked sphere.
     */
    public Sphere getSphere(double x, double y) {
        Sphere sphaereContains = null;
        Point2D ip = vv.getRenderContext().getMultiLayerTransformer().inverseTransform(Layer.VIEW,
                new Point2D.Double(x, y));
        x = ip.getX();
        y = ip.getY();

        try {
            SyndromGraph g = (SyndromGraph) vv.getGraphLayout().getGraph();
            List<Sphere> list = g.getSpheres();
            if (list != null){
                for (Object aSet : list) {
                    Sphere s = (Sphere) aSet;
                    Shape rec = new Rectangle2D.Double(s.getCoordinates().getX(), s.getCoordinates().getY(), s.getWidth(),
                            s.getHeight());
                    if (rec.contains(x, y)) {
                        sphaereContains = s;
                        break;
                    }
                }
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sphaereContains;
    }

    /**
     * Iterates over edges, checking to see if x,y is contained in a edge arrow.
     *
     * @param layout The current layout.
     * @param x      The x- coordinate.
     * @param y      The y- coordinate.
     * @return The associated edge to the arrow.
     */
    public Edge getEdgeArrow(Layout<V, E> layout, double x, double y) {
        throw new UnsupportedOperationException();
    }

}
