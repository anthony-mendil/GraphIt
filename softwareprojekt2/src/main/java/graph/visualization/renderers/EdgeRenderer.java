package graph.visualization.renderers;

import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.util.Context;
import edu.uci.ics.jung.graph.util.Pair;
import edu.uci.ics.jung.visualization.Layer;
import edu.uci.ics.jung.visualization.RenderContext;
import edu.uci.ics.jung.visualization.renderers.BasicEdgeArrowRenderingSupport;
import edu.uci.ics.jung.visualization.renderers.BasicEdgeRenderer;
import edu.uci.ics.jung.visualization.renderers.EdgeArrowRenderingSupport;
import edu.uci.ics.jung.visualization.transform.LensTransformer;
import edu.uci.ics.jung.visualization.transform.MutableTransformer;
import edu.uci.ics.jung.visualization.transform.shape.GraphicsDecorator;
import graph.graph.Edge;
import graph.graph.EdgeArrowType;
import graph.graph.ScopePoint;
import graph.graph.Vertex;
import graph.visualization.transformer.edge.EdgeArrowTransformer;
import gui.Values;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

/**
 * The EdgeRenderer renders all edges from the syndrom graph. If edges converge at the vertex with the same arrowhead
 * in a specific area at the vertex, the arrowheads are grouped together. If the edge has an anchor point at the vertex,
 * the edge is drawn there.
 *
 * @param <V> The vertex type.
 * @param <E> The edge type.
 */
public class EdgeRenderer<V, E> extends BasicEdgeRenderer<V, E> {
    /**
     * The logger.
     */
    private static Logger logger = Logger.getLogger(EdgeRenderer.class);
    /**
     * The helper functions for rendering.
     */
    private RenderHelperFunction renderHelperFunction = new RenderHelperFunction();
    /**
     * The EdgeArrowRendering Support, helps to detect the position of the anchor points.
     */
    private EdgeArrowRenderingSupport<V, E> arrowRenderingSupport = new BasicEdgeArrowRenderingSupport<>();

    /**
     * some of the instructions are just the same the BasicEdgeRenderer used in the drawSimpleEdge() method.
     *
     * @param rc     The render context.
     * @param layout The layout.
     * @param e      The edge e.
     */
    @Override
    protected void drawSimpleEdge(RenderContext<V, E> rc, Layout<V, E> layout, E e) {
        // code from framework
        GraphicsDecorator g = rc.getGraphicsContext();
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

        // shape of the edge
        Shape edgeShape = null;
        try {
            edgeShape = rc.getEdgeShapeTransformer().transform(Context.getInstance(graph, e));
            if (edgeShape == null) {
                return;
            }
        } catch (NullPointerException ex) {
            logger.error(e.toString());
        }

        boolean edgeHit;
        boolean arrowHit;
        Rectangle deviceRectangle;
        JComponent vv = rc.getScreenDevice();
        if (vv != null) {
            Dimension d = vv.getSize();
            deviceRectangle = new Rectangle(0, 0, d.width, d.height);
        } else {
            deviceRectangle = null;
        }

        // translates the edges shape
        AffineTransform xform = AffineTransform.getTranslateInstance(x1, y1);
        Shape oldEdge = edgeShape;
        g.setStroke(new BasicStroke(1.0f));

        // this is a normal edge. Rotate it to the angle between
        // vertex endpoints, then scale it to the distance between
        // the vertices
        float dx = x2 - x1;
        float dy = y2 - y1;
        float thetaRadians = (float) Math.atan2(dy, dx);
        xform.rotate(thetaRadians);
        float dist = (float) Math.sqrt(dx * dx + dy * dy);
        xform.scale(dist, 1.0);

        edgeShape = xform.createTransformedShape(edgeShape);

        MutableTransformer vt = rc.getMultiLayerTransformer().getTransformer(Layer.VIEW);
        if (vt instanceof LensTransformer) {
            vt = ((LensTransformer) vt).getDelegate();
        }
        edgeHit = vt.transform(edgeShape).intersects(deviceRectangle);

        if (edgeHit) {
            Paint oldPaint = g.getPaint();
            float scaleX = (float) g.getTransform().getScaleX();
            float scaleY = (float) g.getTransform().getScaleY();
            // see if arrows are too small to bother drawing
            if (scaleX < .3 || scaleY < .3) return;

            if (rc.getEdgeArrowPredicate().evaluate(Context.getInstance(graph, e))) {
                Shape destVertexShape =
                        rc.getVertexShapeTransformer().transform(graph.getEndpoints(e).getSecond());
                AffineTransform xf = AffineTransform.getTranslateInstance(x2, y2);
                destVertexShape = xf.createTransformedShape(destVertexShape);
                arrowHit = rc.getMultiLayerTransformer().getTransformer(Layer.VIEW).transform(destVertexShape).intersects(deviceRectangle);
                if (arrowHit) {
                    renderEdgeByHit(new javafx.util.Pair<>(new Point2D.Double(x1, y1), new Point2D.Double(x2, y2)), rc, edgeShape, destVertexShape, graph, e, oldEdge);
                }
            }
            g.setPaint(oldPaint);
        }
    }


