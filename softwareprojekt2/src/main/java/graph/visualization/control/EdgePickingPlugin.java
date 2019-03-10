package graph.visualization.control;

import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.util.Pair;
import edu.uci.ics.jung.visualization.control.AbstractGraphMousePlugin;
import edu.uci.ics.jung.visualization.picking.PickedState;
import graph.graph.*;
import graph.visualization.SyndromVisualisationViewer;
import graph.visualization.picking.SyndromPickSupport;
import gui.EdgeContextMenu;
import gui.Values;
import javafx.scene.control.ContextMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;

/**
 * Handles the mouse-interaction on the edges.
 *
 * @author Nina Unterberg
 */
public class EdgePickingPlugin extends AbstractGraphMousePlugin
        implements MouseListener, MouseMotionListener {

    /**
     * The edge, that will be moved by the user.
     */
    private Edge edgeMove = null;
    /**
     * Indicator, if the edge is an incoming edge.
     */
    private boolean isIncoming = false;
    /**
     * The contextmenu of the edge.
     */
    private ContextMenu contextMenu;
    /**
     * The helper-functions.
     */
    private HelperFunctions helper = new HelperFunctions();

    /**
     * Create an instance with passed values.
     */
    public EdgePickingPlugin() {
        super(InputEvent.BUTTON3_DOWN_MASK | InputEvent.BUTTON1_DOWN_MASK);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void mousePressed(MouseEvent e) {
        if (contextMenu != null) {
            helper.hideMenu(contextMenu);
        }
        down = e.getPoint();
        SyndromVisualisationViewer<Vertex, Edge> vv = (SyndromVisualisationViewer) e.getSource();
        SyndromPickSupport<Vertex, Edge> pickSupport = (SyndromPickSupport<Vertex, Edge>) vv.getPickSupport();
        Layout<Vertex, Edge> layout = vv.getGraphLayout();
        SyndromGraph<Vertex, Edge> g = (SyndromGraph<Vertex, Edge>) layout.getGraph();
        Edge edge = (Edge) pickSupport.getEdge(layout, e.getX(), e.getY());
        Vertex vertex = (pickSupport).getVertex(layout, e.getX(), e.getY());
        PickedState<Edge> edgePickedState = vv.getPickedEdgeState();
        int addToSelection = InputEvent.BUTTON1_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK;
        if (edge != null && vertex == null) {
            Point2D p = vv.getRenderContext().getMultiLayerTransformer().inverseTransform(e.getPoint());
            Pair<Vertex> vertices = g.getEndpoints(edge);
            double distanceFirst = Math.abs(getDistance(p, vertices.getFirst().getCoordinates()));
            double distanceSecond = Math.abs(getDistance(p, vertices.getSecond().getCoordinates()));
            isIncoming = (distanceFirst >= distanceSecond);
            edgeMove = edge;
            if (e.getModifiersEx() == addToSelection) {
                edgePickedState.pick(edge, true);
            } else if (SwingUtilities.isLeftMouseButton(e)) {
                edgePickedState.clear();
                edgePickedState.pick(edge, true);
            }
        }
    }

    /**
     * Calculates the distance between two points.
     *
     * @param pointClick  the first point
     * @param pointVertex the second point
     * @return the distance
     */
    private double getDistance(Point2D pointClick, Point2D pointVertex) {
        return Math.hypot(pointClick.getX() - pointVertex.getX(), pointClick.getY() - pointVertex.getY());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        edgeMove = null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void mouseDragged(MouseEvent e) {
        SyndromVisualisationViewer<Vertex, Edge> vv = (SyndromVisualisationViewer) e.getSource();
        SyndromGraph<Vertex, Edge> graph = (SyndromGraph<Vertex, Edge>) vv.getGraphLayout().getGraph();
        if (SwingUtilities.isRightMouseButton(e) && edgeMove != null) {
            Vertex endpoint = (isIncoming) ? graph.getEndpoints(edgeMove).getSecond() : graph.getEndpoints(edgeMove).getFirst();
            Point dragged = e.getPoint();
            Point2D draggedPoint = vv.getRenderContext().getMultiLayerTransformer().inverseTransform(dragged);
            draggedPoint = new Point2D.Double(draggedPoint.getX() - endpoint.getCoordinates().getX(), draggedPoint.getY() - endpoint.getCoordinates().getY());

            if (isIncoming) {
                edgeMove.setAnchorPoints(new javafx.util.Pair<>(edgeMove.getAnchorPoints().getKey(), draggedPoint));
                edgeMove.setHasAnchorIn(true);
            } else {
                edgeMove.setAnchorPoints(new javafx.util.Pair<>(draggedPoint, edgeMove.getAnchorPoints().getValue()));
                edgeMove.setHasAnchorOut(true);
            }
        }
        vv.repaint();
    }

    @Override
    @SuppressWarnings("unchecked")
    public void mouseClicked(MouseEvent e) {
        SyndromVisualisationViewer<Vertex, Edge> vv = (SyndromVisualisationViewer<Vertex, Edge>) e.getSource();
        SyndromPickSupport<Vertex, Edge> pickSupport = (SyndromPickSupport<Vertex, Edge>) vv.getPickSupport();
        Point2D point = e.getPoint();
        Vertex vertex = pickSupport.getVertex(vv.getGraphLayout(), point.getX(), point.getY());
        Edge edge = (Edge) pickSupport.getEdge(vv.getGraphLayout(), point.getX(), point.getY());

        if (SwingUtilities.isRightMouseButton(e)) {
            if (vertex == null && edge != null && Values.getInstance().getMode() != FunctionMode.ANALYSE) {
                PickedState<Edge> edges = vv.getPickedEdgeState();
                edges.clear();
                edges.pick(edge, true);
                contextMenu = new EdgeContextMenu(edge).getContextMenu();
                helper.showSideMenu(e.getLocationOnScreen(), contextMenu);

            }
            vv.repaint();
            Syndrom.getInstance().getVv2().repaint();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        //
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //
    }
}
