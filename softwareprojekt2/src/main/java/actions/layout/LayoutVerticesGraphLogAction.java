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
import log_management.DatabaseManager;
import log_management.parameters.move.LayoutVerticesParam;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Layouts the graph according to a previously defined layout.
 */
public class LayoutVerticesGraphLogAction extends LogAction {
    /**
     * Indicator whether the action is an undo action.
     */
    private boolean indicator;

    /**
     * Layouts the graph (including all vertices) according to the defined layout.
     */
    public LayoutVerticesGraphLogAction() {
        super(LogEntryName.EDIT_VERTICES_LAYOUT);
    }

    private LayoutVerticesGraphLogAction(LayoutVerticesParam pLayoutVerticesParam) {
        super(LogEntryName.EDIT_VERTICES_LAYOUT);
        parameters = pLayoutVerticesParam;
    }

    /**
     * Creates a parameter-object for this action.
     * @param oldVertices The map of vertices and their old position.
     */
    public void createParameter(Map<Vertex, Point2D> oldVertices) {
        parameters = new LayoutVerticesParam(oldVertices);
    }

    @Override
    public void action() {
        SyndromVisualisationViewer<Vertex, Edge> vv = syndrom.getVv();
        AggregateLayout<Vertex, Edge> layout = syndrom.getLayout();
        SyndromGraph<Vertex, Edge> graph = (SyndromGraph<Vertex, Edge>) syndrom.getVv().getGraphLayout().getGraph();
        if (parameters == null || indicator) {
            Map<Vertex, Point2D> oldVertices = new HashMap<>();
            layoutGraph(graph, layout);

            vv.setGraphLayout(layout);
            for (Vertex v : graph.getVertices()) {
                oldVertices.put(v, v.getCoordinates());
                v.setCoordinates(layout.transform(v));
            }

            indicator = true;
            createParameter(oldVertices);
        } else {
            Map<Vertex, Point2D> oldVertices = ((LayoutVerticesParam) parameters).getOldVerticesMap();
            for (Map.Entry<Vertex, Point2D> entry : oldVertices.entrySet()) {
                entry.getKey().setCoordinates(oldVertices.get(entry.getKey()));
            }
        }
        layout.removeAll();
        for (Vertex v : graph.getVertices()) {
            layout.setLocation(v, v.getCoordinates());
        }
        vv.setGraphLayout(layout);

        vv.repaint();
        syndrom.getVv2().repaint();
        DatabaseManager databaseManager = DatabaseManager.getInstance();
        databaseManager.addEntryDatabase(createLog());
        notifyObserverGraph();
    }

    /**
     * Layouts the graph.
     * @param graph The graph.
     * @param layout The layout.
     */
    private void layoutGraph(SyndromGraph<Vertex, Edge> graph, AggregateLayout<Vertex, Edge> layout) {
        for (Sphere s : graph.getSpheres()) {
            List<Vertex> vertices = s.getVertices();

            if (vertices.size() > 1 && !graph.getEdges().isEmpty()) {
                Point2D center = new Point2D.Double();
                center.setLocation(s.getCoordinates().getX() + (s.getWidth() / 2), s.getCoordinates().getY() + (s
                        .getHeight() / 2));

                SyndromGraph<Vertex, Edge> subGraph = layoutSphere(s, vertices, graph);
                Layout<Vertex, Edge> subLayout = new KKLayout<>(subGraph);
                subLayout.setSize(new Dimension((int) s.getWidth(), (int) s.getHeight()));
                layout.put(subLayout, center);
            }
        }
    }

    /**
     * Calculates the layout for each sphere.
     * @param s The sphere.
     * @param vertices The vertices of the sphere.
     * @param graph The current graph.
     * @return A new SubGraph. (for the sphere)
     */
    private SyndromGraph<Vertex, Edge> layoutSphere(Sphere s, List<Vertex> vertices, SyndromGraph<Vertex, Edge> graph) {
        SyndromGraph<Vertex, Edge> subGraph = new SyndromGraph<>();
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
        return subGraph;
    }

    @Override
    public void undo() {
        LayoutVerticesParam layoutVerticesParam = (LayoutVerticesParam) parameters;
        LayoutVerticesGraphLogAction layoutVerticesGraphLogAction = new LayoutVerticesGraphLogAction(layoutVerticesParam);
        layoutVerticesGraphLogAction.action();
    }
}