    /**
     * Renders the edge by the hit.
     * @param points          The points (out, in) of the edges endpoints.
     * @param rc              The render context.
     * @param edgeShape       The edges shape.
     * @param destVertexShape The in vertex shape.
     * @param graph           The current graph.
     * @param e               The edge e.
     * @param oldEdge         The old edges shape (with no anchor points, transform etc.).
     */
    private void renderEdgeByHit(javafx.util.Pair<Point2D, Point2D> points, RenderContext<V, E> rc, Shape edgeShape, Shape destVertexShape, Graph<V, E> graph, E e, Shape oldEdge) {
        Pair<V> endP = graph.getEndpoints(e);
        Vertex second = (Vertex) endP.getSecond();
        Vertex first = (Vertex) endP.getFirst();

        double x1 = points.getKey().getX();
        double y1 = points.getKey().getY();
        double x2 = points.getValue().getX();
        double y2 = points.getValue().getY();

        Shape departVertexShape = rc.getVertexShapeTransformer().transform(graph.getEndpoints(e).getFirst());
        AffineTransform xTransform = AffineTransform.getTranslateInstance(x1, y1);
        departVertexShape = xTransform.createTransformedShape(departVertexShape);

        AffineTransform at = arrowRenderingSupport.getArrowTransform(rc, edgeShape, destVertexShape);
        if (at == null) return;
        Shape arrow = rc.getEdgeArrowTransformer().transform(Context.getInstance(graph, e));
        Edge edge = (Edge) e;
        AffineTransform edgeAngle = null;

        Point2D an = edge.getAnchorPoints().getValue();
        Point2D outgoing = edge.getAnchorPoints().getKey();
        if (edge.isHasAnchorIn() && an != null) {
            Point2D cord = second.getCoordinates();
            Point2D t = new Point2D.Double(an.getX() + cord.getX(), an.getY() + cord.getY());
            Point2D anchor = rc.getMultiLayerTransformer().transform(Layer.LAYOUT, t);
            cord = rc.getMultiLayerTransformer().transform(Layer.LAYOUT, cord);
            Line2D lineAngle = new Line2D.Double(anchor, cord);
            edgeAngle = arrowRenderingSupport.getArrowTransform(rc, lineAngle, destVertexShape);
            if (edgeAngle == null) return;
            arrow = edgeAngle.createTransformedShape(arrow);
            x2 = (float) arrow.getBounds2D().getCenterX();
            y2 = (float) arrow.getBounds2D().getCenterY();
        }

        if (edge.isHasAnchorOut() && outgoing != null) {
            Point2D point = getAnchorOutPointShape(first.getCoordinates(), outgoing, rc, departVertexShape);
            if (point != null) {
                x1 = point.getX();
                y1 = point.getY();
            }
        }

        if (!edge.isHasAnchorIn()) {
            edgeHasNoAnchorIn(new javafx.util.Pair<>(second, destVertexShape), rc, at, arrow, edgeAngle, new Point2D.Double(x1, y1), new javafx.util.Pair<>(oldEdge, e));
            return;
        }
        edgeShape = getAffineTransformEdgeShape(new Point2D.Double(x1, y1), new Point2D.Double(x2, y2), oldEdge);
        setPaintEdge(e, rc);
        drawEdgeArrow(rc, new javafx.util.Pair<>(e, edgeShape), arrow, edgeAngle, new javafx.util.Pair<>(null, at), new Point2D.Double(x1, y1));
    }

