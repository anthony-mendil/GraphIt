package graph.visualization.control;

import edu.uci.ics.jung.visualization.VisualizationViewer;
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
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.List;
import java.util.Set;

public class SpherePickingPlugin extends AbstractGraphMousePlugin
        implements MouseListener, MouseMotionListener {
    private Sphere sphere;
    private Sphere lastSphere;
    private Point2D downFirst = null;

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
            SyndromPickSupport<Vertex, Edge> pickSupport = (SyndromPickSupport) vv.getPickSupport();
            SyndromGraph<Vertex, Edge> graph = (SyndromGraph<Vertex, Edge>) vv.getGraphLayout().getGraph();
            Sphere sp = pickSupport.getSphere(e.getX(), e.getY());
            Vertex vertex = (Vertex) pickSupport.getVertex(vv.getGraphLayout(), e.getX(), e.getY());
            List<Sphere> list = graph.getSpheres();
            Values values = Values.getInstance();
            Rectangle2D newRec = new Rectangle2D.Double(e.getX(), e.getY(), values.getDefaultWidthSphere(),
                    values.getDefaultHeightSphere());
            boolean addSphere = true;
            if (values.getGraphButtonType() == GraphButtonType.ADD_SPHERE) {
                if (sp == null && vertex == null) {
                    for (Object s : list) {
                        Sphere sphere = (Sphere) s;
                        double startY = sphere.getCoordinates().getY();
                        for (int j = (int) startY; j <= startY + sphere.getHeight(); j++) {
                            if (newRec.contains(sphere.getCoordinates().getX(), j)) {
                                addSphere = false;
                            }
                        }
                        double startX = sphere.getCoordinates().getX();
                        for (int i = (int) startX; i <= startX + sphere.getWidth(); i++) {
                            if (newRec.contains(i, sphere.getCoordinates().getY())) {
                                addSphere = false;
                            }
                        }
                    }
                    if (addSphere) {
                        graph.addSphere(e.getPoint());
                    }
                }
            }
            /**if (values.getGraphButtonType() == GraphButtonType.REMOVE_SPHERE && sp != null){
             graph.removeSphere(sp);
             }*/
            vv.repaint();
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public void mousePressed(MouseEvent e) {
        down = e.getPoint();
        System.out.println("down "+down);
        SyndromVisualisationViewer vv = (SyndromVisualisationViewer) e.getSource();
        SyndromPickSupport<Vertex, Edge> pickSupport = (SyndromPickSupport) vv.getPickSupport();
        Sphere sp = pickSupport.getSphere(e.getX(), e.getY());
        sphere = sp;

        if (SwingUtilities.isLeftMouseButton(e)) {
            PickedState<Sphere> pickedSphereState = vv.getPickedSphereState();
            if (sp != null) {
                if (!pickedSphereState.isPicked(sp)) {
                    pickedSphereState.clear();
                    pickedSphereState.pick(sp, true);
                } /*else {
                    pickedSphereState.pick(sp, false);
                }*/

                vv.repaint();
            } else {
                pickedSphereState.clear();
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e)) {
            SyndromVisualisationViewer vv = (SyndromVisualisationViewer) e.getSource();
            SyndromPickSupport<Vertex, Edge> pickSupport = (SyndromPickSupport) vv.getPickSupport();
            PickedState<Sphere> spherePickedState = vv.getPickedSphereState();
            Set<Sphere> spheres = spherePickedState.getPicked();


              /*  for (Sphere s : spheres) {
                    double y = s.getCoordinates().getY();
                    double height = y + s.getHeight();
                    double x = s.getCoordinates().getX();
                    double width = x + s.getWidth();

                    for (int j = (int) y ; j < height + y; j++) {
                        List<Sphere> sp = pickSupport.getSpheres(x+width, j);

                        for (Sphere containingSphere: sp){
                            if (!containingSphere.equals(s)){
                                s.setCoordinates(new Point2D.Double(downFirst.getX(), downFirst.getY()));
                                break;
                            }
                        }
                    }

                    for (int j = (int) y; j < width + y; j++) {
                        List<Sphere> sp = pickSupport.getSpheres(j, y+height);

                        for (Sphere containingSphere: sp){
                            if (!containingSphere.equals(s)){
                                s.setCoordinates(new Point2D.Double(downFirst.getX(), downFirst.getY()));
                                break;
                            }
                        }
                    }
                }*/


            downFirst = null;
            vv.repaint();

        }
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
    @SuppressWarnings("unchecked")
    public void mouseDragged(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e)) {
            SyndromVisualisationViewer<Vertex, Edge> vv = (SyndromVisualisationViewer<Vertex, Edge>) e.getSource();

            if (sphere != null) {
                Point p = e.getPoint();
                Point2D graphPoint = vv.getRenderContext().getMultiLayerTransformer().inverseTransform(p);
                Point2D graphDown = vv.getRenderContext().getMultiLayerTransformer().inverseTransform(down);
                double dx = graphPoint.getX() - graphDown.getX();
                double dy = graphPoint.getY() - graphDown.getY();
                PickedState<Sphere> spherePickedState = vv.getPickedSphereState();
                SyndromPickSupport<Vertex, Edge> pickSupport = (SyndromPickSupport) vv.getPickSupport();

                for (Sphere s : spherePickedState.getPicked()) {
                    if (downFirst == null) {
                        downFirst = s.getCoordinates();
                    }
                    s.setCoordinates(new Point2D.Double(s.getCoordinates().getX() + dx, s.getCoordinates().getY() + dy));
                }
                down = p;
                vv.repaint();
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        //
    }
}
