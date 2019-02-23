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
import org.apache.log4j.Logger;

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

    private static Logger logger = Logger.getLogger(LayoutVerticesGraphLogAction.class);

    /**
     * Layouts the graph (including all vertices) according to the defined layout.
     */
    public LayoutVerticesGraphLogAction() {
        super(LogEntryName.EDIT_VERTICES_LAYOUT);
    }

    public LayoutVerticesGraphLogAction(LayoutVerticesParam pLayoutVerticesParam) {
        super(LogEntryName.EDIT_VERTICES_LAYOUT);
        parameters = pLayoutVerticesParam;
    }

    public void createParameter(Map<Vertex,Point2D> oldVertices) {
        parameters = new LayoutVerticesParam(oldVertices);
     }

    @Override
    public void action() {
        SyndromVisualisationViewer<Vertex, Edge> vv = syndrom.getVv();
        AggregateLayout<Vertex, Edge> layout =  syndrom.getLayout();
        SyndromGraph<Vertex, Edge> graph = (SyndromGraph<Vertex, Edge>) syndrom.getVv().getGraphLayout().getGraph();
        if(parameters == null || indicator == true){
            Map<Vertex,Point2D> oldVertices = new HashMap<>();
        for (Sphere s : graph.getSpheres()) {
            List<Vertex> vertices = s.getVertices();

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
                    logger.error(ex.toString());
                }
            }
        }

        vv.setGraphLayout(layout);

        for (Vertex v : graph.getVertices()){
            oldVertices.put(v, v.getCoordinates());
            v.setCoordinates(layout.transform(v));
        }

        indicator = true;
        createParameter(oldVertices);
        } else{
            Map<Vertex,Point2D> oldVertices = ((LayoutVerticesParam)parameters).getOldVerticesMap();
            for(Map.Entry<Vertex,Point2D> entry : oldVertices.entrySet()){
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


    @Override
    public void undo() {
        LayoutVerticesParam layoutVerticesParam = (LayoutVerticesParam)parameters;
        LayoutVerticesGraphLogAction layoutVerticesGraphLogAction = new LayoutVerticesGraphLogAction(layoutVerticesParam);
        layoutVerticesGraphLogAction.action();
    }
}
