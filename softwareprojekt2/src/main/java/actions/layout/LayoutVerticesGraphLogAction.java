package actions.layout;

import actions.LogAction;
import actions.LogEntryName;
import edu.uci.ics.jung.algorithms.layout.AggregateLayout;
import edu.uci.ics.jung.algorithms.layout.KKLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.util.Pair;
import graph.graph.Edge;
import graph.graph.Sphere;
import graph.graph.SyndromGraph;
import graph.graph.Vertex;
import graph.visualization.SyndromVisualisationViewer;
import graph.visualization.picking.SyndromPickSupport;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Collection;
import java.util.LinkedList;

/**
 * Layouts the graph according to a previously defined layout.
 */
public class LayoutVerticesGraphLogAction extends LogAction {

    /**
     * Layouts the graph (including all vertices) according to the defined layout.
     */
    public LayoutVerticesGraphLogAction() {
        super(LogEntryName.EDIT_VERTICES_LAYOUT);
    }

    //@Override
    //public void createParameter() {
    //    throw new UnsupportedOperationException();
    //}

    @Override
    public void action() {
        SyndromVisualisationViewer<Vertex, Edge> vv = syndrom.getVv();
        AggregateLayout<Vertex, Edge> layout =  syndrom.getLayout();
        SyndromGraph<Vertex, Edge> graph = (SyndromGraph<Vertex, Edge>) syndrom.getVv().getGraphLayout().getGraph();
        for (Sphere s : graph.getSpheres()) {
            LinkedList<Vertex> vertices = s.getVertices();

            if (vertices.size() > 1 && !graph.getEdges().isEmpty()) {
                Point2D center = new Point2D.Double();
                center.setLocation(s.getCoordinates().getX() + (s.getWidth() / 2), s.getCoordinates().getY() + (s
                        .getHeight() / 2));

                SyndromGraph<Vertex, Edge> subGraph;
                try {
                    subGraph = new SyndromGraph<>();
                    subGraph.addSphere(s.getCoordinates());
                    for (Vertex v : vertices) {
                        subGraph.addVertex(v);
                        Collection<Edge> incidentEdges = graph.getIncidentEdges(v);
                        for (Edge edge : incidentEdges) {
                            Pair<Vertex> endpoints = graph.getEndpoints(edge);
                            if (vertices.containsAll(endpoints)) {
                                subGraph.addEdge(edge, endpoints.getFirst(), endpoints.getSecond());
                            }
                        }
                    }

                    Layout<Vertex, Edge> subLayout = new KKLayout<>(subGraph);
                    subLayout.setSize(new Dimension((int)s.getWidth(),(int) s.getHeight()));
                    layout.put(subLayout, center);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }

        vv.setGraphLayout(layout);

        SyndromPickSupport<Vertex, Edge> pickSupport = (SyndromPickSupport) vv.getPickSupport();

        for (Point2D point2D : layout.getLayouts().values()){
            Sphere sp = pickSupport.getSphere(point2D.getX(), point2D.getY());
            if (sp != null){
                for (Vertex v: sp.getVertices()){
                    v.setCoordinates(layout.transform(v));
                }
            }
        }


        layout.removeAll();

        for (Sphere s : graph.getSpheres()) {
            for(Vertex v : s.getVertices()){
                layout.setLocation(v,v.getCoordinates());
            }
        }

        vv.setGraphLayout(layout);

        vv.repaint();
    }


    @Override
    public void undo() {
        throw new UnsupportedOperationException();
    }
}
