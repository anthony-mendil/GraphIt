package graph.visualization.control;

import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.AbstractGraphMousePlugin;
import graph.graph.*;
import graph.visualization.picking.SyndromPickSupport;
import gui.Values;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;
import java.util.List;

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
            System.out.println("sphere: "+sp);

            Vertex vertex = (Vertex) pickSupport.getVertex(vv.getGraphLayout(),e.getX(), e.getY());
            List<Sphere> list = graph.getSpheres();
            Values values = Values.getInstance();
            Rectangle2D newRec = new Rectangle2D.Double(e.getX(), e.getY(), values.getDefaultWidthSphere(),
                    values.getDefaultHeightSphere());
            boolean addSphere = true;
            if (sp == null && vertex == null){
                for (Object s: list) {
                    Sphere sphere = (Sphere) s;
                    double startY = sphere.getCoordinates().getY();
                    for(int j = (int)startY; j<= startY+sphere.getHeight(); j++){
                        if (newRec.contains(sphere.getCoordinates().getX(), j)){
                            addSphere = false;
                        }
                    }
                    double startX = sphere.getCoordinates().getX();
                    for(int i = (int)startX; i<= startX+sphere.getWidth(); i++){
                        if (newRec.contains(i, sphere.getCoordinates().getY())){
                            addSphere = false;
                        }
                    }
                }
                if(addSphere){
                    graph.addSphere(e.getPoint());
                    vv.setGraphLayout(vv.getGraphLayout());
                    System.out.println("added");
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //
    }

    @Override
    public void mouseReleased(MouseEvent e) {
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
    public void mouseDragged(MouseEvent e) {
        //
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        //
    }
}
