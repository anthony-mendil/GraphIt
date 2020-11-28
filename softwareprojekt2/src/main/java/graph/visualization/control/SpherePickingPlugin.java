package graph.visualization.control;

import actions.ActionHistory;
import actions.add.AddSphereLogAction;
import actions.move.MoveSphereLogAction;
import edu.uci.ics.jung.visualization.control.AbstractGraphMousePlugin;
import edu.uci.ics.jung.visualization.picking.PickedState;
import graph.graph.*;
import graph.visualization.SyndromVisualisationViewer;
import graph.visualization.picking.SyndromPickSupport;
import graph.visualization.transformer.sphere.SphereShapeTransformer;
import gui.GraphButtonType;
import gui.SphereContextMenu;
import gui.Values;
import gui.properties.LoadLanguage;
import javafx.scene.control.ContextMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The sphere picking plugin, implements mouse interactions on the spheres.
 *
 * @author Nina Unterberg
 */
public class SpherePickingPlugin extends AbstractGraphMousePlugin
        implements MouseListener, MouseMotionListener {
    /**
     * The values.
     */
    private final Values values;
    /**
     * HelperFunctions.
     */
    private final HelperFunctions helper;
    /**
     * The class where to get the current strings (according to the selected language).
     */
    private final LoadLanguage loadLanguage = LoadLanguage.getInstance();
    /**
     * A SphereShapeTransformer.
     */
    private final SphereShapeTransformer<Sphere> sphereShapeTransformer = new SphereShapeTransformer<>();
    /**
     * The coordinates of the picked sphere.
     */
    private Point2D spherePickedCoordinate = null;
    /**
     * The point where the user pressed the mouse.
     */
    private Point2D downFirst = null;
    /**
     * The old position of the vertices, which been moved.
     */
    private Map<Integer, Point2D> points = null;
    /**
     * The ContextMenu of the spheres.
     */
    private ContextMenu contextMenu;
    /**
     * The ActionHistory.
     */
    private ActionHistory history = ActionHistory.getInstance();
    /**
     *  Defines the add to the selection modifier (SHIFT)
     */
    private int addToSelectionModifiers;

    /**
     * Creates an instance with passed values.
     */
    public SpherePickingPlugin() {
        super(InputEvent.BUTTON3_DOWN_MASK | InputEvent.BUTTON1_DOWN_MASK);
        this.cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
        values = Values.getInstance();
        helper = new HelperFunctions();
        addToSelectionModifiers = InputEvent.SHIFT_DOWN_MASK;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void mouseClicked(MouseEvent e) {
        SyndromVisualisationViewer<Vertex, Edge> vv = (SyndromVisualisationViewer<Vertex, Edge>) e.getSource();
        SyndromPickSupport<Vertex, Edge> pickSupport = (SyndromPickSupport<Vertex, Edge>) vv.getPickSupport();
        SyndromGraph<Vertex, Edge> graph = (SyndromGraph<Vertex, Edge>) vv.getGraphLayout().getGraph();
        Point2D p = vv.getRenderContext().getMultiLayerTransformer().inverseTransform(e.getPoint());
        Sphere sp = pickSupport.getSphere(e.getX(), e.getY());
        Vertex vertex = pickSupport.getVertex(vv.getGraphLayout(), e.getX(), e.getY());

        if (SwingUtilities.isLeftMouseButton(e)) {
            if (values.getGraphButtonType() == GraphButtonType.ADD_SPHERE) {
                if (vertex == null && sp == null) {
                    List<Sphere> list = graph.getSpheres();
                    Rectangle2D newRec = new Rectangle2D.Double(p.getX(), p.getY(), Values.DEFAULT_WIDTH_SPHERE,
                            Values.DEFAULT_HEIGHT_SPHERE);
                    addSphere(list, newRec, p);
                } else {
                    helper.setActionText("SPHERE_PICKING_ADD_ALERT", true, true);
                }
        } else {

                if(e.getModifiersEx() == addToSelectionModifiers && vertex == null){
                    PickedState<Sphere> spheres =  vv.getPickedSphereState();
                    spheres.pick(sp, true);
                }
                else if (vertex == null && sp != null && values.getMode() != FunctionMode.ANALYSE) {
                    contextMenu = new SphereContextMenu(sp).getContextMenu();
                    helper.showSideMenu(e.getLocationOnScreen(), contextMenu);
                    PickedState<Sphere> spheres = vv.getPickedSphereState();
                    spheres.clear();
                    spheres.pick(sp, true);
                }
            }
            vv.repaint();
            Syndrom.getInstance().getVv2().repaint();
        }
    }

    /**
     * Contains the logic if a sphere can be added at a certain point.
     *
     * @param list   A list containing all the spheres.
     * @param newRec The shape of the potential new sphere.
     * @param p      The point where to add the sphere.
     */
    private void addSphere(List<Sphere> list, Rectangle2D newRec, Point2D p) {
        boolean addSphere = calculateOverlapSpheres(list, newRec);
        if (addSphere) {
            SyndromVisualisationViewer<Vertex, Edge> vv = Syndrom.getInstance().getVv();
            SyndromGraph<Vertex, Edge> graph = (SyndromGraph<Vertex, Edge>) vv.getGraphLayout().getGraph();
            if (graph.getSpheres().size() < Syndrom.getInstance().getTemplate().getMaxSpheres() || values.getMode() == FunctionMode.TEMPLATE) {
                AddSphereLogAction addSphereLogAction = new AddSphereLogAction(p);
                history.execute(addSphereLogAction);
            } else {
                Object[] obj = {Syndrom.getInstance().getTemplate().getMaxSpheres()};
                helper.setActionText(loadLanguage.loadLanguagesKey("SPHERE_PICKING_TEMPLATE_ALERT", obj), true, false);
            }
        } else {
            helper.setActionText("SPHERE_PICKING_ADD_ALERT", true, true);
        }
    }

    /**
     * Calculate if the potential new sphere overlaps with another one.
     *
     * @param list   The list containing all spheres.
     * @param newRec The shape of the potential new sphere.
     * @return If the potential new sphere overlaps another one.
     */
    private boolean calculateOverlapSpheres(List<Sphere> list, Rectangle2D newRec) {
        boolean addSphere = true;
        for (Sphere sphere : list) {
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
        return addSphere;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void mousePressed(MouseEvent e) {
        if (contextMenu != null) {
            helper.hideMenu(contextMenu);
        }
        SyndromVisualisationViewer<Vertex, Edge> vv = (SyndromVisualisationViewer) e.getSource();
        SyndromPickSupport<Vertex, Edge> pickSupport = (SyndromPickSupport<Vertex, Edge>) vv.getPickSupport();
        Sphere sp = pickSupport.getSphere(e.getX(), e.getY());
        Vertex vert = pickSupport.getVertex(vv.getGraphLayout(), e.getX(), e.getY());
        Edge edge = (Edge) pickSupport.getEdge(vv.getGraphLayout(), e.getX(), e.getY());

        down = e.getPoint();
        PickedState<Sphere> pickedSphereState = vv.getPickedSphereState();

        if (sp != null && vert == null && edge == null) {
            if (SwingUtilities.isRightMouseButton(e)) {
                if(pickedSphereState.getPicked().size() < 2) {
                    if (sp.isLockedPosition() && values.getMode() == FunctionMode.EDIT) {
                        helper.setActionText("SPHERE_PICKING_ALERT", true, true);
                    } else if (values.getMode() != FunctionMode.ANALYSE) {
                        spherePickedCoordinate = sp.getCoordinates();
                        setVerticesPositionToPoints(sp);
                    }
                }else{
                    helper.setActionText("SPHERE_MOVING_ALERT", true, true);
                }
            }
        }
        vv.repaint();
        Syndrom.getInstance().getVv2().repaint();
    }

    /**
     * Sets the 'old' vertices position to a list, for getting them back, if the new add- point is not valid.
     *
     * @param sp The sphere containing the vertices.
     */
    private void setVerticesPositionToPoints(Sphere sp) {
        points = new LinkedHashMap<>();
        for (Vertex v : sp.getVertices()) {
            points.put(v.getId(), v.getCoordinates());
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public void mouseReleased(MouseEvent e) {
        if (SwingUtilities.isRightMouseButton(e) && spherePickedCoordinate != null) {
            SyndromVisualisationViewer<Vertex, Edge> vv = (SyndromVisualisationViewer<Vertex, Edge>) e.getSource();
            PickedState<Sphere> spherePickedState = vv.getPickedSphereState();
            Set<Sphere> spheres = spherePickedState.getPicked();
            SyndromGraph<Vertex, Edge> graph = (SyndromGraph<Vertex, Edge>) vv.getGraphLayout().getGraph();
            List<Sphere> allSpheres = graph.getSpheres();
            setCoordinateSpheres(allSpheres, spheres, vv);
            spherePickedCoordinate = null;
            points = null;
            vv.repaint();
            Syndrom.getInstance().getVv2().repaint();
        }
    }

    /**
     * Sets the coordinates of an sphere.
     * @param allSpheres The list of all spheres.
     * @param spheres    The sphere to move.
     * @param vv         The visualization viewer to work on.
     */
    private void setCoordinateSpheres(List<Sphere> allSpheres, Set<Sphere> spheres, SyndromVisualisationViewer<Vertex, Edge> vv) {
        if(spheres.size() < 2) {
            for (Sphere s : spheres) {
                Shape sShape = sphereShapeTransformer.transform(s);
                boolean move = calculateMove(allSpheres, s, sShape);
                if (!move) {
                    s.setCoordinates(spherePickedCoordinate);
                    for (Vertex v : s.getVertices()) {
                        Point2D vp = points.get(v.getId());
                        v.setCoordinates(vp);
                        vv.getGraphLayout().setLocation(v, vp);
                    }
                } else {
                    if (spherePickedCoordinate != s.getCoordinates()) {
                        MoveSphereLogAction moveSphereLogAction = new MoveSphereLogAction(s, spherePickedCoordinate, s.getCoordinates());
                        history.execute(moveSphereLogAction);
                    }
                }
            }
        }
        else{
            helper.setActionText("SPHERE_MOVING_ALERT", true, true);
        }
    }

    /**
     * Calculates if the sphere which been moved, overlaps with another one.
     *
     * @param allSpheres List containing all the spheres.
     * @param s          The sphere.
     * @param sShape     The shape of the sphere.
     * @return If the sphere can be moved.
     */
    private boolean calculateMove(List<Sphere> allSpheres, Sphere s, Shape sShape) {
        boolean move = true;
        for (Sphere sphere : allSpheres) {
            if (!s.equals(sphere)) {
                Shape sphereShape = sphereShapeTransformer.transform(sphere);
                if (sphereShape.intersects(sShape.getBounds())) {
                    move = false;
                }
            }
        }
        return move;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        //
    }

    @Override
    @SuppressWarnings("unchecked")
    public void mouseDragged(MouseEvent e) {
        if (SwingUtilities.isRightMouseButton(e)) {
            SyndromVisualisationViewer<Vertex, Edge> vv = (SyndromVisualisationViewer<Vertex, Edge>) e.getSource();
            if (spherePickedCoordinate != null) {
                Point p = e.getPoint();
                Point2D graphPoint = vv.getRenderContext().getMultiLayerTransformer().inverseTransform(p);
                Point2D graphDown = vv.getRenderContext().getMultiLayerTransformer().inverseTransform(down);
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
                        vv.getGraphLayout().setLocation(vertex, point);
                    }
                }
                down = p;
                vv.repaint();
                Syndrom.getInstance().getVv2().repaint();
            }
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
}
