package graph.visualization.control;

import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.visualization.control.AbstractGraphMousePlugin;
import edu.uci.ics.jung.visualization.picking.PickedState;
import graph.graph.Edge;
import graph.graph.Sphere;
import graph.graph.SyndromGraph;
import graph.graph.Vertex;
import graph.visualization.SyndromVisualisationViewer;
import graph.visualization.picking.SyndromPickSupport;
import gui.GraphButtonType;
import gui.Values;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Collection;

public class VertexPickingPlugin extends AbstractGraphMousePlugin
        implements MouseListener, MouseMotionListener {
    private Vertex source;
    /**
     * create an instance with passed values
     */
    public VertexPickingPlugin() {
        super(InputEvent.BUTTON3_MASK | InputEvent.BUTTON1_MASK);
        this.cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e)) {
            SyndromVisualisationViewer<Vertex, Edge> vv = (SyndromVisualisationViewer) e.getSource();
            SyndromPickSupport<Vertex, Edge> pickSupport = (SyndromPickSupport) vv.getPickSupport();
            SyndromGraph<Vertex, Edge> graph = (SyndromGraph<Vertex, Edge>) vv.getGraphLayout().getGraph();
            Sphere sp = pickSupport.getSphere(e.getX(), e.getY());
            Vertex vertex = (Vertex) pickSupport.getVertex(vv.getGraphLayout(), e.getX(), e.getY());

            Values values = Values.getInstance();
            Collection<Vertex> vertices = graph.getVertices();

            if (values.getGraphButtonType() == GraphButtonType.ADD_VERTEX) {
                if (sp != null && vertex == null) {
                    Vertex newVertex = graph.addVertex(e.getPoint(), sp);
                    vv.getGraphLayout().setLocation(newVertex, e.getPoint());
                }
            }
            vv.repaint();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        down = e.getPoint();
        SyndromVisualisationViewer vv = (SyndromVisualisationViewer) e.getSource();
        SyndromPickSupport<Vertex, Edge> pickSupport = (SyndromPickSupport) vv.getPickSupport();
        Layout layout = vv.getGraphLayout();
        Vertex vert = (Vertex) pickSupport.getVertex(layout, e.getX(), e.getY());

        if (SwingUtilities.isLeftMouseButton(e)) {
            PickedState<Vertex> vertexPickedState = vv.getPickedVertexState();
            if (vert != null) {
                if (!vertexPickedState.isPicked(vert)) {
                    vertexPickedState.pick(vert, true);
                }
                vv.repaint();
            } else {
                vertexPickedState.clear();
            }
        } else if (SwingUtilities.isRightMouseButton(e)){
            if (vert != null){
                source = vert;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        SyndromVisualisationViewer vv = (SyndromVisualisationViewer) e.getSource();
        SyndromPickSupport<Vertex, Edge> pickSupport = (SyndromPickSupport) vv.getPickSupport();
        Layout layout = vv.getGraphLayout();
        Vertex vert = (Vertex) pickSupport.getVertex(layout, e.getX(), e.getY());
        if (SwingUtilities.isRightMouseButton(e)){
            if (vert != null && source != null && !source.equals(vert)){
                SyndromGraph<Vertex, Edge> graph = (SyndromGraph<Vertex, Edge>) layout.getGraph();
                graph.addEdge(source, vert);
            }
            vv.repaint();
        }
        source = null;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
