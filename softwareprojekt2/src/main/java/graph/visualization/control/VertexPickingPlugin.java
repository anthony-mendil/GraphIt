package graph.visualization.control;

import actions.ActionHistory;
import actions.add.AddEdgesLogAction;
import actions.add.AddVerticesLogAction;
import actions.move.MoveVerticesLogAction;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.visualization.control.AbstractGraphMousePlugin;
import edu.uci.ics.jung.visualization.picking.PickedState;
import graph.graph.*;
import graph.visualization.SyndromVisualisationViewer;
import graph.visualization.picking.SyndromPickSupport;
import gui.GraphButtonType;
import gui.Values;
import gui.VertexContextMenu;
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

public class VertexPickingPlugin extends AbstractGraphMousePlugin
        implements MouseListener, MouseMotionListener {
    private Vertex source;

    private Values values;
    private Map<Vertex, Pair<Point2D, Sphere>> points = null;
    private final HelperFunctions helper;
    private ContextMenu contextMenu;
    private int addToSelectionModifiers;
    private ActionHistory history;

    /**
     * create an instance with passed values
     */
    public VertexPickingPlugin() {
        this(InputEvent.BUTTON3_DOWN_MASK | InputEvent.BUTTON1_DOWN_MASK, InputEvent.SHIFT_DOWN_MASK | InputEvent.BUTTON1_DOWN_MASK);
        this.cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
        values = Values.getInstance();
        history = ActionHistory.getInstance();
    }

    private VertexPickingPlugin(int selectionModifiers, int addToSelectionModifiers) {
        super(selectionModifiers);
        this.addToSelectionModifiers = addToSelectionModifiers;
        helper = new HelperFunctions();
    }

    @Override
    @SuppressWarnings("unchecked")
    public void mouseClicked(MouseEvent e) {
        SyndromVisualisationViewer<Vertex, Edge> vv = (SyndromVisualisationViewer<Vertex, Edge>) e.getSource();
        SyndromPickSupport<Vertex, Edge> pickSupport = (SyndromPickSupport<Vertex, Edge>) vv.getPickSupport();
        Point2D point = e.getPoint();
        Sphere sp = pickSupport.getSphere(point.getX(), point.getY());
        Vertex vertex = (Vertex) pickSupport.getVertex(vv.getGraphLayout(), point.getX(), point.getY());
        Edge edge = (Edge) pickSupport.getEdge(vv.getGraphLayout(), point.getX(), point.getY());

        if (SwingUtilities.isLeftMouseButton(e)) {
            if (values.getGraphButtonType() == GraphButtonType.ADD_VERTEX) {
                if (sp != null && vertex == null && edge == null) {
                    if(values.getMode() != FunctionMode.TEMPLATE && Syndrom.getInstance().getTemplate().getMaxVertices() != 0 &&
                            Syndrom.getInstance().getVv().getGraphLayout().getGraph().getVertices().size() >= Syndrom.getInstance().getTemplate().getMaxVertices()){
                        helper.setActionText("Es dürfen aufgrund der Vorlageregeln nur maximal " + Syndrom.getInstance().getTemplate().getMaxVertices() + " Symptome gesetzt weren.", true);
                        return;
                    }
                    if(sp.isLockedVertices() && values.getMode() != FunctionMode.TEMPLATE){
                        helper.setActionText("Es dürfen aufgrund der Vorlageregeln keine Symptome hinzugefügt werden.", true);
                    }
                    if(sp.getLockedMaxAmountVertices() == "" || sp.getVertices().size() < Integer.parseInt(sp.getLockedMaxAmountVertices()) || values.getMode() == FunctionMode.TEMPLATE)  {
                        AddVerticesLogAction addVerticesLogAction = new AddVerticesLogAction(e.getPoint(), sp);
                        history.execute(addVerticesLogAction);
                        Vertex newVertex = (Vertex) pickSupport.getVertex(vv.getGraphLayout(), e.getX(), e.getY());
                        PickedState<Vertex> pickedState = vv.getPickedVertexState();
                        pickedState.clear();
                        pickedState.pick(newVertex, true);
                    }else{
                        helper.setActionText("Es können aufgrund der Vorlageregeln keine weitere Symptome in die Sphäre gesetzt werden", true);
                    }
                } else {
                    helper.setActionText("Hinzufügen eines Knoten hier nicht möglich!", true);
                }
            }
            vv.repaint();
            Syndrom.getInstance().getVv2().repaint();
        }

        if (SwingUtilities.isRightMouseButton(e)) {
            if (vertex != null) {
                if (Values.getInstance().getMode() != FunctionMode.ANALYSE) {
                    PickedState<Vertex> vertices = vv.getPickedVertexState();
                    vertices.clear();
                    vertices.pick(vertex, true);
                    contextMenu = new VertexContextMenu(vertex).getContextMenu();
                    helper.showSideMenu(e.getLocationOnScreen(), contextMenu);
                }
            }
            vv.repaint();
            Syndrom.getInstance().getVv2().repaint();
        }
    }


    @Override
    @SuppressWarnings("unchecked")
    public void mousePressed(MouseEvent e) {
        if (contextMenu != null){
            helper.hideMenu(contextMenu);
        }

        down = e.getPoint();
        SyndromVisualisationViewer<Vertex, Edge> vv = (SyndromVisualisationViewer) e.getSource();
        SyndromPickSupport<Vertex, Edge> pickSupport = (SyndromPickSupport<Vertex, Edge>) vv.getPickSupport();
        Layout<Vertex, Edge> layout = vv.getGraphLayout();
        Vertex vert = (Vertex) pickSupport.getVertex(layout, e.getX(), e.getY());
        PickedState<Vertex> vertexPickedState = vv.getPickedVertexState();

        if (e.getModifiersEx() == addToSelectionModifiers && vert != null) {
            vertexPickedState.pick(vert, true);
        } else {
            if (vert != null && !vertexPickedState.isPicked(vert)) {
                vertexPickedState.clear();
                vertexPickedState.pick(vert, true);
            }
            if (SwingUtilities.isRightMouseButton(e) && vert != null) {
                if (Values.getInstance().getMode() != FunctionMode.ANALYSE) {
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
            } else if (e.getModifiers() == InputEvent.BUTTON1_MASK && vert != null) {
                source = vert;
            }
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public void mouseReleased(MouseEvent e) {
        SyndromVisualisationViewer vv = (SyndromVisualisationViewer) e.getSource();
        SyndromPickSupport<Vertex, Edge> pickSupport = (SyndromPickSupport<Vertex, Edge>) vv.getPickSupport();
        Layout<Vertex, Edge> layout = vv.getGraphLayout();
        Vertex vert = (Vertex) pickSupport.getVertex(layout, e.getX(), e.getY());
        PickedState<Vertex> pickedState = vv.getPickedVertexState();
        if (SwingUtilities.isRightMouseButton(e) && points != null) {
            setVerticesCoord(pickedState, vv, layout, pickSupport);
            points = null;
            down = null;
            vv.repaint();
            Syndrom.getInstance().getVv2().repaint();
        }

        if (SwingUtilities.isLeftMouseButton(e) && vert != null && source != null && !source.equals(vert)) {
            if(values.getMode() == FunctionMode.TEMPLATE ||
                    Syndrom.getInstance().getTemplate().getMaxEdges() > Syndrom.getInstance().getVv().getGraphLayout().getGraph().getEdges().size()) {
                    System.out.println(values.getEdgeArrowType().name());
                    System.out.println(Syndrom.getInstance().getTemplate().isReinforcedEdgesAllowed());
                if(values.getMode() != FunctionMode.TEMPLATE) {
                    switch (values.getEdgeArrowType()) {
                        case REINFORCED:
                            if (!Syndrom.getInstance().getTemplate().isReinforcedEdgesAllowed()) {
                                helper.setActionText("Verstärkende Relationen sind aufgrund der Vorlageregeln nicht erlaubt", true);
                                return;
                            }
                            break;
                        case EXTENUATING:
                            if (!Syndrom.getInstance().getTemplate().isExtenuatingEdgesAllowed()) {
                                helper.setActionText("Abschwächende Relationen sind aufgrund der Vorlageregelen nicht erlaubt", true);
                                return;
                            }
                            break;
                        case NEUTRAL:
                            if (!Syndrom.getInstance().getTemplate().isNeutralEdgesAllowed()) {
                                helper.setActionText("Unbekannte Relationen sind aufgrund der Vorlageregelen nicht erlaubt.", true);
                                return;
                            }
                            break;
                    }
                }
                Pair<Vertex, Vertex> edge = new Pair<>(source, vert);
                AddEdgesLogAction addEdgesLogAction = new AddEdgesLogAction(edge);
                history.execute(addEdgesLogAction);
            }else{
                helper.setActionText("Es dürfen nur " + Syndrom.getInstance().getTemplate().getMaxEdges() + " Relation(en) aufgrund der Vorlageregeln gesetzt werden.", true);
            }
        }
        source = null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void mouseDragged(MouseEvent e) {
        if (SwingUtilities.isRightMouseButton(e)) {
            SyndromVisualisationViewer<Vertex, Edge> vv = (SyndromVisualisationViewer<Vertex, Edge>) e.getSource();

            if (points != null) {



                Point p = e.getPoint();
                Point2D graphPoint = vv.getRenderContext().getMultiLayerTransformer().inverseTransform(p);
                Point2D graphDown = vv.getRenderContext().getMultiLayerTransformer().inverseTransform(down);
                double dx = graphPoint.getX() - graphDown.getX();
                double dy = graphPoint.getY() - graphDown.getY();
                PickedState<Vertex> pickedState = vv.getPickedVertexState();

                Layout<Vertex, Edge> layout = vv.getGraphLayout();
                for (Vertex vertex : pickedState.getPicked()) {
                    for (Edge edge: vv.getGraphLayout().getGraph().getIncidentEdges(vertex)){
                        edge.setHasPrio(true);
                    }
                    if(!vertex.isLockedPosition() || values.getMode() == FunctionMode.TEMPLATE) {
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

    private void setVerticesCoord(PickedState<Vertex> pickedState, SyndromVisualisationViewer<Vertex, Edge> vv, Layout<Vertex, Edge> layout, SyndromPickSupport pickSupport) {
        boolean addNot = false;
        for (Vertex v : pickedState.getPicked()) {
            for (Edge edge: vv.getGraphLayout().getGraph().getIncidentEdges(v)){
                edge.setHasPrio(false);
            }
            Point2D vp = vv.getRenderContext().getMultiLayerTransformer().transform(v.getCoordinates());
            Sphere sp = pickSupport.getSphere(vp.getX(), vp.getY());
            if (sp == null) {
                addNot = true;
            }
        }
        if (addNot) {
            for (Vertex v : pickedState.getPicked()) {
                Point2D vp = new Point2D.Double(points.get(v).getKey().getX(), points.get(v)
                        .getKey().getY());
                v.setCoordinates(vp);
                layout.setLocation(v, vp);
            }
        } else {
            for (Vertex v : pickedState.getPicked()) {
                if(intersects(v)){
                    return;
                }
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
            List<Vertex> vertList = new ArrayList<>();
            vertList.addAll(pickedState.getPicked());
            Vertex pivotVertex = vertList.get(0);
            if(pivotVertex.getCoordinates() != points.get(pivotVertex).getKey()) {
                MoveVerticesLogAction moveVerticesLogAction = new MoveVerticesLogAction(pickedState.getPicked(), points);
                history.execute(moveVerticesLogAction);
            }
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

    public boolean intersects(Vertex v){
        SyndromVisualisationViewer<Vertex, Edge> vv = Syndrom.getInstance().getVv();
        SyndromGraph<Vertex, Edge> graph = (SyndromGraph<Vertex, Edge>) vv.getGraphLayout().getGraph();
        for(Vertex vertex : graph.getVertices()){
            if(vertex != v){
                if(Math.abs(vertex.getCoordinates().getX() - v.getCoordinates().getX()) < 40
                        && Math.abs(vertex.getCoordinates().getY() - v.getCoordinates().getY()) < 30 ){
                    return true;
                }
            }
        }
        return false;
    }
}
