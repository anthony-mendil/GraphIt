package graph.visualization.control;

import edu.uci.ics.jung.visualization.control.AbstractGraphMousePlugin;
import edu.uci.ics.jung.visualization.picking.PickedState;
import graph.graph.Edge;
import graph.graph.Sphere;
import graph.graph.Vertex;
import graph.visualization.SyndromVisualisationViewer;
import graph.visualization.picking.SyndromPickSupport;

import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;

public class GeneralPickingPlugin extends AbstractGraphMousePlugin
        implements MouseListener, MouseMotionListener {

    private HelperFunctions helper = new HelperFunctions();

    public GeneralPickingPlugin() {
        super(InputEvent.BUTTON3_DOWN_MASK | InputEvent.BUTTON1_DOWN_MASK);
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

        if (sp == null && edge == null && vertex == null){
            PickedState<Vertex> vertexPickedState = vv.getPickedVertexState();
            PickedState<Edge> edgePickedState = vv.getPickedEdgeState();
            PickedState<Sphere> spherePickedState = vv.getPickedSphereState();
            vertexPickedState.clear();
            edgePickedState.clear();
            spherePickedState.clear();
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
        setMousePositionText(e);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        setMousePositionText(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        setMousePositionText(e);
    }

    @SuppressWarnings("unchecked")
    private void setMousePositionText(MouseEvent e){
        SyndromVisualisationViewer<Vertex, Edge> vv = (SyndromVisualisationViewer<Vertex, Edge>) e.getSource();
        Point2D position = vv.getRenderContext().getMultiLayerTransformer().inverseTransform(e.getPoint());
        double x = (double) Math.round(position.getX()*100)/100;
        double y = (double) Math.round(position.getY()*100)/100;
        helper.setMouseLocation("X: "+x, "Y: "+y);
    }
}