    /**
     * Draws the edge if the sink-vertex has no anchor-point.
     * @param pairV,    Contains second vertex, Vertex shape.
     * @param rc        The render context.
     * @param at        AffineTransform.
     * @param arrow     The arrow shape.
     * @param edgeAngle AffineTransform for out.
     * @param one       The first endpoint coordinates.
     * @param ePair     Contains old edge shape, edge e.
     */
    private void edgeHasNoAnchorIn(javafx.util.Pair<Vertex, Shape> pairV, RenderContext<V, E> rc, AffineTransform at, Shape arrow, AffineTransform edgeAngle, Point2D one, javafx.util.Pair<Shape, E> ePair) {
        double x1 = one.getX();
        double y1 = one.getY();
        double x2;
        double y2;
        Shape oldEdge = ePair.getKey();
        Vertex second = pairV.getKey();
        Shape destVertexShape = pairV.getValue();
        E e = ePair.getValue();
        Edge edge = (Edge) e;
        EdgeArrowType arrowType = edge.getArrowType();
        Map<ScopePoint, javafx.util.Pair<Point2D, AffineTransform>> map;
        map = getMap(arrowType, second);
        Point2D fitPoint = null;
        AffineTransform arr = null;
        ArrayList<ScopePoint> scopePoints = null;
        if (map != null) {
            javafx.util.Pair<Point2D, AffineTransform> pair = getFitPoint(rc, map, at, second, arrow);
            fitPoint = pair.getKey();
            arr = pair.getValue();
            scopePoints = getUnusedScopePoints(map);
        }

        if (fitPoint != null) {
            x2 = (float) fitPoint.getX();
            y2 = (float) fitPoint.getY();
            edge.setAnchorPoints(new javafx.util.Pair<>(edge.getAnchorPoints().getKey(), fitPoint));
            arrow = arr.createTransformedShape(arrow);

        } else {
            // get next free point
            Shape ar = at.createTransformedShape(arrow);
            x2 = (float) ar.getBounds2D().getCenterX();
            y2 = (float) ar.getBounds2D().getCenterY();
            Point2D original = new Point2D.Double(x2, y2);
            javafx.util.Pair<Point2D, AffineTransform> nextPoint = getNextFreePoint(arrowType, original, second, at, arrow, destVertexShape, rc);

            if (nextPoint == null) {
                arrow = ar;
            } else {
                edgeAngle = nextPoint.getValue();
                if (edgeAngle == null) return;
                arrow = edgeAngle.createTransformedShape(arrow);
                x2 = (float) arrow.getBounds2D().getCenterX();
                y2 = (float) arrow.getBounds2D().getCenterY();
            }

            Point2D newPoint = new Point2D.Double(arrow.getBounds2D().getCenterX(), arrow.getBounds2D().getCenterY());
            setScopePoints(scopePoints, map, newPoint, edge, second, at);
        }

        Shape edgeShape = getAffineTransformEdgeShape(new Point2D.Double(x1, y1), new Point2D.Double(x2, y2), oldEdge);
        setPaintEdge(e, rc);
        drawEdgeArrow(rc, new javafx.util.Pair<>(e, edgeShape), arrow, edgeAngle, new javafx.util.Pair<>(arr, at), new Point2D.Double(x1, y1));
    }

