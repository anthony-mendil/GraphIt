package actions.analyse;

import actions.GraphAction;
import graph.graph.Edge;
import graph.graph.SyndromGraph;
import graph.graph.Vertex;
import graph.visualization.SyndromVisualisationViewer;
import javafx.util.Pair;
import jgrapht.JGraphTHandler;
import lombok.Getter;

import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.Set;

/**
 * Displays some useful data in matter of the dimension of the graph (scope, networking index, structural index). The
 * information is displayed in the gui. Pls look in the website for the formula of the indices.
 */
public class GraphDimensionAction extends GraphAction {
    /**
     * The scope.
     */
    @Getter
    private String scope;
    /**
     * The networkIndex.
     */
    @Getter
    private String networkIndex;
    /**
     * The structureIndex.
     */
    @Getter
    private String structureIndex;


    /**
     * Does the processing/calculating stuff.
     */
    @Override
    public void action() {
        SyndromVisualisationViewer<Vertex, Edge> vv = syndrom.getVv();
        SyndromGraph<Vertex, Edge> graph = (SyndromGraph<Vertex, Edge>) vv.getGraphLayout().getGraph();

        /**
         * Using the JGraphTHandler for more complex calculations.
         */
        Set<Pair<Vertex, Vertex>> edges = new HashSet<>();
        for (Edge edge : graph.getEdges()) {
            edu.uci.ics.jung.graph.util.Pair<Vertex> jungPair = graph.getEndpoints(edge);
            Pair<Vertex, Vertex> vertices = new Pair<>(jungPair.getFirst(), jungPair.getSecond());
            edges.add(vertices);
        }
        JGraphTHandler jGraphTHandler = new JGraphTHandler();


        /**
         * Calculating the indices. Dividing by zero is not so useful.
         */
        DecimalFormat format = new DecimalFormat("####.##");

        scope = format.format(graph.getEdges().size() + (long) graph.getVertices().size());
        if (!graph.getVertices().isEmpty()) {
            networkIndex = format.format((double) 2 * graph.getEdges().size() / graph.getVertices().size());
            structureIndex = format.format((double) (jGraphTHandler.detectRelationChains().getKey().size() +
                    jGraphTHandler.detectConvergentBranches().size() +
                    jGraphTHandler.detectDivergentBranches().size() +
                    jGraphTHandler.detectCycles().size()) / graph.getVertices().size());
        } else {
            networkIndex = "NaN";
            structureIndex = "NaN";
        }
    }

    /**
     * There is no undo operation
     */
    @Override
    public void undo() {
        //no undo operation for this action
    }
}
