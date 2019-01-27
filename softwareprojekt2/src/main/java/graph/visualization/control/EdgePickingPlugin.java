package graph.visualization.control;

import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.visualization.Layer;
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
        Edge edge = (Edge) pickSupport.getEdge(layout, e.getX(), e.getY());
        Vertex vertex = (Vertex) pickSupport.getVertex(layout, e.getX(), e.getY());
        PickedState<Edge> edgePickedState = vv.getPickedEdgeState();
        if (edge != null && vertex == null) {
            edgeMove = edge;
            if (e.getModifiers() == InputEvent.SHIFT_MASK) {
                edgePickedState.pick(edge, true);
            } else if (SwingUtilities.isLeftMouseButton(e)) {
                edgePickedState.clear();
                edgePickedState.pick(edge, true);
            }
        }
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
            Vertex endpoint = graph.getEndpoints(edgeMove).getSecond();
            Point dragged = e.getPoint();
            Point2D draggedPoint = vv.getRenderContext().getMultiLayerTransformer().transform(Layer.LAYOUT, dragged);
            edgeMove.setHasAnchor(true);
            draggedPoint = new Point2D.Double(draggedPoint.getX() - endpoint.getCoordinates().getX(), draggedPoint.getY() - endpoint.getCoordinates().getY());
            edgeMove.setAnchorPoint(draggedPoint);
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
