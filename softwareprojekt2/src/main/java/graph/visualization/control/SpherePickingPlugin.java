package graph.visualization.control;

import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.AbstractGraphMousePlugin;
import edu.uci.ics.jung.visualization.picking.PickedState;
import graph.graph.*;
import graph.visualization.picking.SyndromPickSupport;
import org.apache.commons.collections15.Transformer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class SpherePickingPlugin extends AbstractGraphMousePlugin
        implements MouseListener, MouseMotionListener {
    /**
     * create an instance with passed values
     */
    public SpherePickingPlugin() {
        super(InputEvent.BUTTON3_MASK | InputEvent.BUTTON1_MASK);
        this.cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void mouseClicked(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e)) {
            VisualizationViewer<Vertex, Edge> vv = (VisualizationViewer) e.getSource();
            SyndromPickSupport<Vertex, Edge> pickSupport = (SyndromPickSupport)vv.getPickSupport();
            SyndromGraph<Vertex, Edge> graph = (SyndromGraph<Vertex, Edge>) vv.getGraphLayout().getGraph();
            Sphere sp = pickSupport.getSphere(e.getX(), e.getY());
            Vertex vertex = (Vertex) pickSupport.getVertex(vv.getGraphLayout(),e.getX(), e.getY());
            if (sp == null && vertex == null){
                graph.addSphere(e.getPoint());
                System.out.println("added");
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

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
