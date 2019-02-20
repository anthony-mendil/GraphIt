package actions.analyse;

import actions.GraphAction;
import edu.uci.ics.jung.algorithms.layout.AggregateLayout;
import graph.graph.Edge;
import graph.graph.SyndromGraph;
import graph.graph.Vertex;
import graph.visualization.SyndromVisualisationViewer;
import javafx.util.Pair;
import jgrapht.JGraphTHandler;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Displays some useful data in matter of the dimension of the graph (scope, networking index, structural index). The
 * information is displayed in the gui.
 */
public class GraphDimensionAction extends GraphAction {
    /**
     * Computes the data needed for the current graph.
     */
    public GraphDimensionAction() {
        SyndromVisualisationViewer<Vertex, Edge> vv = syndrom.getVv();
        SyndromGraph<Vertex, Edge> graph = (SyndromGraph<Vertex, Edge>) vv.getGraphLayout().getGraph();

        /**
         * Using the JGraphTHandler for more complex calculations.
         */
        Set<Pair<Vertex,Vertex>> edges = new HashSet<>();
        for(Edge edge : graph.getEdges()){
            edu.uci.ics.jung.graph.util.Pair<Vertex> jungPair = graph.getEndpoints(edge);
            Pair<Vertex,Vertex> vertices = new Pair<>(jungPair.getFirst(), jungPair.getSecond());
            edges.add(vertices);
        }
        JGraphTHandler jGraphTHandler = new JGraphTHandler(new ArrayList<>(graph.getVertices()),edges);


        /**
         * Calculating the indices.
         */
        double umfang = graph.getSpheres().size() + graph.getVertices().size();
        double vernetzungsindex = (2 * graph.getEdges().size())/graph.getVertices().size();
        double strukturindex = 3;

    }

    /**
     * There os no undo operation for this.
     */
    @Override
    public void action() {
        return;
    }

    /**
     * There is no redo operation
     */
    @Override
    public void undo() {
        return;
    }
}
