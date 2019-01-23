package graph.visualization.picking;

import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.util.Context;
import edu.uci.ics.jung.graph.util.Pair;
import edu.uci.ics.jung.visualization.Layer;
import edu.uci.ics.jung.visualization.VisualizationServer;
import edu.uci.ics.jung.visualization.picking.ShapePickSupport;
import edu.uci.ics.jung.visualization.renderers.BasicEdgeArrowRenderingSupport;
import edu.uci.ics.jung.visualization.renderers.EdgeArrowRenderingSupport;
import graph.graph.Edge;
import graph.graph.Sphere;
import graph.graph.SyndromGraph;
import graph.graph.Vertex;
import graph.visualization.transformer.sphere.SphereShapeTransformer;

import java.awt.*;
import java.awt.geom.*;
import java.util.ConcurrentModificationException;
import java.util.List;

/**
 * SyndromPickSupport extends the ShapePickSupport with the option to pick spheres and arrows from edges.
 */
public class SyndromPickSupport<V, E> extends ShapePickSupport {
    /**
     * The visualisation server.
     */
    private VisualizationServer<V, E> pVisualizationServer;
    private SphereShapeTransformer<Sphere> sphereShapeTransformer = new SphereShapeTransformer<Sphere>();

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
     * @param x The x- coordinate.
     * @param y The y- coordinate.
     * @return The picked sphere.
     */
    public Sphere getSphere(double x, double y) {
        Sphere sphaereContains = null;
        Point2D ip = vv.getRenderContext().getMultiLayerTransformer().inverseTransform(Layer.LAYOUT,
                new Point2D.Double(x, y));
        x = ip.getX();
        y = ip.getY();

        try {
            SyndromGraph g = (SyndromGraph) vv.getGraphLayout().getGraph();
            List<Sphere> list = g.getSpheres();
            for (Object aSet : list) {
                Sphere s = (Sphere) aSet;
                Point2D p = s.getCoordinates();
                Shape rec = new Rectangle2D.Double(p.getX(), p.getY(), s
                        .getWidth(), s.getHeight());
                AffineTransform transform = vv.getRenderContext().getMultiLayerTransformer().getTransformer(Layer.LAYOUT).getTransform();
                transform.createTransformedShape(rec);
                if (rec.contains(x, y)) {
                    sphaereContains = s;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sphaereContains;
    }

    //@Override
    public Object getEdges(Layout layout, double x, double y) {
        Point2D ip = vv.getRenderContext().getMultiLayerTransformer().inverseTransform(Layer.VIEW, new Point2D.Double(x, y));
        x = ip.getX();
        y = ip.getY();

        // as a Line has no area, we can't always use edgeshape.contains(point) so we
        // make a small rectangular pickArea around the point and check if the
        // edgeshape.intersects(pickArea)
        Rectangle2D pickArea =
                new Rectangle2D.Float((float) x - pickSize / 2, (float) y - pickSize / 2, pickSize, pickSize);
        E closest = null;
        double minDistance = Double.MAX_VALUE;
        while (true) {
            try {
                for (Object o : getFilteredEdges(layout)) {

                    E e = (E) o;

                    Shape edgeShape = getTransformedEdgeShape(layout, e);
                    if (edgeShape == null)
                        continue;

                    // because of the transform, the edgeShape is now a GeneralPath
                    // see if this edge is the closest of any that intersect
                    if (edgeShape.intersects(pickArea)) {
                        float cx = 0;
                        float cy = 0;
                        float[] f = new float[6];
                        PathIterator pi = new GeneralPath(edgeShape).getPathIterator(null);
                        if (pi.isDone() == false) {
                            pi.next();
                            pi.currentSegment(f);
                            cx = f[0];
                            cy = f[1];
                            if (pi.isDone() == false) {
                                pi.currentSegment(f);
                                cx = f[0];
                                cy = f[1];
                            }
                        }
                        float dx = (float) (cx - x);
                        float dy = (float) (cy - y);
                        float dist = dx * dx + dy * dy;
                        if (dist < minDistance) {
                            minDistance = dist;
                            closest = e;
                        }
                    }
                }
                break;
            } catch (ConcurrentModificationException cme) {
            }
        }
        return closest;
    }

    private Shape getTransformedEdgeShape(Layout<V, E> layout, E e) {
        Pair<V> pair = layout.getGraph().getEndpoints(e);
        V v1 = pair.getFirst();
        V v2 = pair.getSecond();
        Vertex vertex = (Vertex) v2;
        Point2D p1 = vv.getRenderContext().getMultiLayerTransformer().transform(Layer.LAYOUT, layout.transform(v1));
        Point2D p2 = vv.getRenderContext().getMultiLayerTransformer().transform(Layer.LAYOUT, layout.transform(v2));
        if (p1 == null || p2 == null)
            return null;
        float x1 = (float) p1.getX();
        float y1 = (float) p1.getY();
        float x2 = (float) p2.getX();
        float y2 = (float) p2.getY();

        // translate the edge to the starting vertex
        AffineTransform xform = AffineTransform.getTranslateInstance(x2, y2);
        float dx = x2 - x1;
        float dy = y2 - y1;
        // rotate the edge to the angle between the vertices
        double theta = Math.atan2(dy, dx);
        xform.rotate(theta);
        // stretch the edge to span the distance between the vertices
        float dist = (float) Math.sqrt(dx * dx + dy * dy);
        xform.scale(dist, 1.0f);

        Shape edgeShape = (Shape)
                vv.getRenderContext().getEdgeShapeTransformer().transform(Context.<Graph<V, E>, E>getInstance(vv.getGraphLayout().getGraph(), e));
        Shape oldShape = edgeShape;
        // transform the edge to its location and dimensions
        edgeShape = xform.createTransformedShape(edgeShape);

        Shape arrow = (Shape) vv.getRenderContext().getEdgeArrowTransformer().transform(Context.<Graph<V, E>, E>getInstance(layout.getGraph(), e));
        Edge edge = (Edge) e;

        if (edge.isHasAnchor()) {
            Shape shape = xform.createTransformedShape(edgeShape);
            Shape destVertexShape = (Shape)
                    vv.getRenderContext().getVertexShapeTransformer().transform(layout.getGraph().getEndpoints(e).getSecond());
            AffineTransform xf = AffineTransform.getTranslateInstance(x2, y2);
            destVertexShape = xf.createTransformedShape(destVertexShape);
            Line2D lineAngle = new Line2D.Double( edge.getAnchorPoint(), vertex.getCoordinates());
            EdgeArrowRenderingSupport renderingSupport = new BasicEdgeArrowRenderingSupport();
            AffineTransform edgeAngle = renderingSupport.getArrowTransform(vv.getRenderContext(), lineAngle, destVertexShape);

            // arrow = edgeAngle.createTransformedShape(arrow);
            arrow = edgeAngle.createTransformedShape(arrow);
            x2 = (float) arrow.getBounds2D().getCenterX();
            y2 = (float) arrow.getBounds2D().getCenterY();

        }

        AffineTransform x2form = AffineTransform.getTranslateInstance(x2, y2);

        dx = x2 - x1;
        dy = y2 - y1;
        // rotate the edge to the angle between the vertices
        theta = Math.atan2(dy, dx);
        x2form.rotate(theta);
        // stretch the edge to span the distance between the vertices
        float dist2 = (float) Math.sqrt(dx * dx + dy * dy);
        x2form.scale(dist, 1.0f);

        // transform the edge to its location and dimensions
        edgeShape = x2form.createTransformedShape(oldShape);
        return edgeShape;
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
