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
    private RenderHelperFunction renderHelperFunction = new RenderHelperFunction();

    private static Logger logger = Logger.getLogger(EdgeRenderer.class);

    @Override
    @SuppressWarnings("unchecked")
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
            float scalex = (float) g.getTransform().getScaleX();
            float scaley = (float) g.getTransform().getScaleY();
            // see if arrows are too small to bother drawing
            if (scalex < .3 || scaley < .3) return;

            if (rc.getEdgeArrowPredicate().evaluate(Context.getInstance(graph, e))) {

                Pair<V> endP = graph.getEndpoints(e);
                Vertex second = (Vertex) endP.getSecond();
                Vertex first = (Vertex) endP.getFirst();
                Shape destVertexShape =
                        rc.getVertexShapeTransformer().transform(graph.getEndpoints(e).getSecond());
                Shape departVertexShape = rc.getVertexShapeTransformer().transform(graph.getEndpoints(e).getFirst());

                AffineTransform xf = AffineTransform.getTranslateInstance(x2, y2);
                destVertexShape = xf.createTransformedShape(destVertexShape);

                AffineTransform xTransform = AffineTransform.getTranslateInstance(x1, y1);
                departVertexShape = xTransform.createTransformedShape(departVertexShape);


                arrowHit = rc.getMultiLayerTransformer().getTransformer(Layer.VIEW).transform(destVertexShape).intersects(deviceRectangle);
                if (arrowHit) {
                    AffineTransform at = edgeArrowRenderingSupport.getArrowTransform(rc, edgeShape, destVertexShape);
                    if (at == null) return;
                    Shape arrow = rc.getEdgeArrowTransformer().transform(Context.getInstance(graph, e));
                    Edge edge = (Edge) e;
                    AffineTransform edgeAngle = null;
                    AffineTransform outEdgeAngle;
                    AffineTransform arr = null;

                    Point2D an = edge.getAnchorPoints().getValue();
                    Point2D outgoing = edge.getAnchorPoints().getKey();
                    if (edge.isHasAnchorIn() && (an != null)) {
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
                    }

                    if (edge.isHasAnchorOut() && outgoing != null) {
                        Point2D outCoord = first.getCoordinates();
                        Point2D oT = new Point2D.Double(outgoing.getX() + outCoord.getX(), outgoing.getY() + outCoord.getY());
                        Point2D outAnchor = rc.getMultiLayerTransformer().transform(Layer.LAYOUT, oT);
                        outCoord = rc.getMultiLayerTransformer().transform(Layer.LAYOUT, outCoord);
                        Line2D outLineAngle = new Line2D.Double(outAnchor, outCoord);
                        outEdgeAngle = edgeArrowRenderingSupport.getArrowTransform(rc, outLineAngle, departVertexShape);
                        if (outEdgeAngle == null) return;

                        Shape tryP = new Ellipse2D.Double(0, 0, 5, 5);
                        tryP = outEdgeAngle.createTransformedShape(tryP);

                        x1 = (float) tryP.getBounds2D().getCenterX();
                        y1 = (float) tryP.getBounds2D().getCenterY();
                    }

                    if (!edge.isHasAnchorIn()) {
                        EdgeArrowType edgetype = edge.getArrowType();
                        Map<ScopePoint, javafx.util.Pair<Point2D, AffineTransform>> map;
                        map = getMap(edgetype, second);
                        Point2D fitPoint = null;
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

                            javafx.util.Pair<Point2D, AffineTransform> nextPoint = getNextFreePoint(edgetype, original, second, at, arrow, destVertexShape, rc);

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

                            ///

                            if (scopePoints != null && !scopePoints.isEmpty()) {
                                map.put(scopePoints.get(scopePoints.size() - 1), new javafx.util.Pair<>(newPoint, at));
                            } else {
                                throw new IllegalArgumentException();
                            }
                            setMap(edgetype, second, map);
                            edge.setAnchorPoints(new javafx.util.Pair<>(edge.getAnchorPoints().getKey(), newPoint));
                        }
                    }

                    AffineTransform affineTransform = AffineTransform.getTranslateInstance(x1, y1);
                    dx = x2 - x1;
                    dy = y2 - y1;
                    thetaRadians = (float) Math.atan2(dy, dx);
                    affineTransform.rotate(thetaRadians);
                    dist = (float) Math.sqrt(dx * dx + dy * dy);
                    affineTransform.scale(dist, 1.0);
                    edgeShape = affineTransform.createTransformedShape(oldEdge);

                    setPaintEdge(e, rc, g);
                    drawEdgeArrow(rc, new javafx.util.Pair<>(e, edgeShape), g, arrow, edgeAngle, arr, at, new Point2D.Double(x1, y1));
                }
            }

            // restore old paint
            g.setPaint(oldPaint);
        }

    }

    @SuppressWarnings("unchecked")
    private javafx.util.Pair<Point2D, AffineTransform> getPoint(Ellipse2D help, Point2D center, Point2D avoid, Shape arrow1, Shape arrow2, RenderContext<V, E> rc, Shape vertexShape, Shape arrow3) {
        double[] coordinates = new double[6];
        Point2D.Double point;
        Point2D.Double newP = null;
        javafx.util.Pair newPair = null;
        Boolean isMinus = null;
        javafx.util.Pair<Point2D, AffineTransform> pair = null;

        for (PathIterator pi = new GeneralPath(help).getPathIterator(null, 0.1); !pi.isDone(); pi.next()) {
            pi.currentSegment(coordinates);
            double cx = coordinates[0];
            double cy = coordinates[1];
            point = new Point2D.Double(cx, cy);

            pair = getIntersection(point, arrow1, arrow2, center, rc, vertexShape, arrow3);

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

    @SuppressWarnings("unchecked")
    private javafx.util.Pair<Point2D, AffineTransform> getIntersection(Point2D p2, Shape arrow1, Shape arrow2, Point2D center, RenderContext<V, E> rc, Shape vertexShape, Shape arrow3) {
        Line2D line = new Line2D.Double(p2, center);

        AffineTransform a2 = edgeArrowRenderingSupport.getArrowTransform(rc, line, vertexShape);
        if (a2 == null) return null;

        Shape s2 = a2.createTransformedShape(arrow2);

        if (!s2.getBounds2D().intersects(arrow1.getBounds2D())) {
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
            javafx.util.Pair<Point2D, AffineTransform> pair = getIntersection(original, f, arrow, center, rc, vertexShape, null);
            if (pair != null) {
                return pair;
            } else {
                return getPoint(help, center, pointF, f, arrow, rc, vertexShape, null);
            }
        } else if (pointF == null) {
            s = second.getValue().createTransformedShape(s);
            javafx.util.Pair<Point2D, AffineTransform> pair = getIntersection(original, s, arrow, center, rc, vertexShape, null);
            if (pair != null) {
                return pair;
            } else {
                return getPoint(help, center, pointS, s, arrow, rc, vertexShape, null);
            }
        } else {
            s = second.getValue().createTransformedShape(s);
            f = first.getValue().createTransformedShape(f);
            javafx.util.Pair<Point2D, AffineTransform> pair = getIntersection(original, s, arrow, center, rc, vertexShape, f);
            if (pair != null) {
                return pair;
            } else {
                return getPoint(help, center, pointS, s, arrow, rc, vertexShape, f);
            }
        }
    }

    /**
     *
     */
    private void drawEdgeArrow(RenderContext<V, E> rc, javafx.util.Pair<E, Shape> pair, GraphicsDecorator g, Shape arrow, AffineTransform edgeAngle, AffineTransform arr, AffineTransform at, Point2D anchorPoint) {
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


    private double angleBetween(Point2D center, Point2D previous, Point2D current) {
        return Math.toDegrees(Math.atan2(current.getX() - center.getX(), current.getY() - center.getY()) -
                Math.atan2(previous.getX() - center.getX(), previous.getY() - center.getY()));
    }

    private ArrayList<ScopePoint> getUnusedScopePoints(Map<ScopePoint, javafx.util.Pair<Point2D, AffineTransform>> map) {
        ArrayList<ScopePoint> scopePoints = new ArrayList<>(Arrays.asList(ScopePoint.values()));
        for (Map.Entry<ScopePoint, javafx.util.Pair<Point2D, AffineTransform>> entry : map.entrySet()) {
            scopePoints.remove(entry.getKey());
        }
        return scopePoints;
    }

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

    private void setMap(EdgeArrowType edgetype, Vertex second, Map<ScopePoint, javafx.util.Pair<Point2D, AffineTransform>> map) {
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

    private Map<ScopePoint, javafx.util.Pair<Point2D, AffineTransform>> getMap(EdgeArrowType edgetype, Vertex second) {
        Map<ScopePoint, javafx.util.Pair<Point2D, AffineTransform>> map = null;
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