    /**
     * Adds the arrow to the scopePoint map.
     *
     * @param scopePoints The list of unused scopePoints.
     * @param map         The map wth all scopePoints and its 'entry's' - points/ affineTransform of the related arrows.
     * @param newPoint    The new point where the arrow is set.
     * @param edge        The edge.
     * @param second      The second endpoint of the edge.
     * @param at          The AffineTransform for the arrow.
     */
    private void setScopePoints(ArrayList<ScopePoint> scopePoints, Map<ScopePoint, javafx.util.Pair<Point2D, AffineTransform>> map, Point2D newPoint, Edge edge, Vertex second, AffineTransform at) {
        if (scopePoints != null && !scopePoints.isEmpty()) {
            map.put(scopePoints.get(scopePoints.size() - 1), new javafx.util.Pair<>(newPoint, at));
        } else {
            throw new IllegalArgumentException();
        }
        setMap(edge.getArrowType(), second, map);
        edge.setAnchorPoints(new javafx.util.Pair<>(edge.getAnchorPoints().getKey(), newPoint));
    }

    /**
     * Calculates the position of the anchor-point of the start-vertex.
     * @param point    The point.
     * @param outgoing The outgoing anchor point.
     * @param rc       The render context.
     * @param shape    The edge shape.
     * @return The vertex coordinates (center).
     */
    private Point2D getAnchorOutPointShape(Point2D point, Point2D outgoing, RenderContext<V, E> rc, Shape shape) {
        Point2D oT = new Point2D.Double(outgoing.getX() + point.getX(), outgoing.getY() + point.getY());
        Point2D outAnchor = rc.getMultiLayerTransformer().transform(Layer.LAYOUT, oT);
        point = rc.getMultiLayerTransformer().transform(Layer.LAYOUT, point);
        Line2D outLineAngle = new Line2D.Double(outAnchor, point);
        AffineTransform outEdgeAngle;
        outEdgeAngle = arrowRenderingSupport.getArrowTransform(rc, outLineAngle, shape);
        if (outEdgeAngle == null) return null;
        Shape tryP = new Ellipse2D.Double(0, 0, 5, 5);
        tryP = outEdgeAngle.createTransformedShape(tryP);
        return new Point2D.Double((float) tryP.getBounds2D().getCenterX(), (float) tryP.getBounds2D().getCenterY());
    }

    /**
     * Claculates the new edge-shape.
     * @param one The point where the edge is outgoing from the vertex.
     * @param two The point where the edge is incoming into the vertex.
     * @param old The old edge shape.
     * @return The new edge shape.
     */
    private Shape getAffineTransformEdgeShape(Point2D one, Point2D two, Shape old) {
        AffineTransform affineTransform = AffineTransform.getTranslateInstance(one.getX(), one.getY());
        double dx = two.getX() - one.getX();
        double dy = two.getY() - one.getY();
        double thetaRadians = (float) Math.atan2(dy, dx);
        affineTransform.rotate(thetaRadians);
        double dist = (float) Math.sqrt(dx * dx + dy * dy);
        affineTransform.scale(dist, 1.0);
        return affineTransform.createTransformedShape(old);
    }

