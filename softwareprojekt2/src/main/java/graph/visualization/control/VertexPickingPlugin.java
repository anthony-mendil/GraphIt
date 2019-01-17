package graph.visualization.control;

import actions.ActionHistory;
import actions.add.AddEdgesLogAction;
import actions.add.AddVerticesLogAction;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.visualization.Layer;
import edu.uci.ics.jung.visualization.RenderContext;
import edu.uci.ics.jung.visualization.control.AbstractGraphMousePlugin;
import edu.uci.ics.jung.visualization.picking.PickedState;
import graph.graph.*;
import graph.visualization.SyndromVisualisationViewer;
import graph.visualization.picking.SyndromPickSupport;
import gui.GraphButtonType;
import gui.Values;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Pair;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.*;
import java.util.concurrent.CountDownLatch;

public class VertexPickingPlugin extends AbstractGraphMousePlugin
        implements MouseListener, MouseMotionListener {
    private Vertex source;

    private Values values;
    private Map<Integer, Pair<Point2D, Sphere>> points;



    private ActionHistory history;

    /**
     * create an instance with passed values
     */
    public VertexPickingPlugin() {
        super(InputEvent.BUTTON3_MASK | InputEvent.BUTTON1_MASK);
        this.cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
        values = Values.getInstance();
        history = ActionHistory.getInstance();
    }

    @Override
    @SuppressWarnings("unchecked")
    public void mouseClicked(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e)) {
            SyndromVisualisationViewer<Vertex, Edge> vv = (SyndromVisualisationViewer<Vertex, Edge>) e.getSource();
            SyndromPickSupport<Vertex, Edge> pickSupport = (SyndromPickSupport<Vertex, Edge>) vv.getPickSupport();
            Point2D point = e.getPoint();
            Sphere sp = pickSupport.getSphere(point.getX(), point.getY());
            Vertex vertex = (Vertex) pickSupport.getVertex(vv.getGraphLayout(), point.getX(), point.getY());

            if (values.getGraphButtonType() == GraphButtonType.ADD_VERTEX) {
                if (sp != null && vertex == null) {

                    boolean add = true;
                    for (Vertex sphereVert : sp.getVertices()) {
                        GraphObjectsFactory graphObjectsFactory = new GraphObjectsFactory();
                        Vertex test = graphObjectsFactory.createTestVertex(point);
                        RenderContext<Vertex, Edge> rc = vv.getRenderContext();
                        Layout<Vertex, Edge> layout = vv.getGraphLayout();

                        Shape shapeTest = vv.getRenderContext().getVertexShapeTransformer().transform(test);
                        Shape shapeSphereVert = rc.getVertexShapeTransformer().transform
                                (sphereVert);
                        float x = (float) point.getX();
                        float y = (float) point.getY();
                        AffineTransform xform = AffineTransform.getTranslateInstance(x, y);
                        shapeTest = xform.createTransformedShape(shapeTest);

                        Point2D p2 = layout.transform(sphereVert);
                        p2 = rc.getMultiLayerTransformer().transform(Layer.LAYOUT, p2);
                        float x2 = (float) p2.getX();
                        float y2 = (float) p2.getY();
                        AffineTransform xform2 = AffineTransform.getTranslateInstance(x2, y2);
                        shapeSphereVert = xform2.createTransformedShape(shapeSphereVert);

                        if (shapeTest.intersects(shapeSphereVert.getBounds())) {
                            add = false;
                        }
                    }
                    if (add) {
                        AddVerticesLogAction addVerticesLogAction = new AddVerticesLogAction(e.getPoint(), sp);
                    	history.execute(addVerticesLogAction);
                    } else {
                        setActionText("Hinzufügen eines Knoten hier nicht möglich!", true);
                    }
                } else {
                    setActionText("Hinzufügen eines Knoten hier nicht möglich!", true);
                }
            }
            vv.repaint();
        }
    }


    @Override
    @SuppressWarnings("unchecked")
    public void mousePressed(MouseEvent e) {
        setActionText("<<Action>>", false);
        down = e.getPoint();
        SyndromVisualisationViewer<Vertex, Edge> vv = (SyndromVisualisationViewer) e.getSource();
        SyndromPickSupport<Vertex, Edge> pickSupport = (SyndromPickSupport<Vertex, Edge>) vv.getPickSupport();
        Layout<Vertex, Edge> layout = vv.getGraphLayout();
        Vertex vert = (Vertex) pickSupport.getVertex(layout, e.getX(), e.getY());

        if (SwingUtilities.isLeftMouseButton(e)) {
            PickedState<Vertex> vertexPickedState = vv.getPickedVertexState();
            if (vert != null) {
                if (!vertexPickedState.isPicked(vert)) {
                    vertexPickedState.pick(vert, true);
                }
            } else {
                vertexPickedState.clear();
            }

            Object[] pickedArray = vertexPickedState.getPicked().toArray();
            points = new LinkedHashMap<>();
            for (Object aPickedArray : pickedArray) {
                Vertex v = (Vertex) aPickedArray;
                Point2D point2D = vv.getRenderContext().getMultiLayerTransformer().transform(new Point2D.Double(v
                        .getCoordinates().getX(), v.getCoordinates().getY()));
                Sphere sp = pickSupport.getSphere(point2D.getX(), point2D.getY());
                points.put(v.getId(), new Pair<>(v.getCoordinates(), sp));
            }
            vv.repaint();
        } else if (SwingUtilities.isRightMouseButton(e)) {
            if (vert != null) {
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
        if (SwingUtilities.isLeftMouseButton(e)) {
            boolean addNot = false;
            for (Vertex v : pickedState.getPicked()) {
                Point2D vp = vv.getRenderContext().getMultiLayerTransformer().transform(v.getCoordinates());
                Sphere sp = pickSupport.getSphere(vp.getX(), vp.getY());
                if (sp == null) {
                    addNot = true;
                }
            }
            System.out.println(addNot);

            if (addNot) {
                for (Vertex v : pickedState.getPicked()) {
                    Point2D vp = new Point2D.Double(points.get(v.getId()).getKey().getX(), points.get(v.getId())
                            .getKey().getY());
                    v.setCoordinates(vp);
                    layout.setLocation(v, vp);
                }
            } else {
                for (Vertex v : pickedState.getPicked()) {
                    System.out.println("picked");
                    Point2D point2D = vv.getRenderContext().getMultiLayerTransformer().transform(v
                            .getCoordinates());
                    Sphere s = pickSupport.getSphere(point2D.getX(), point2D.getY());
                    Sphere oldSphere = points.get(v.getId()).getValue();
                    if (!s.equals(oldSphere)){
                        LinkedList<Vertex> list = oldSphere.getVertices();
                        list.remove(v);
                        oldSphere.setVertices(list);
                        System.out.println("oldSphere: "+oldSphere.getVertices());

                        LinkedList<Vertex> newList = s.getVertices();
                        newList.add(v);
                        s.setVertices(newList);
                        System.out.println("sphere: "+s.getVertices());

                    }
                }
            }
            points = null;
            down = null;
            vv.repaint();
        }

        if (SwingUtilities.isRightMouseButton(e)) {
            if (vert != null && source != null && !source.equals(vert)) {
                Pair<Vertex, Vertex> edge = new Pair<>(source, vert);
                AddEdgesLogAction addEdgesLogAction = new AddEdgesLogAction(edge);
                history.execute(addEdgesLogAction);
            }
        }
        source = null;
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
        if (SwingUtilities.isLeftMouseButton(e)) {
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
                    Point2D vp = layout.transform(vertex);
                    vp.setLocation(vertex.getCoordinates().getX() + dx, vertex.getCoordinates().getY() + dy);
                    layout.setLocation(vertex, vp);
                    vertex.setCoordinates(vp);
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

    private void setActionText(String string, boolean isAlert) {
        Service<Void> service = new Service<Void>() {
            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        final CountDownLatch latch = new CountDownLatch(1);
                        Platform.runLater(() -> {
                            try {
                                Text text = (Text) values.getNamespace().get("currentActionText");
                                Color color;
                                Font font;
                                if (isAlert) {
                                    color = values.getActionTextColorAlert();
                                    font = values.getActionTextAlert();
                                } else {
                                    color = values.getActionTextColorInfo();
                                    font = values.getActionTextInfo();
                                }
                                text.setFill(color);
                                text.setText(string);
                                text.setFont(font);
                            } finally {
                                latch.countDown();
                            }
                        });
                        latch.await();
                        return null;
                    }
                };
            }
        };
        service.start();
    }
}
