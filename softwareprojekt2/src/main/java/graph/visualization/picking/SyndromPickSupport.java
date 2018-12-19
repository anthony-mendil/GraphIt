package graph.visualization.picking;

import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.visualization.VisualizationServer;
import edu.uci.ics.jung.visualization.picking.ShapePickSupport;
import graph.graph.Edge;
import graph.graph.Sphere;

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
    SyndromPickSupport(VisualizationServer<V, E> vv) {
        super(vv);
    }

    /**
     * Iterates over spheres, checking to see if x,y is contained in the spheres shape.
     *
     * @param layout The current layout.
     * @param x      The x- coordinate.
     * @param y      The y- coordinate.
     * @return The picked sphere.
     */
    public Sphere getSphaere(Layout<V, E> layout, double x, double y) {
        throw new UnsupportedOperationException();
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
