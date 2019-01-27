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
import javafx.geometry.Bounds;
import javafx.scene.transform.Affine;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
                    boolean drawArrow = true;
                    AffineTransform arr = null;
                    Color paintTogetherFill = new Color(236,236,236);
                    Color paintTogetherDraw = Color.BLACK;


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
                        Float arrow_x = (float) arrow.getBounds2D().getCenterX();
                        Float arrow_y = (float) arrow.getBounds().getCenterY();
                        Float destionation_v_x = (float) destVertexShape.getBounds().getCenterX();
                        Float destionation_v_y = (float) destVertexShape.getBounds().getCenterY();
                        Shape ar = at.createTransformedShape(arrow);

                        Float ax = (float) ar.getBounds2D().getCenterX();
                        Float ay = (float) ar.getBounds().getCenterY();

                        ScopePoint direction = null;
                        if (ax > destionation_v_x) {
                            if (ay < destionation_v_y) {
                                direction = ScopePoint.POS_1;
                            } else {
                                direction = ScopePoint.POS_2;
                            }
                        } else {
                            if (ay < destionation_v_y) {
                                direction = ScopePoint.POS_4;
                            } else {
                                direction = ScopePoint.POS_3;
                            }
                        }

                        EdgeArrowType edgetype = edge.getArrowType();
                        EnumMap<ScopePoint, javafx.util.Pair<Point2D, AffineTransform>> map = new EnumMap<>(ScopePoint.class);
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

                        Point2D point2D = null;
                        javafx.util.Pair pair = null;
                        switch (direction) {
                            case POS_1:
                                pair = map.get(ScopePoint.POS_1);
                                if (pair != null){
                                    point2D = map.get(ScopePoint.POS_1).getKey();
                                    arr = map.get(ScopePoint.POS_1).getValue();
                                }
                                break;
                            case POS_2:
                                pair = map.get(ScopePoint.POS_2);
                                if (pair != null){
                                    point2D = map.get(ScopePoint.POS_2).getKey();
                                    arr = map.get(ScopePoint.POS_2).getValue();
                                }
                                break;
                            case POS_3:
                                pair = map.get(ScopePoint.POS_3);
                                if (pair != null){
                                    point2D = map.get(ScopePoint.POS_3).getKey();
                                    arr = map.get(ScopePoint.POS_3).getValue();
                                }
                                break;
                            case POS_4:
                                 pair = map.get(ScopePoint.POS_4);
                                if (pair != null){
                                    point2D = map.get(ScopePoint.POS_4).getKey();
                                    arr = map.get(ScopePoint.POS_4).getValue();
                                }
                                break;
                        }

                        if (point2D != null) {
                            //drawArrow = false;
                            x2 = (float) point2D.getX();
                            y2 = (float) point2D.getY();
                            edge.setAnchorPoint(point2D);
                            arrow = arr.createTransformedShape(arrow);

                        } else {
                            arrow = at.createTransformedShape(arrow);
                            x2 = (float) arrow.getBounds2D().getCenterX();
                            y2 = (float) arrow.getBounds2D().getCenterY();
                            Point2D newPoint = new Point2D.Double(arrow.getBounds2D().getCenterX(), arrow.getBounds2D().getCenterY());

                            map.put(direction, new javafx.util.Pair<>(newPoint, at));
                            edge.setAnchorPoint(newPoint);
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
                    
                    }

                    AffineTransform xTransform = AffineTransform.getTranslateInstance(x1, y1);
                    dx = x2 - x1;
                    dy = y2 - y1;
                    thetaRadians = (float) Math.atan2(dy, dx);
                    xTransform.rotate(thetaRadians);
                    dist = (float) Math.sqrt(dx * dx + dy * dy);
                    xTransform.scale(dist, 1.0);
                    edgeShape = xTransform.createTransformedShape(oldEdge);

                    Paint fillPaint = rc.getEdgeFillPaintTransformer().transform(e);
                    Stroke newStroke = rc.getEdgeStrokeTransformer().transform(e);

                    if (newStroke != null)
                        g.setStroke(newStroke);

                    if (fillPaint != null) {
                        g.setPaint(fillPaint);
                        g.fill(edgeShape);
                    }
                    Paint drawPaint = rc.getEdgeDrawPaintTransformer().transform(e);
                    if (drawPaint != null) {
                        g.setPaint(drawPaint);
                        g.draw(edgeShape);
                    }

                    g.setStroke(new BasicStroke(1.0f));


                    Paint drawColor = rc.getArrowFillPaintTransformer().transform(e);
                    if (edge.getArrowType() == EdgeArrowType.NEUTRAL) {
                        Shape ellipse = new Ellipse2D.Double(-18.06472, -18.06472 / 2, 18.06472, 18.06472);

                        if (edgeAngle != null){
                            ellipse = edgeAngle.createTransformedShape(ellipse);
                        } else {
                            ellipse = (arr != null) ? arr.createTransformedShape(ellipse) : at.createTransformedShape(ellipse);
                        }

                        if (drawArrow){
                            if (arr != null){
                                g.setPaint(paintTogetherFill);
                                g.fill(ellipse);
                                g.setPaint(paintTogetherDraw);
                                g.draw(ellipse);

                                paintTogetherFill = paintTogetherDraw;
                            } else {
                                g.setPaint(rc.getArrowFillPaintTransformer().transform(e));
                                g.fill(ellipse);
                                g.draw(ellipse);
                            }
                        }


                        drawColor = getLuminanceColor(drawColor);

                    }
                    g.setPaint(drawColor);


                        if (arr != null) {
                            g.setPaint(paintTogetherFill);
                            g.fill(arrow);
                            g.setPaint(paintTogetherDraw);
                            g.draw(arrow);
                        } else {
                            g.fill(arrow);
                            g.setPaint(drawColor);
                            g.draw(arrow);
                        }


                }
            }

            // restore old paint
            g.setPaint(oldPaint);
        }

    }

    private Color getLuminanceColor(Paint drawColor) {
        double luminance = (0.2126 * ((Color) drawColor).getRed() + 0.7152 * ((Color) drawColor).getGreen() + 0.0722 * ((Color) drawColor).getGreen());
        return luminance > 127 ? new Color(20, 20, 20) : new Color(245, 245, 245);
    }
}
