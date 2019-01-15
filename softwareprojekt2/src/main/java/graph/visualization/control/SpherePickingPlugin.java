package graph.visualization.control;

import actions.ActionHistory;
import actions.add.AddSphereLogAction;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.visualization.Layer;
import edu.uci.ics.jung.visualization.RenderContext;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.AbstractGraphMousePlugin;
import edu.uci.ics.jung.visualization.picking.PickedState;
import graph.graph.*;
import graph.visualization.SyndromVisualisationViewer;
import graph.visualization.picking.SyndromPickSupport;
import graph.visualization.transformer.sphere.SphereShapeTransformer;
import gui.GraphButtonType;
import gui.Values;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.List;
import java.util.Set;

public class SpherePickingPlugin extends AbstractGraphMousePlugin
        implements MouseListener, MouseMotionListener {
    private Point2D spherePickedCoord = null;
    private Point2D downFirst = null;

    /**
     * create an instance with passed values
     */
    public SpherePickingPlugin() {
        super(InputEvent.BUTTON3_MASK | InputEvent.BUTTON1_MASK);
        this.cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
    }

    /**
     * The actionhistory
     */
    private ActionHistory history = ActionHistory.getInstance();

    @Override
    @SuppressWarnings("unchecked")
    public void mouseClicked(MouseEvent e) {
        System.out.println("point: "+e.getPoint());
        if (SwingUtilities.isLeftMouseButton(e)) {
            VisualizationViewer<Vertex, Edge> vv = (VisualizationViewer) e.getSource();
            SyndromPickSupport<Vertex, Edge> pickSupport = (SyndromPickSupport) vv.getPickSupport();
            SyndromGraph<Vertex, Edge> graph = (SyndromGraph<Vertex, Edge>) vv.getGraphLayout().getGraph();
            Point2D p = vv.getRenderContext().getMultiLayerTransformer().inverseTransform(e.getPoint());
            Sphere sp = pickSupport.getSphere(e.getX(), e.getY());
            Vertex vertex = (Vertex) pickSupport.getVertex(vv.getGraphLayout(), e.getX(), e.getY());
            List<Sphere> list = graph.getSpheres();
            Values values = Values.getInstance();
            Rectangle2D newRec = new Rectangle2D.Double(p.getX(), p.getY(), values.getDefaultWidthSphere(),
                    values.getDefaultHeightSphere());
            boolean addSphere = true;
            if (values.getGraphButtonType() == GraphButtonType.ADD_SPHERE && sp == null && vertex == null) {
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
                    AddSphereLogAction addSphereLogAction = new AddSphereLogAction(p);
                    history.execute(addSphereLogAction);
                }
            }
            /*
             * if (values.getGraphButtonType() == GraphButtonType.REMOVE_SPHERE && sp != null){
             graph.removeSphere(sp);
             }*/
            vv.repaint();
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public void mousePressed(MouseEvent e) {
        SyndromVisualisationViewer vv = (SyndromVisualisationViewer) e.getSource();
        SyndromPickSupport<Vertex, Edge> pickSupport = (SyndromPickSupport) vv.getPickSupport();
        Sphere sp = pickSupport.getSphere(e.getX(), e.getY());
        Vertex vert = (Vertex) pickSupport.getVertex(vv.getGraphLayout(), e.getX(), e.getY());
        down = e.getPoint();

        if (SwingUtilities.isLeftMouseButton(e)) {
            PickedState<Sphere> pickedSphereState = vv.getPickedSphereState();
            if (sp != null && vert == null) {
                spherePickedCoord = sp.getCoordinates();
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
    @SuppressWarnings("unchecked")
    public void mouseReleased(MouseEvent e) {
         if (SwingUtilities.isLeftMouseButton(e) && spherePickedCoord != null) {
             SyndromVisualisationViewer vv = (SyndromVisualisationViewer) e.getSource();
             PickedState<Sphere> spherePickedState = vv.getPickedSphereState();
             Set<Sphere> spheres = spherePickedState.getPicked();
             SyndromGraph graph = (SyndromGraph) vv.getGraphLayout().getGraph();
             List<Sphere> allSpheres = graph.getSpheres();
             SphereShapeTransformer sphereShapeTransformer = new SphereShapeTransformer();

             for (Sphere s : spheres) {
                 Shape sShape = sphereShapeTransformer.transform(s);
                 boolean move = true;
                 for (Sphere sphere: allSpheres){
                     if (!s.equals(sphere)){
                         Shape sphereShape = sphereShapeTransformer.transform(sphere);
                         if (sphereShape.intersects(sShape.getBounds())){
                             move = false;
                             System.out.println("move: "+move);
                         }
                     }
                 }
                 if (!move){
                     s.setCoordinates(spherePickedCoord);
                 }
             }
             spherePickedCoord = null;
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

            if (spherePickedCoord != null) {
                Point p = e.getPoint();
                Point2D graphPoint = vv.getRenderContext().getMultiLayerTransformer().transform(p);
                Point2D graphDown = vv.getRenderContext().getMultiLayerTransformer().transform(down);
                double dx = graphPoint.getX() - graphDown.getX();
                double dy = graphPoint.getY() - graphDown.getY();
                PickedState<Sphere> spherePickedState = vv.getPickedSphereState();

                for (Sphere s : spherePickedState.getPicked()) {
                    if (downFirst == null) {
                        downFirst = s.getCoordinates();
                    }

                    s.setCoordinates(new Point2D.Double(s.getCoordinates().getX() + dx, s.getCoordinates().getY() +
                            dy));
                    for (Vertex vertex : s.getVertices()) {
                        Point2D point = new Point2D.Double(vertex.getCoordinates().getX() + dx, vertex.getCoordinates()
                                .getY() + dy);
                        vertex.setCoordinates(point);
                        Layout layout = vv.getGraphLayout();
                        layout.setLocation(vertex, point);
                    }
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
