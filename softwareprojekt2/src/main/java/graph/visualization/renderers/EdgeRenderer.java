package graph.visualization.renderers;

import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.util.Context;
import edu.uci.ics.jung.graph.util.EdgeIndexFunction;
import edu.uci.ics.jung.graph.util.EdgeType;
import edu.uci.ics.jung.graph.util.Pair;
import edu.uci.ics.jung.visualization.Layer;
import edu.uci.ics.jung.visualization.RenderContext;
import edu.uci.ics.jung.visualization.decorators.EdgeShape;
import edu.uci.ics.jung.visualization.renderers.BasicEdgeRenderer;
import edu.uci.ics.jung.visualization.transform.LensTransformer;
import edu.uci.ics.jung.visualization.transform.MutableTransformer;
import edu.uci.ics.jung.visualization.transform.shape.GraphicsDecorator;
import graph.graph.Edge;
import graph.graph.EdgeArrowType;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.Objects;

/**
 * The EdgeRenderer renderers all edges from the syndrom graph. If edges converge at the vertex with the same arrowhead
 * in a specific area at the vertex, the arrowheads are grouped together. If the edge has an anchor point at the vertex,
 * the edge is drawn there.
 *
 * @param <V> The vertex type.
 * @param <E> The edge type.
 */
public class EdgeRenderer<V, E> extends BasicEdgeRenderer<V, E> {

    @Override
    protected void drawSimpleEdge(RenderContext<V, E> rc, Layout<V, E> layout, E e) {
        GraphicsDecorator g = rc.getGraphicsContext();
        Graph<V, E> graph = layout.getGraph();
        Pair<V> endpoints = graph.getEndpoints(e);
        V v1 = endpoints.getFirst();
        V v2 = endpoints.getSecond();

        Point2D p1 = layout.transform(v1);
        Point2D p2 = layout.transform(v2);
        p1 = rc.getMultiLayerTransformer().transform(Layer.LAYOUT, p1);
        p2 = rc.getMultiLayerTransformer().transform(Layer.LAYOUT, p2);
        float x1 = (float) p1.getX();
        float y1 = (float) p1.getY();
        float x2 = (float) p2.getX();
        float y2 = (float) p2.getY();

        Shape s2 = rc.getVertexShapeTransformer().transform(v2);
        Shape edgeShape = rc.getEdgeShapeTransformer().transform(Context.<Graph<V, E>, E>getInstance(graph, e));

        boolean edgeHit = true;
        boolean arrowHit = true;
        Rectangle deviceRectangle = null;
        JComponent vv = rc.getScreenDevice();
        if (vv != null) {
            Dimension d = vv.getSize();
            deviceRectangle = new Rectangle(0, 0, d.width, d.height);
        }

        AffineTransform xform = AffineTransform.getTranslateInstance(x1, y1);


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

        if (edgeHit == true) {

            Paint oldPaint = g.getPaint();

            // get Paints for filling and drawing
            // (filling is done first so that drawing and label use same Paint)
            Paint fill_paint = rc.getEdgeFillPaintTransformer().transform(e);
            if (fill_paint != null) {
                g.setPaint(fill_paint);
                g.fill(edgeShape);
            }
            Paint draw_paint = rc.getEdgeDrawPaintTransformer().transform(e);
            if (draw_paint != null) {
                g.setPaint(draw_paint);
                g.draw(edgeShape);
            }

            float scalex = (float) g.getTransform().getScaleX();
            float scaley = (float) g.getTransform().getScaleY();
            // see if arrows are too small to bother drawing
            if (scalex < .3 || scaley < .3) return;

            if (rc.getEdgeArrowPredicate().evaluate(Context.<Graph<V, E>, E>getInstance(graph, e))) {

                Stroke new_stroke = rc.getEdgeArrowStrokeTransformer().transform(e);
                Stroke old_stroke = g.getStroke();
                if (new_stroke != null)
                    g.setStroke(new_stroke);


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
                    arrow = at.createTransformedShape(arrow);
                    Edge edge = (Edge) e;
                    Paint drawColor = rc.getArrowFillPaintTransformer().transform(e);
                    if (edge.getArrowType() == EdgeArrowType.NEUTRAL) {
                        Shape ellipse = new Ellipse2D.Double(-18.06472, -18.06472 / 2, 18.06472, 18.06472);
                        ellipse = at.createTransformedShape(ellipse);

                        g.setPaint(rc.getArrowFillPaintTransformer().transform(e));
                        g.fill(ellipse);
                        g.draw(ellipse);

                        double luminesce = (0.2126*((Color) drawColor).getRed() + 0.7152*((Color) drawColor).getGreen() + 0.0722*((Color) drawColor).getGreen());

                        if (luminesce > 127){
                            drawColor = new Color(20,20,20);
                        } else {
                            drawColor = new Color(245,245,245);
                        }
                    }
                    g.setPaint(drawColor);
                    g.fill(arrow);
                    g.setPaint(drawColor);
                    g.draw(arrow);

                }

                // restore paint and stroke
                if (new_stroke != null)
                    g.setStroke(old_stroke);

            }

            // restore old paint
            g.setPaint(oldPaint);
        }
    }
}
