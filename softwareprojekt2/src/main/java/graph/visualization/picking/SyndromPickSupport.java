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
import org.apache.log4j.Logger;

import java.awt.*;
import java.awt.geom.*;
import java.util.ConcurrentModificationException;
import java.util.List;

/**
 * SyndromPickSupport extends the ShapePickSupport with the option to pick spheres and arrows from edges.
 */
public class SyndromPickSupport<V, E> extends ShapePickSupport {

    private SphereShapeTransformer<Sphere> sphereShapeTransformer = new SphereShapeTransformer<>();
    private BasicEdgeArrowRenderingSupport edgeArrowRenderingSupport = new BasicEdgeArrowRenderingSupport();
    private static Logger logger = Logger.getLogger(SyndromPickSupport.class);

    /**
     * Creates a <code>SyndromPickSupport</code> for the <code>vv</code> VisualizationServer. The
     * <code>VisualizationServer</code> is used to access properties of the current visualization (layout, renderer,
     * coordinate transformations, vertex/edge shapes, etc.).
     *
     * @param vv Source of the current <code>Layout</code>.
     */
    @SuppressWarnings("unchecked")
    public SyndromPickSupport(VisualizationServer<V, E> vv) {
        super(vv);
        pickSize = 5;
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
            List list = g.getSpheres();
            for (Object aSet : list) {
                Sphere s = (Sphere) aSet;
                Shape rec = sphereShapeTransformer.transform(s);
                rec = vv.getRenderContext().getMultiLayerTransformer().transform(Layer.LAYOUT, rec);
                if (rec.contains(x, y)) {
                    sphaereContains = s;
                }
            }
        } catch (Exception e) {
            logger.error(e.toString());
        }
        return sphaereContains;
    }

    /**
     * Dieser Code ist aus dem Framework JUNG aus der Klasse ShapePickSupport kopiert und hier eingefügt.
     * Die Methode benutzt eine weitere Methode getTransformedEdgeShape(). Diese ist in der genannten Klasse private,
     * weswegen wir sie nicht überschreiben konnten. Außerdem wurde der return Wert von getTransformedEdgeShape()
     * geändert.
     * Der restliche Code der Methode wurde nicht verändert und nicht von uns programmiert. Deswegen haben wir hier
     * auch keinen Einfluss auf den von sonarqube generierten issue (complexity)
     */
    @Override
    @SuppressWarnings("unchecked")
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
                    javafx.util.Pair<javafx.util.Pair<Shape, Point2D>, Shape> pair = getTransformedEdgeShape(e, vv.getRenderContext(), layout);
                    if (pair == null) {
                        return null;
                    }
                    Shape edgeShape = pair.getKey().getKey();
                    if (edgeShape == null)
                        continue;

                    // because of the transform, the edgeShape is now a GeneralPath
                    // see if this edge is the closest of any that intersect
                    if (edgeShape.intersects(pickArea)) {
                        float cx = 0;
                        float cy = 0;
                        float[] f = new float[6];
                        PathIterator pi = new GeneralPath(edgeShape).getPathIterator(null);
                        if (!pi.isDone()) {
                            pi.next();
                            pi.currentSegment(f);
                            cx = f[0];
                            cy = f[1];
                            if (!pi.isDone()) {
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
                logger.error(cme.toString());
            }
        }
        return closest;
    }


    private javafx.util.Pair<javafx.util.Pair<Shape, Point2D>, Shape> getTransformedEdgeShape(E e, RenderContext<V, E> rc, Layout<V, E> layout) {
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
        Shape edgeShape = null;
        try {
            edgeShape = rc.getEdgeShapeTransformer().transform(Context.getInstance(graph, e));
            if (edgeShape == null) {
                return null;
            }
        } catch (NullPointerException ex) {
            logger.error(ex.toString());
        }

        Shape arrow = null;

        if (rc.getEdgeArrowPredicate().evaluate(Context.getInstance(graph, e))) {
            Vertex second = (Vertex) graph.getEndpoints(e).getSecond();
            Vertex first = (Vertex) graph.getEndpoints(e).getFirst();
            arrow = rc.getEdgeArrowTransformer().transform(Context.getInstance(graph, e));
            Edge edge = (Edge) e;
            AffineTransform edgeAngle;
            AffineTransform outEdgeAngle;
            Point2D in = edge.getAnchorPoints().getValue();
            Point2D out = edge.getAnchorPoints().getKey();
            if (edge.isHasAnchorIn() && in != null) {
                edgeAngle = getAffineTransformAnchor(rc, second, x2, y2, in);
                if (edgeAngle == null) {
                    return null;
                }
                arrow = edgeAngle.createTransformedShape(arrow);
                x2 = (float) arrow.getBounds2D().getCenterX();
                y2 = (float) arrow.getBounds2D().getCenterY();
            }

            if (edge.isHasAnchorOut() && out != null) {
                outEdgeAngle = getAffineTransformAnchor(rc, first, x1, y1, out);
                if (outEdgeAngle == null) return null;
                Shape tryP = new Ellipse2D.Double(0, 0, 5, 5);
                tryP = outEdgeAngle.createTransformedShape(tryP);
                x1 = (float) tryP.getBounds2D().getCenterX();
                y1 = (float) tryP.getBounds2D().getCenterY();
            }

            Point2D point = getSecondAnchorIn(edge, x2, y2);
            x2 =(float) point.getX();
            y2 = (float) point.getY();

            AffineTransform xForm = AffineTransform.getTranslateInstance(x1, y1);
            edgeShape = getNormalEdgeShape(edgeShape, xForm, new Point2D.Double(x1, y1), new Point2D.Double(x2, y2));
        }
        javafx.util.Pair<Shape, Point2D> pair = new javafx.util.Pair<>(edgeShape, new Point2D.Double(x1, y1));
        return new javafx.util.Pair<>(pair, arrow);
    }

    private Point2D getSecondAnchorIn(Edge edge, float x2, float y2){
        if (!edge.isHasAnchorIn() && edge.getAnchorPoints().getValue() != null) {
            x2 = (float) edge.getAnchorPoints().getValue().getX();
            y2 = (float) edge.getAnchorPoints().getValue().getY();
        }
        return new Point2D.Float(x2, y2);
    }

    @SuppressWarnings("unchecked")

    private AffineTransform getAffineTransformAnchor(RenderContext<V, E> rc, Vertex vertex, float endX, float endY, Point2D anchorPoint) {
        AffineTransform transform;
        Point2D cord = vertex.getCoordinates();
        Point2D t = new Point2D.Double(anchorPoint.getX() + cord.getX(), anchorPoint.getY() + cord.getY());
        Point2D anchor = rc.getMultiLayerTransformer().transform(Layer.LAYOUT, t);
        cord = rc.getMultiLayerTransformer().transform(Layer.LAYOUT, cord);

        Line2D lineAngle = new Line2D.Double(anchor, cord);

        Shape destVertexShape =
                rc.getVertexShapeTransformer().transform((V) vertex);

        AffineTransform xf = AffineTransform.getTranslateInstance(endX, endY);
        destVertexShape = xf.createTransformedShape(destVertexShape);
        transform = edgeArrowRenderingSupport.getArrowTransform(rc, lineAngle, destVertexShape);
        return transform;
    }

    private Shape getNormalEdgeShape(Shape edgeShape, AffineTransform xform, Point2D pointOne, Point2D pointTwo) {
        float dx = (float) (pointTwo.getX() - pointOne.getX());
        float dy = (float) (pointTwo.getY() - pointOne.getY());
        float thetaRadians = (float) Math.atan2(dy, dx);
        xform.rotate(thetaRadians);
        float dist = (float) Math.sqrt(dx * dx + dy * dy);
        xform.scale(dist, 1.0);

        edgeShape = xform.createTransformedShape(edgeShape);
        return edgeShape;
    }

}