    /**
     * Calculates the next free point to dock on a vertex.
     * @param help        The ellipse to traverse to.
     * @param center      The center of the vertex shape.
     * @param avoid       The point where not to set the arrow.
     * @param shapes      The arrow shapes.
     * @param rc          The render context.
     * @param vertexShape The vertex shape.
     * @param arrow3      The third arrow, if the ScopePoint is containing all 3 arrow types.
     * @return The next free point and the AffineTransform for the arrow shape.
     */
    private javafx.util.Pair<Point2D, AffineTransform> getPoint(Ellipse2D help, Point2D center, Point2D avoid, javafx.util.Pair<Shape, Shape> shapes, RenderContext<V, E> rc, Shape vertexShape, Shape arrow3) {
        double[] coordinates = new double[6];
        Point2D.Double point;
        Point2D.Double newP = null;
        javafx.util.Pair<Point2D, AffineTransform> newPair = null;
        Boolean isMinus = null;
        javafx.util.Pair<Point2D, AffineTransform> pair = null;

        for (PathIterator pi = new GeneralPath(help).getPathIterator(null, 0.1); !pi.isDone(); pi.next()) {
            pi.currentSegment(coordinates);
            double cx = coordinates[0];
            double cy = coordinates[1];
            point = new Point2D.Double(cx, cy);

            pair = getIntersection(point, new Pair<>(shapes.getKey(), shapes.getValue()), center, rc, vertexShape, arrow3);

            double b = angleBetween(new Point2D.Double(center.getX(), center.getY()), avoid, point);
            if (newP == null) {
                newP = point;
                newPair = pair;
                isMinus = b < 0;
            }
            b = Math.abs(b);
            double c = angleBetween(new Point2D.Double(center.getX(), center.getY()), avoid, newP);
            boolean cMinus = c < 0;
            c = Math.abs(c);

            if (b < c && isMinus == cMinus && pair != null) {
                newP = point;
                newPair = pair;
            }
        }
        if (pair != null) {
            return newPair;
        } else {
            return null;
        }
    }

    /**
     * Detects if there is intersection between the two arrow shapes passed.
     *
     * @param p2          The current position of the arrow.
     * @param arrows      A pair containing the two arrows to check if there intersect.
     * @param center      The center of the vertex shape.
     * @param rc          The render context.
     * @param vertexShape The vertex shape.
     * @param arrow3      The third arrow, if the ScopePoint is containing all 3 arrow types.
     * @return A pair of point and AffineTransform if there is no intersection, if there is, returning null.
     */
    private javafx.util.Pair<Point2D, AffineTransform> getIntersection(Point2D p2, Pair<Shape> arrows, Point2D center, RenderContext<V, E> rc, Shape vertexShape, Shape arrow3) {
        Line2D line = new Line2D.Double(p2, center);
        AffineTransform a2 = arrowRenderingSupport.getArrowTransform(rc, line, vertexShape);
        if (a2 == null) return null;
        Shape s2 = a2.createTransformedShape(arrows.getSecond());
        if (!s2.getBounds2D().intersects(arrows.getFirst().getBounds2D())) {
            if (arrow3 != null) {
                if (!s2.getBounds2D().intersects(arrow3.getBounds2D())) {
                    return new javafx.util.Pair<>(p2, a2);
                }
            } else {
                return new javafx.util.Pair<>(p2, a2);
            }
        }
        return null;
    }

