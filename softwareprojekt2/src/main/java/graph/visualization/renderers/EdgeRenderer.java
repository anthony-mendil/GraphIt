package graph.visualization.renderers;

import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.util.Context;
import edu.uci.ics.jung.graph.util.Pair;
import edu.uci.ics.jung.visualization.Layer;
import edu.uci.ics.jung.visualization.RenderContext;
import edu.uci.ics.jung.visualization.renderers.BasicEdgeRenderer;
import edu.uci.ics.jung.visualization.transform.LensTransformer;
import edu.uci.ics.jung.visualization.transform.MutableTransformer;
import edu.uci.ics.jung.visualization.transform.shape.GraphicsDecorator;
import graph.graph.Edge;
import graph.graph.EdgeArrowType;
import graph.graph.ScopePoint;
import graph.graph.Vertex;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
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
        Shape edgeShape = rc.getEdgeShapeTransformer().transform(Context.getInstance(graph, e));

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
            float scalex = (float) g.getTransform().getScaleX();
            float scaley = (float) g.getTransform().getScaleY();
            // see if arrows are too small to bother drawing
            if (scalex < .3 || scaley < .3) return;

            if (rc.getEdgeArrowPredicate().evaluate(Context.<Graph<V, E>, E>getInstance(graph, e))) {

                Vertex second = (Vertex) graph.getEndpoints(e).getSecond();
                Shape destVertexShape =
                        rc.getVertexShapeTransformer().transform(graph.getEndpoints(e).getSecond());

                AffineTransform xf = AffineTransform.getTranslateInstance(x2, y2);
                destVertexShape = xf.createTransformedShape(destVertexShape);

                arrowHit = rc.getMultiLayerTransformer().getTransformer(Layer.VIEW).transform(destVertexShape).intersects(deviceRectangle);
                if (arrowHit) {
                    AffineTransform at =
                            edgeArrowRenderingSupport.getArrowTransform(rc, edgeShape, destVertexShape);
                    if (at == null) return;
                    Shape arrow = rc.getEdgeArrowTransformer().transform(Context.<Graph<V, E>, E>getInstance(graph, e));
                    Edge edge = (Edge) e;
                    AffineTransform edgeAngle = null;
                    AffineTransform arr = null;


                    if (edge.isHasAnchor()) {
                        Point2D an = edge.getAnchorPoint();
                        Point2D cord = second.getCoordinates();
                        Point2D t = new Point2D.Double(an.getX() + cord.getX(), an.getY() + cord.getY());
                        Point2D anchor = rc.getMultiLayerTransformer().transform(Layer.LAYOUT, t);
                        cord = rc.getMultiLayerTransformer().transform(Layer.LAYOUT, cord);

                        Line2D lineAngle = new Line2D.Double(anchor, cord);

                        edgeAngle = edgeArrowRenderingSupport.getArrowTransform(rc, lineAngle, destVertexShape);
                        if (edgeAngle == null) return;

                        arrow = edgeAngle.createTransformedShape(arrow);

                        x2 = (float) arrow.getBounds2D().getCenterX();
                        y2 = (float) arrow.getBounds2D().getCenterY();
                    } else {

                        EdgeArrowType edgetype = edge.getArrowType();
                        EnumMap<ScopePoint, javafx.util.Pair<Point2D, AffineTransform>> map;
                        map = getMap(edgetype, second);
                        Point2D fitPoint = null;
                        ArrayList<ScopePoint> scopePoints = null;
                        if (map != null) {
                            javafx.util.Pair<Point2D, AffineTransform> pair = getFitPoint(map, at, second, arrow);
                            fitPoint = pair.getKey();
                            arr = pair.getValue();
                            scopePoints = getUnusedScopePoints(map);
                        }

                        if (fitPoint != null) {
                            x2 = (float) fitPoint.getX();
                            y2 = (float) fitPoint.getY();
                            edge.setAnchorPoint(fitPoint);
                            arrow = arr.createTransformedShape(arrow);

                        } else {
                            arrow = at.createTransformedShape(arrow);
                            x2 = (float) arrow.getBounds2D().getCenterX();
                            y2 = (float) arrow.getBounds2D().getCenterY();
                            Point2D newPoint = new Point2D.Double(arrow.getBounds2D().getCenterX(), arrow.getBounds2D().getCenterY());

                            if (scopePoints != null && !scopePoints.isEmpty()) {
                                map.put(scopePoints.get(scopePoints.size() - 1), new javafx.util.Pair<>(newPoint, at));
                            } else {
                                throw new IllegalArgumentException();
                            }
                            setMap(edgetype, second, map);
                            edge.setAnchorPoint(newPoint);
                        }
                    }

                    AffineTransform xTransform = AffineTransform.getTranslateInstance(x1, y1);
                    dx = x2 - x1;
                    dy = y2 - y1;
                    thetaRadians = (float) Math.atan2(dy, dx);
                    xTransform.rotate(thetaRadians);
                    dist = (float) Math.sqrt(dx * dx + dy * dy);
                    xTransform.scale(dist, 1.0);
                    edgeShape = xTransform.createTransformedShape(oldEdge);

                    setPaintEdge(e, rc, g);
                    drawEdgeArrow(rc, new javafx.util.Pair<>(e, edgeShape), g, arrow, edgeAngle, arr, at);

                }
            }

            // restore old paint
            g.setPaint(oldPaint);
        }

    }

    /**
     *
     * @param rc
     * @param pair
     * @param g
     * @param arrow
     * @param edgeAngle AffineTransform for arrow, if the edge having an anchor point
     * @param arr AffineTransform from the fitPoint
     * @param at AffineTransform for arrow
     */
    private void drawEdgeArrow(RenderContext<V, E> rc, javafx.util.Pair<E, Shape> pair, GraphicsDecorator g, Shape arrow, AffineTransform edgeAngle, AffineTransform arr, AffineTransform at) {
        E e = pair.getKey();
        Shape edgeShape = pair.getValue();
        Paint drawPaint = rc.getEdgeDrawPaintTransformer().transform(e);
        if (drawPaint != null) {
            g.setPaint(drawPaint);
            g.draw(edgeShape);
        }
        Edge edge = (Edge) e;

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
            drawColor = getLuminanceColor(drawColor);
        }
        g.setPaint(drawColor);
        g.fill(arrow);
        g.setPaint(drawColor);
        g.draw(arrow);
    }


    private double angleBetween(Point2D center, Point2D previous, Point2D current) {
        return Math.toDegrees(Math.atan2(current.getX() - center.getX(), current.getY() - center.getY()) -
                Math.atan2(previous.getX() - center.getX(), previous.getY() - center.getY()));
    }


    private Color getLuminanceColor(Paint drawColor) {
        double luminance = (0.2126 * ((Color) drawColor).getRed() + 0.7152 * ((Color) drawColor).getGreen() + 0.0722 * ((Color) drawColor).getGreen());
        return luminance > 127 ? new Color(20, 20, 20) : new Color(245, 245, 245);
    }

    private ArrayList<ScopePoint> getUnusedScopePoints(EnumMap<ScopePoint, javafx.util.Pair<Point2D, AffineTransform>> map) {
        ArrayList<ScopePoint> scopePoints = new ArrayList<>(Arrays.asList(ScopePoint.values()));
        for (Map.Entry<ScopePoint, javafx.util.Pair<Point2D, AffineTransform>> entry : map.entrySet()) {
            scopePoints.remove(entry.getKey());
        }
        return scopePoints;
    }

    private javafx.util.Pair<Point2D, AffineTransform> getFitPoint(EnumMap<ScopePoint, javafx.util.Pair<Point2D, AffineTransform>> map, AffineTransform at, Vertex second, Shape arrow) {
        Point2D fitPoint = null;
        AffineTransform ar = null;
        for (Map.Entry<ScopePoint, javafx.util.Pair<Point2D, AffineTransform>> entry : map.entrySet()) {
            Point2D position = entry.getValue().getKey();
            Shape arrowTemp = at.createTransformedShape(arrow);
            double x2 = (float) arrowTemp.getBounds2D().getCenterX();
            double y2 = (float) arrowTemp.getBounds2D().getCenterY();
            Point2D newPoint = new Point2D.Double(x2, y2);
            double angle = angleBetween(second.getCoordinates(), position, newPoint);
            angle = Math.abs(angle);
            if (angle > 0 && angle <= 45) {
                fitPoint = position;
                ar = entry.getValue().getValue();
                break;
            }
        }
        return new javafx.util.Pair<>(fitPoint, ar);
    }

    private void setMap(EdgeArrowType edgetype, Vertex second, EnumMap<ScopePoint, javafx.util.Pair<Point2D, AffineTransform>> map) {
        switch (edgetype) {
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

    private EnumMap<ScopePoint, javafx.util.Pair<Point2D, AffineTransform>> getMap(EdgeArrowType edgetype, Vertex second) {
        EnumMap<ScopePoint, javafx.util.Pair<Point2D, AffineTransform>> map = null;
        switch (edgetype) {
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

    private void setPaintEdge(E e, RenderContext<V, E> rc, GraphicsDecorator g) {
        Paint fillPaint = rc.getEdgeFillPaintTransformer().transform(e);
        Stroke newStroke = rc.getEdgeStrokeTransformer().transform(e);

        if (newStroke != null)
            g.setStroke(newStroke);

        if (fillPaint != null) {
            g.setPaint(fillPaint);
        }
    }
}
