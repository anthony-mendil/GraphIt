package graph.visualization.control;

import actions.ActionHistory;
import actions.add.AddEdgesLogAction;
import actions.add.AddVerticesLogAction;
import actions.move.MoveVerticesLogAction;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.AbstractGraphMousePlugin;
import edu.uci.ics.jung.visualization.picking.PickedState;
import graph.graph.*;
import graph.visualization.SyndromVisualisationViewer;
import graph.visualization.picking.SyndromPickSupport;
import gui.GraphButtonType;
import gui.Values;
import gui.VertexContextMenu;
import gui.properties.LoadLanguage;
import javafx.scene.control.ContextMenu;
import javafx.util.Pair;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.util.*;
import java.util.List;

/**
 * the vertex picking plugin, implements mouse interactions on the vertices
 */
public class VertexPickingPlugin extends AbstractGraphMousePlugin
        implements MouseListener, MouseMotionListener {
    /**
     * the source vertex for adding edge
     */
    private Vertex source;

    /**
     * the values instance
     */
    private Values values;

    /**
     * a map, with vertices and its old position/ sphere
     */
    private Map<Vertex, Pair<Point2D, Sphere>> points = null;

    /**
     * an instance of the helper functions
     */
    private final HelperFunctions helper;

    /**
     * a context menu (vertex context menu)
     */
    private ContextMenu contextMenu;

    /**
     * defines the add to selection modifier (SHIFT)
     */
    private int addToSelectionModifiers;

    /**
     * an instance of the action history
     */
    private ActionHistory history;

    /**
     * the class where to get the current strings (according to the selected language)
     */
    private LoadLanguage loadLanguage = LoadLanguage.getInstance();

    /**
     * create an instance with passed values
     */
    public VertexPickingPlugin() {
        this(InputEvent.BUTTON3_DOWN_MASK | InputEvent.BUTTON1_DOWN_MASK, InputEvent.SHIFT_DOWN_MASK | InputEvent.BUTTON1_DOWN_MASK);
        this.cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
        values = Values.getInstance();
        history = ActionHistory.getInstance();
    }

    /**
     * create an instance with passed values
     */
    private VertexPickingPlugin(int selectionModifiers, int addToSelectionModifiers) {
        super(selectionModifiers);
        this.addToSelectionModifiers = addToSelectionModifiers;
        helper = new HelperFunctions();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //SyndromVisualisationViewer sv = (SyndromVisualisationViewer) e.getSource();
        @SuppressWarnings("unchecked") SyndromVisualisationViewer<Vertex, Edge> vv = (SyndromVisualisationViewer<Vertex, Edge>) e.getSource();
        SyndromPickSupport<Vertex, Edge> pickSupport = (SyndromPickSupport<Vertex, Edge>) vv.getPickSupport();
        Point2D point = e.getPoint();
        Sphere sp = pickSupport.getSphere(point.getX(), point.getY());
        Vertex vertex = pickSupport.getVertex(vv.getGraphLayout(), point.getX(), point.getY());
        Edge edge = (Edge) pickSupport.getEdge(vv.getGraphLayout(), point.getX(), point.getY());

        if (SwingUtilities.isLeftMouseButton(e) && values.getGraphButtonType() == GraphButtonType.ADD_VERTEX) {
            if (sp != null && vertex == null && edge == null) {
                leftMouseClicked(sp, pickSupport, e, vv);
            } else {
                helper.setActionText("VERTEX_PICKING_ALERT_ADD", true, true);
            }
        }

        if (SwingUtilities.isRightMouseButton(e)) {
            rightMouseClicked(vertex, vv, e);
        }
        vv.repaint();
        Syndrom.getInstance().getVv2().repaint();
    }

    /**
     * @param sp the sphere, containing the vertex where the click was registered
     * @param pickSupport the PickSupport of syndrom
     * @param e the MouseEvent e
     * @param vv the VisualisationViewer
     */
    private void leftMouseClicked(Sphere sp, SyndromPickSupport<Vertex, Edge> pickSupport, MouseEvent e, SyndromVisualisationViewer<Vertex, Edge> vv) {
        Syndrom syndrom = Syndrom.getInstance();
        if (values.getMode() != FunctionMode.TEMPLATE && syndrom.getTemplate().getMaxVertices() != 0 &&
                syndrom.getVv().getGraphLayout().getGraph().getVertices().size() >= syndrom.getTemplate().getMaxVertices()) {
            Object[] obj = {Syndrom.getInstance().getTemplate().getMaxVertices()};
            helper.setActionText(loadLanguage.loadLanguagesKey("VERTEX_PICKING_MAX_COUNT_ALERT", obj), true, false);
            return;
        }
        if (sp.isLockedVertices() && values.getMode() != FunctionMode.TEMPLATE) {
            helper.setActionText("VERTEX_PICKING_TEMPLATE_COUNT_ALERT", true, true);
        }
        if (sp.getLockedMaxAmountVertices().equals("") || sp.getVertices().size() < Integer.parseInt(sp.getLockedMaxAmountVertices()) || values.getMode() == FunctionMode.TEMPLATE) {
            AddVerticesLogAction addVerticesLogAction = new AddVerticesLogAction(e.getPoint(), sp);
            history.execute(addVerticesLogAction);
            Vertex newVertex = pickSupport.getVertex(vv.getGraphLayout(), e.getX(), e.getY());
            PickedState<Vertex> pickedState = vv.getPickedVertexState();
            pickedState.clear();
            pickedState.pick(newVertex, true);
        } else {
            helper.setActionText("VERTEX_PICKING_COUNT_ALERT", true, true);
        }
    }

    /**
     * @param vertex the vertex where the click was registered
     * @param vv the VisualisationViewer
     * @param e the MouseEvent
     */
    private void rightMouseClicked(Vertex vertex, SyndromVisualisationViewer<Vertex, Edge> vv, MouseEvent e) {
        if (vertex != null && Values.getInstance().getMode() != FunctionMode.ANALYSE) {
            PickedState<Vertex> vertices = vv.getPickedVertexState();
            vertices.clear();
            vertices.pick(vertex, true);
            contextMenu = new VertexContextMenu(vertex).getContextMenu();
            helper.showSideMenu(e.getLocationOnScreen(), contextMenu);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (contextMenu != null) {
            helper.hideMenu(contextMenu);
        }
        down = e.getPoint();
        @SuppressWarnings("unchecked") SyndromVisualisationViewer<Vertex, Edge> vv = (SyndromVisualisationViewer<Vertex, Edge>) e.getSource();
        SyndromPickSupport<Vertex, Edge> pickSupport = (SyndromPickSupport<Vertex, Edge>) vv.getPickSupport();
        Layout<Vertex, Edge> layout = vv.getGraphLayout();
        Vertex vert = pickSupport.getVertex(layout, e.getX(), e.getY());
        PickedState<Vertex> vertexPickedState = vv.getPickedVertexState();

        if (e.getModifiersEx() == addToSelectionModifiers && vert != null) {
            vertexPickedState.pick(vert, true);
            return;
        }
        if (vert != null && !vertexPickedState.isPicked(vert)) {
            vertexPickedState.clear();
            vertexPickedState.pick(vert, true);
        }
        if (SwingUtilities.isRightMouseButton(e) && vert != null) {
            if (values.getMode() != FunctionMode.ANALYSE) {
                Object[] pickedArray = vertexPickedState.getPicked().toArray();
                points = new LinkedHashMap<>();
                for (Object aPickedArray : pickedArray) {
                    Vertex v = (Vertex) aPickedArray;
                    Point2D point2D = vv.getRenderContext().getMultiLayerTransformer().transform(new Point2D.Double(v
                            .getCoordinates().getX(), v.getCoordinates().getY()));
                    Sphere sp = pickSupport.getSphere(point2D.getX(), point2D.getY());
                    points.put(v, new Pair<>(v.getCoordinates(), sp));
                }
                vv.repaint();
                Syndrom.getInstance().getVv2().repaint();
            }
        } else if (e.getModifiersEx() == InputEvent.BUTTON1_DOWN_MASK && vert != null) {
            source = vert;
        }
    }

    /**
     * @param vert the second vertex for the new edge
     */
    private void edgeAdd(Vertex vert) {
        if (values.getMode() != FunctionMode.TEMPLATE) {
            switch (values.getEdgeArrowType()) {
                case REINFORCED:
                    if (!Syndrom.getInstance().getTemplate().isReinforcedEdgesAllowed()) {
                        helper.setActionText("EDGES_TYPE_REINFORCED_ALERT", true, true);
                        return;
                    }
                    break;
                case EXTENUATING:
                    if (!Syndrom.getInstance().getTemplate().isExtenuatingEdgesAllowed()) {
                        helper.setActionText("EDGES_TYPE_EXTENUATING_ALERT", true, true);
                        return;
                    }
                    break;
                case NEUTRAL:
                    if (!Syndrom.getInstance().getTemplate().isNeutralEdgesAllowed()) {
                        helper.setActionText("EDGES_TYPE_NEURAL_ALERT", true, true);
                        return;
                    }
                    break;
            }
        }
        Pair<Vertex, Vertex> edge = new Pair<>(source, vert);
        AddEdgesLogAction addEdgesLogAction = new AddEdgesLogAction(edge);
        history.execute(addEdgesLogAction);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        @SuppressWarnings("unchecked") SyndromVisualisationViewer<Vertex, Edge> vv = (SyndromVisualisationViewer) e.getSource();
        SyndromPickSupport<Vertex, Edge> pickSupport = (SyndromPickSupport<Vertex, Edge>) vv.getPickSupport();
        Layout<Vertex, Edge> layout = vv.getGraphLayout();
        Vertex vert = pickSupport.getVertex(layout, e.getX(), e.getY());
        PickedState<Vertex> pickedState = vv.getPickedVertexState();
        Syndrom syndrom = Syndrom.getInstance();
        if (SwingUtilities.isRightMouseButton(e) && points != null) {
            setVerticesCoordinate(pickedState, vv, layout, pickSupport);
            points = null;
            down = null;
            vv.repaint();
            syndrom.getVv2().repaint();
        }

        if (SwingUtilities.isLeftMouseButton(e) && vert != null && source != null && !source.equals(vert)) {
            if (values.getMode() == FunctionMode.TEMPLATE ||
                    syndrom.getTemplate().getMaxEdges() > Syndrom.getInstance().getVv().getGraphLayout().getGraph().getEdges().size()) {
                edgeAdd(vert);
            } else {
                Object[] obj = {syndrom.getTemplate().getMaxEdges()};
                helper.setActionText(loadLanguage.loadLanguagesKey("VERTEX_PICKING_COUNT2_ALERT", obj), true, false);
            }
        }
        source = null;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (SwingUtilities.isRightMouseButton(e)) {
            @SuppressWarnings("unchecked") SyndromVisualisationViewer<Vertex, Edge> vv = (SyndromVisualisationViewer<Vertex, Edge>) e.getSource();
            if (points != null) {
                Point p = e.getPoint();
                Point2D graphPoint = vv.getRenderContext().getMultiLayerTransformer().inverseTransform(p);
                Point2D graphDown = vv.getRenderContext().getMultiLayerTransformer().inverseTransform(down);
                double dx = graphPoint.getX() - graphDown.getX();
                double dy = graphPoint.getY() - graphDown.getY();
                PickedState<Vertex> pickedState = vv.getPickedVertexState();

                Layout<Vertex, Edge> layout = vv.getGraphLayout();
                for (Vertex vertex : pickedState.getPicked()) {
                    for (Edge edge : vv.getGraphLayout().getGraph().getIncidentEdges(vertex)) {
                        edge.setHasPriority(true);
                    }
                    if (!vertex.isLockedPosition() || values.getMode() == FunctionMode.TEMPLATE) {
                        Point2D vp = layout.transform(vertex);
                        vp.setLocation(vertex.getCoordinates().getX() + dx, vertex.getCoordinates().getY() + dy);
                        layout.setLocation(vertex, vp);
                        vertex.setCoordinates(vp);
                    }
                }

                down = p;
                vv.repaint();
                Syndrom.getInstance().getVv2().repaint();
            }
        }
    }

    /**
     * @param pickedState the current PickedState of the vertices
     * @param pickSupport the PickSupport of syndrom
     * @param vv the VisualisationViewer
     * @return if the vertex should be added or not
     */
    private boolean calculateAddNot(PickedState<Vertex> pickedState, SyndromPickSupport<Vertex, Edge> pickSupport, VisualizationViewer<Vertex, Edge> vv) {
        boolean addNot = false;
        for (Vertex v : pickedState.getPicked()) {
            for (Edge edge : vv.getGraphLayout().getGraph().getIncidentEdges(v)) {
                edge.setHasPriority(false);
            }
            Point2D vp = vv.getRenderContext().getMultiLayerTransformer().transform(v.getCoordinates());
            Sphere sp = pickSupport.getSphere(vp.getX(), vp.getY());
            if (sp == null) {
                addNot = true;
            }
        }
        return addNot;
    }

    /**
     * @param pickedState the current PickedState of the vertices
     * @param vv the VisualisationViewer
     * @param layout the current Layout of the graph
     * @param pickSupport the PickSupport of syndrom
     */
    private void setVerticesCoordinate(PickedState<Vertex> pickedState, SyndromVisualisationViewer<Vertex, Edge> vv, Layout<Vertex, Edge> layout, SyndromPickSupport<Vertex, Edge> pickSupport) {
        boolean addNot = calculateAddNot(pickedState, pickSupport, vv);

        if (addNot) {
            for (Vertex v : pickedState.getPicked()) {
                Point2D vp = new Point2D.Double(points.get(v).getKey().getX(), points.get(v)
                        .getKey().getY());
                v.setCoordinates(vp);
                layout.setLocation(v, vp);
            }
        } else {
            moveVertices(pickedState, vv, pickSupport, layout);
        }
    }

    /**
     * @param pickedState the current PickedState of the vertices
     * @param vv the VisualisationViewer
     * @param layout the current Layout of the graph
     * @param pickSupport the PickSupport of syndrom
     */
    private void moveVertices(PickedState<Vertex> pickedState, SyndromVisualisationViewer<Vertex, Edge> vv, SyndromPickSupport<Vertex, Edge> pickSupport, Layout<Vertex, Edge> layout) {
        boolean jumpBack = false;
        for (Vertex v : pickedState.getPicked()) {
            if (intersects(v)) {
                jumpBack = true;
                break;
            } else {
                Point2D point2D = vv.getRenderContext().getMultiLayerTransformer().transform(v
                        .getCoordinates());
                Sphere s = pickSupport.getSphere(point2D.getX(), point2D.getY());
                Sphere oldSphere = points.get(v).getValue();
                if (!s.equals(oldSphere)) {
                    LinkedList<Vertex> list = oldSphere.getVertices();
                    list.remove(v);
                    oldSphere.setVertices(list);
                    LinkedList<Vertex> newList = s.getVertices();
                    newList.add(v);
                    s.setVertices(newList);
                }
            }
        }
        if (jumpBack) {
            for (Vertex vert : pickedState.getPicked()) {
                vert.setCoordinates(points.get(vert).getKey());
                layout.setLocation(vert, vert.getCoordinates());
            }
            vv.repaint();
            Syndrom.getInstance().getVv2().repaint();
            return;
        }
        List<Vertex> vertList = new ArrayList<>(pickedState.getPicked());
        Vertex pivotVertex = vertList.get(0);
        if (pivotVertex.getCoordinates() != points.get(pivotVertex).getKey()) {
            MoveVerticesLogAction moveVerticesLogAction = new MoveVerticesLogAction(pickedState.getPicked(), points);
            history.execute(moveVerticesLogAction);
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

    /**
     * @param v the vertex to check
     * @return if the vertex intersects with another one
     */
    private boolean intersects(Vertex v) {
        SyndromVisualisationViewer<Vertex, Edge> vv = Syndrom.getInstance().getVv();
        SyndromGraph<Vertex, Edge> graph = (SyndromGraph<Vertex, Edge>) vv.getGraphLayout().getGraph();
        for (Vertex vertex : graph.getVertices()) {
            if (vertex != v && Math.abs(vertex.getCoordinates().getX() - v.getCoordinates().getX()) < 40
                    && Math.abs(vertex.getCoordinates().getY() - v.getCoordinates().getY()) < 30) {
                return true;
            }

        }
        return false;
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //
    }
}