    /**
     * Find the next free point on the vertex.
     *
     * @param arrowType   The edge arrow type.
     * @param original    The current in anchor point.
     * @param vertex      The vertex.
     * @param at          The AffineTransform of the arrow.
     * @param arrow       The arrows shape.
     * @param vertexShape The vertex shape.
     * @param rc          The render context.
     * @return A pair, containing the new arrow point and AffineTransform.
     */
    private javafx.util.Pair<Point2D, AffineTransform> getNextFreePoint(EdgeArrowType arrowType, Point2D original, Vertex vertex, AffineTransform at, Shape arrow, Shape vertexShape, RenderContext<V, E> rc) {
        Pair<javafx.util.Pair<Map<ScopePoint, javafx.util.Pair<Point2D, AffineTransform>>, EdgeArrowType>> getOtherTypesMap = getOtherTypesMap(arrowType, vertex);
        javafx.util.Pair<Point2D, AffineTransform> first = getFitPoint(rc, getOtherTypesMap.getFirst().getKey(), at, vertex, arrow);
        javafx.util.Pair<Point2D, AffineTransform> second = getFitPoint(rc, getOtherTypesMap.getSecond().getKey(), at, vertex, arrow);
        Point2D pointF = first.getKey();
        Point2D pointS = second.getKey();
        EdgeArrowTransformer edgeArrowTransformer = new EdgeArrowTransformer();
        Shape f = edgeArrowTransformer.getTransform(getOtherTypesMap.getFirst().getValue());
        Shape s = edgeArrowTransformer.getTransform(getOtherTypesMap.getSecond().getValue());
        Rectangle2D bounds = vertexShape.getBounds2D();
        Point2D center = new Point2D.Double(bounds.getCenterX(), bounds.getCenterY());
        double width = (bounds.getWidth() > bounds.getHeight()) ? bounds.getWidth() : bounds.getHeight();
        Ellipse2D help = new Ellipse2D.Double(bounds.getCenterX() - ((width + 10) / 2), bounds.getCenterY() - ((width + 10) / 2), width + 10, width + 10);

        if (pointF == null && pointS == null) {
            return null;
        } else if (pointF != null && pointS == null) {
            f = first.getValue().createTransformedShape(f);
            javafx.util.Pair<Point2D, AffineTransform> pair = getIntersection(original, new Pair<>(f, arrow), center, rc, vertexShape, null);
            return getPair(pair, help, new javafx.util.Pair<>(center, pointF), new javafx.util.Pair<>(f, arrow), rc, vertexShape, null);
        } else if (pointF == null) {
            s = second.getValue().createTransformedShape(s);
            javafx.util.Pair<Point2D, AffineTransform> pair = getIntersection(original, new Pair<>(s, arrow), center, rc, vertexShape, null);
            return getPair(pair, help, new javafx.util.Pair<>(center, pointS), new javafx.util.Pair<>(s, arrow), rc, vertexShape, null);
        } else {
            s = second.getValue().createTransformedShape(s);
            f = first.getValue().createTransformedShape(f);
            javafx.util.Pair<Point2D, AffineTransform> pair = getIntersection(original, new Pair<>(s, arrow), center, rc, vertexShape, f);
            return getPair(pair, help, new javafx.util.Pair<>(center, pointS), new javafx.util.Pair<>(s, arrow), rc, vertexShape, f);
        }
    }

    /**
     * Returns the new position in the other type.
     * @param pair        The point and AffineTransform of the new position of the arrow, if null there was a intersection of arrows.
     * @param help        The ellipse to traverse to.
     * @param points      Containing the center of the vertex shape, the point to not set the arrow, where the other arrow is.
     * @param shapes      The shape of the to arrows (point to set, point to avoid).
     * @param rc          The render context.
     * @param vertexShape The vertex shape.
     * @param arrow3      The third arrow, if a ScopePoint contains all 3 Arrow types.
     * @return The new position.
     */
    private javafx.util.Pair<Point2D, AffineTransform> getPair(javafx.util.Pair<Point2D, AffineTransform> pair, Ellipse2D help, javafx.util.Pair<Point2D, Point2D> points, javafx.util.Pair<Shape, Shape> shapes, RenderContext<V, E> rc, Shape vertexShape, Shape arrow3) {
        if (pair != null) {
            return pair;
        } else {
            return getPoint(help, points.getKey(), points.getValue(), shapes, rc, vertexShape, arrow3);
        }
    }

