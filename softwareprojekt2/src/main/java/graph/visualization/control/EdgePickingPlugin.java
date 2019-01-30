package graph.visualization.control;

import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.util.Pair;
import edu.uci.ics.jung.visualization.control.AbstractGraphMousePlugin;
import edu.uci.ics.jung.visualization.picking.PickedState;
import graph.graph.Edge;
import graph.graph.SyndromGraph;
import graph.graph.Vertex;
import graph.visualization.SyndromVisualisationViewer;
import graph.visualization.picking.SyndromPickSupport;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;

public class EdgePickingPlugin extends AbstractGraphMousePlugin
        implements MouseListener, MouseMotionListener {

    private Edge edgeMove = null;
    private boolean isIncoming = false;
    private int addToSelection = InputEvent.BUTTON1_MASK | InputEvent.SHIFT_MASK;

    /**
     * create an instance with passed values
     */
    public EdgePickingPlugin() {
        super(InputEvent.BUTTON3_MASK | InputEvent.BUTTON1_MASK);
    }


    @SuppressWarnings("unchecked")
    @Override
    public void mousePressed(MouseEvent e) {
        down = e.getPoint();
        SyndromVisualisationViewer<Vertex, Edge> vv = (SyndromVisualisationViewer) e.getSource();
        SyndromPickSupport<Vertex, Edge> pickSupport = (SyndromPickSupport<Vertex, Edge>) vv.getPickSupport();
        Layout<Vertex, Edge> layout = vv.getGraphLayout();
        SyndromGraph<Vertex, Edge> g =  (SyndromGraph<Vertex, Edge>) layout.getGraph();
        Edge edge = (Edge) pickSupport.getEdge(layout, e.getX(), e.getY());
        Vertex vertex = (Vertex) pickSupport.getVertex(layout, e.getX(), e.getY());
        PickedState<Edge> edgePickedState = vv.getPickedEdgeState();
        if (edge != null && vertex == null) {
            Point2D p = vv.getRenderContext().getMultiLayerTransformer().inverseTransform(e.getPoint());
            Pair<Vertex> vertices = g.getEndpoints(edge);
            double distanceFirst = Math.abs(getDistance(p, vertices.getFirst().getCoordinates()));
            double distanceSecond = Math.abs(getDistance(p, vertices.getSecond().getCoordinates()));
            isIncoming = (distanceFirst >= distanceSecond);
            edgeMove = edge;
            if (e.getModifiers() == addToSelection) {
                edgePickedState.pick(edge, true);
            } else if (SwingUtilities.isLeftMouseButton(e)) {
                edgePickedState.clear();
                edgePickedState.pick(edge, true);
            }
        }
    }

    private double getDistance(Point2D pointClick, Point2D pointVertex){
        return Math.hypot(pointClick.getX()-pointVertex.getX(), pointClick.getY()-pointVertex.getY());
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

        if (SwingUtilities.isLeftMouseButton(e) && edgeMove != null) {
            Vertex endpoint = (isIncoming) ? graph.getEndpoints(edgeMove).getSecond():graph.getEndpoints(edgeMove).getFirst();
            Point dragged = e.getPoint();
            Point2D draggedPoint = vv.getRenderContext().getMultiLayerTransformer().inverseTransform(dragged);
            edgeMove.setHasAnchor(true);
            draggedPoint = new Point2D.Double(draggedPoint.getX() - endpoint.getCoordinates().getX(), draggedPoint.getY() - endpoint.getCoordinates().getY());

            if (isIncoming){
                edgeMove.setAnchorPoints(new javafx.util.Pair<>(edgeMove.getAnchorPoints().getKey(), draggedPoint));
            } else {
                edgeMove.setAnchorPoints(new javafx.util.Pair<>(draggedPoint, edgeMove.getAnchorPoints().getValue()));
            }
        }
        vv.repaint();
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

    @Override
    public void mouseClicked(MouseEvent e) {
        //
    }
}
