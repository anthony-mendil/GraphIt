package graph.visualization.picking;

import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.util.Context;
import edu.uci.ics.jung.graph.util.Pair;
import edu.uci.ics.jung.visualization.Layer;
import edu.uci.ics.jung.visualization.RenderContext;
import edu.uci.ics.jung.visualization.VisualizationServer;
import edu.uci.ics.jung.visualization.picking.ShapePickSupport;
import edu.uci.ics.jung.visualization.renderers.BasicEdgeArrowRenderingSupport;
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
        Point2D ip = vv.getRenderContext().getMultiLayerTransformer().inverseTransform(Layer.VIEW,
                new Point2D.Double(x, y));
        x = ip.getX();
        y = ip.getY();

        try {
            SyndromGraph g = (SyndromGraph) vv.getGraphLayout().getGraph();
            List<Sphere> list = g.getSpheres();
            for (Object aSet : list) {
                Sphere s = (Sphere) aSet;
                Shape rec = sphereShapeTransformer.transform(s);
                rec = vv.getRenderContext().getMultiLayerTransformer().transform(Layer.LAYOUT, rec);
                if (rec.contains(x, y)) {
                    sphaereContains = s;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sphaereContains;
    }

    @Override
    public Object getEdge(Layout layout, double x, double y) {
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

                    Shape edgeShape = getTransformedEdgeShape(e, vv.getRenderContext(), layout);
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
                cme.printStackTrace();
            }
        }
        return closest;
    }

    public Shape getTransformedEdgeShape(E e, RenderContext<V, E> rc, Layout<V, E> layout) {
        // code from framework
        Graph<V, E> graph = layout.getGraph();
        Pair<V> endpoints = graph.getEndpoints(e);
        V v1 = endpoints.getFirst();
        V v2 = endpoints.getSecond();
        Point2D p1 = rc.getMultiLayerTransformer().transform(Layer.LAYOUT, layout.transform(v1));
        Point2D p2 = rc.getMultiLayerTransformer().transform(Layer.LAYOUT, layout.transform(v2));
        float x1 = (float) p1.getX();
        float y1 = (float) p1.getY();
        float x2 = (float) p2.getX();
        float y2 = (float) p2.getY();
        Shape edgeShape = rc.getEdgeShapeTransformer().transform(Context.getInstance(graph, e));

        if (rc.getEdgeArrowPredicate().evaluate(Context.<Graph<V, E>, E>getInstance(graph, e))) {
            Vertex second = (Vertex) graph.getEndpoints(e).getSecond();
            Shape arrow = rc.getEdgeArrowTransformer().transform(Context.<Graph<V, E>, E>getInstance(graph, e));
            Edge edge = (Edge) e;
            AffineTransform edgeAngle = null;

            if (edge.isHasAnchor()) {
                edgeAngle = getAffineTransformAnchor(rc, edge, second, x2, y2);
                if (edgeAngle == null){
                    return null;
                }

                arrow = edgeAngle.createTransformedShape(arrow);
                x2 = (float) arrow.getBounds2D().getCenterX();
                y2 = (float) arrow.getBounds2D().getCenterY();
            } else {
                if (edge.getAnchorPoint() != null){
                    x2 = (float)edge.getAnchorPoint().getX();
                    y2 = (float)edge.getAnchorPoint().getY();
                }

            }

            AffineTransform x_form = AffineTransform.getTranslateInstance(x1, y1);
            float dx = x2 - x1;
            float dy = y2 - y1;
            float thetaRadians = (float) Math.atan2(dy, dx);
            x_form.rotate(thetaRadians);
            float dist = (float) Math.sqrt(dx * dx + dy * dy);
            x_form.scale(dist, 1.0);
            edgeShape = x_form.createTransformedShape(edgeShape);
        }
        return edgeShape;
    }

    private AffineTransform getAffineTransformAnchor(RenderContext<V,E> rc, Edge edge, Vertex vertex, float endX, float endY){
        AffineTransform transform;
        Point2D an = edge.getAnchorPoint();
        Point2D cord = vertex.getCoordinates();
        Point2D t =  new Point2D.Double(an.getX() +cord.getX(), an.getY() + cord.getY());
        Point2D anchor = rc.getMultiLayerTransformer().transform(Layer.LAYOUT, t);
        cord = rc.getMultiLayerTransformer().transform(Layer.LAYOUT, cord);

        Line2D lineAngle = new Line2D.Double(anchor, cord);

        Shape destVertexShape =
                rc.getVertexShapeTransformer().transform((V)vertex);

        AffineTransform xf = AffineTransform.getTranslateInstance(endX, endY);
        destVertexShape = xf.createTransformedShape(destVertexShape);
        BasicEdgeArrowRenderingSupport edgeArrowRenderingSupport = new BasicEdgeArrowRenderingSupport();
        transform = edgeArrowRenderingSupport.getArrowTransform(rc, lineAngle, destVertexShape);
        return transform;
    }

}