    /**
     * Draws the arrow and edge.
     *
     * @param rc          The render context.
     * @param pair        Contains the edge and shape.
     * @param arrow       The arrows shape.
     * @param edgeAngle   AffineTransform for painting the neutral arrow.
     * @param forms       Contains the AffineTransforms for painting the neutral arrow.
     * @param anchorPoint The anchor point.
     */
    private void drawEdgeArrow(RenderContext<V, E> rc, javafx.util.Pair<E, Shape> pair, Shape arrow, AffineTransform edgeAngle, javafx.util.Pair<AffineTransform, AffineTransform> forms, Point2D anchorPoint) {
        GraphicsDecorator g = rc.getGraphicsContext();
        AffineTransform arr = forms.getKey();
        AffineTransform at = forms.getValue();
        E e = pair.getKey();
        Shape edgeShape = pair.getValue();
        Paint drawPaint = rc.getEdgeDrawPaintTransformer().transform(e);
        if (drawPaint != null) {
            g.setPaint(drawPaint);
            g.draw(edgeShape);
        }
        Edge edge = (Edge) e;
        Values values = Values.getInstance();
        if (values.isShowAnchor() && edge.isHasAnchorOut()) {
            g.setStroke(new BasicStroke(1.0f));
            double dimension = 15;
            Shape anchor = new Ellipse2D.Double(-dimension / 2, -dimension / 2, dimension, dimension);
            AffineTransform anchorTransform = AffineTransform.getTranslateInstance(anchorPoint.getX(), anchorPoint.getY());
            anchor = anchorTransform.createTransformedShape(anchor);
            g.setPaint(values.getAnchorHighlight());
            g.fill(anchor);
            g.draw(anchor);
        }

        g.setStroke(new BasicStroke(1.0f));
        Paint drawColor = rc.getArrowFillPaintTransformer().transform(e);

        if (edge.getArrowType() == EdgeArrowType.NEUTRAL) {
            Shape ellipse = new Ellipse2D.Double(-18.06472, -18.06472 / 2, 18.06472, 18.06472);
            if (edgeAngle != null) {
                ellipse = edgeAngle.createTransformedShape(ellipse);
            } else {
                ellipse = (arr != null) ? arr.createTransformedShape(ellipse) : at.createTransformedShape(ellipse);
            }
            g.setPaint(rc.getArrowFillPaintTransformer().transform(e));
            g.fill(ellipse);
            g.draw(ellipse);
            drawColor = renderHelperFunction.getLuminanceColor(drawColor);
        }
        g.setPaint(drawColor);
        g.fill(arrow);
        g.setPaint(drawColor);
        g.draw(arrow);
    }


    /**
     * Returns the angle between 2 points.
     *
     * @param center   The center point.
     * @param previous The first point.
     * @param current  The second point.
     * @return The angle between the first and second point.
     */
    private double angleBetween(Point2D center, Point2D previous, Point2D current) {
        return Math.toDegrees(Math.atan2(current.getX() - center.getX(), current.getY() - center.getY()) -
                Math.atan2(previous.getX() - center.getX(), previous.getY() - center.getY()));
    }

    /**
     * Returns the unused ScopePoints position.
     *
     * @param map The ScopePoint map.
     * @return A list with the unused ScopePoints.
     */
    private ArrayList<ScopePoint> getUnusedScopePoints(Map<ScopePoint, javafx.util.Pair<Point2D, AffineTransform>> map) {
        ArrayList<ScopePoint> scopePoints = new ArrayList<>(Arrays.asList(ScopePoint.values()));
        for (Map.Entry<ScopePoint, javafx.util.Pair<Point2D, AffineTransform>> entry : map.entrySet()) {
            scopePoints.remove(entry.getKey());
        }
        return scopePoints;
    }

    /**
     * Calculates the point of the snap-point of several edges.
     * @param rc     The render context.
     * @param map    The current scope point map.
     * @param at     The arrows AffineTransform.
     * @param second The vertex shape.
     * @param arrow  The arrows shape.
     * @return The point and the AffineTransform of the arrow, if the scope points map containing a point in the same ScopePoint.
     */
    private javafx.util.Pair<Point2D, AffineTransform> getFitPoint(RenderContext<V, E> rc, Map<ScopePoint, javafx.util.Pair<Point2D, AffineTransform>> map, AffineTransform at, Vertex second, Shape arrow) {
        Point2D fitPoint = null;
        AffineTransform ar = null;
        for (Map.Entry<ScopePoint, javafx.util.Pair<Point2D, AffineTransform>> entry : map.entrySet()) {
            Point2D position = entry.getValue().getKey();
            Shape arrowTemp = at.createTransformedShape(arrow);
            double x2 = (float) arrowTemp.getBounds2D().getCenterX();
            double y2 = (float) arrowTemp.getBounds2D().getCenterY();
            Point2D newPoint = new Point2D.Double(x2, y2);
            Point2D c = second.getCoordinates();
            c = rc.getMultiLayerTransformer().transform(Layer.LAYOUT, c);
            double angle = angleBetween(c, position, newPoint);
            angle = Math.abs(angle);
            if (angle > 0 && angle <= 45) {
                fitPoint = position;
                ar = entry.getValue().getValue();
                break;
            }
        }
        return new javafx.util.Pair<>(fitPoint, ar);
    }

    /**
     * Sets a new ScopePoint map.
     *
     * @param edgeType The edge type.
     * @param second   The vertex shape.
     * @param map      The new ScopePoint map.
     */
    private void setMap(EdgeArrowType edgeType, Vertex second, Map<ScopePoint, javafx.util.Pair<Point2D, AffineTransform>> map) {
        switch (edgeType) {
            case EXTENUATING:
                second.setVertexArrowExtenuating(map);
                break;
            case REINFORCED:
                second.setVertexArrowReinforced(map);
                break;
            case NEUTRAL:
                second.setVertexArrowNeutral(map);
                break;
        }
    }

    /**
     * Returning the map (depending of the edge type) with the ScopePoints.
     *
     * @param edgeType The edge type.
     * @param second   The vertex shape.
     * @return The right map depending on the edge type with the ScopePoints.
     */
    private Map<ScopePoint, javafx.util.Pair<Point2D, AffineTransform>> getMap(EdgeArrowType edgeType, Vertex second) {
        Map<ScopePoint, javafx.util.Pair<Point2D, AffineTransform>> map = null;
        switch (edgeType) {
            case EXTENUATING:
                map = second.getVertexArrowExtenuating();
                break;
            case REINFORCED:
                map = second.getVertexArrowReinforced();
                break;
            case NEUTRAL:
                map = second.getVertexArrowNeutral();
                break;
        }
        return map;
    }

    /**
     * Returns the map of the other type.
     * @param edgeArrowType The edge arrow type.
     * @param second        The vertex shape.
     * @return A pair which is containing pairs with the other edge types ScopePoints maps.
     */
    private Pair<javafx.util.Pair<Map<ScopePoint, javafx.util.Pair<Point2D, AffineTransform>>, EdgeArrowType>> getOtherTypesMap(EdgeArrowType edgeArrowType, Vertex second) {
        Pair<javafx.util.Pair<Map<ScopePoint, javafx.util.Pair<Point2D, AffineTransform>>, EdgeArrowType>> map;
        Map<ScopePoint, javafx.util.Pair<Point2D, AffineTransform>> extenuating = second.getVertexArrowExtenuating();
        Map<ScopePoint, javafx.util.Pair<Point2D, AffineTransform>> reinforced = second.getVertexArrowReinforced();
        Map<ScopePoint, javafx.util.Pair<Point2D, AffineTransform>> neutral = second.getVertexArrowNeutral();

        switch (edgeArrowType) {
            case EXTENUATING:
                map = new Pair<>(new javafx.util.Pair<>(reinforced, EdgeArrowType.REINFORCED), new javafx.util.Pair<>(neutral, EdgeArrowType.NEUTRAL));
                break;
            case REINFORCED:
                map = new Pair<>(new javafx.util.Pair<>(extenuating, EdgeArrowType.EXTENUATING), new javafx.util.Pair<>(neutral, EdgeArrowType.NEUTRAL));
                break;
            default:
                map = new Pair<>(new javafx.util.Pair<>(reinforced, EdgeArrowType.REINFORCED), new javafx.util.Pair<>(extenuating, EdgeArrowType.EXTENUATING));
                break;
        }
        return map;
    }

    /**
     * Gets and sets the edge paint/ stroke.
     *
     * @param e  The edge.
     * @param rc The render context.
     */
    private void setPaintEdge(E e, RenderContext<V, E> rc) {
        GraphicsDecorator g = rc.getGraphicsContext();
        Paint fillPaint = rc.getEdgeFillPaintTransformer().transform(e);
        Stroke newStroke = rc.getEdgeStrokeTransformer().transform(e);

        if (newStroke != null)
            g.setStroke(newStroke);

        if (fillPaint != null) {
            g.setPaint(fillPaint);
        }
    }
}
