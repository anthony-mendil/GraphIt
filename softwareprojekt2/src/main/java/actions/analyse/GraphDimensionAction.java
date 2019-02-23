package actions.analyse;

import actions.GraphAction;
import graph.graph.Edge;
import graph.graph.SyndromGraph;
import graph.graph.Vertex;
import graph.visualization.SyndromVisualisationViewer;
import javafx.util.Pair;
import jgrapht.JGraphTHandler;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Displays some useful data in matter of the dimension of the graph (scope, networking index, structural index). The
 * information is displayed in the gui.
 */
public class GraphDimensionAction extends GraphAction {
    /**
     * The scope.
     */
    @Getter
    private double scope;
    /**
     * The networkIndex.
     */
    @Getter
    private double networkIndex;
    /**
     * The structureindex.
     */
    @Getter
    private double structureIndex;

    /**
     * Computes the data needed for the current graph.
     */
    public GraphDimensionAction() {
        SyndromVisualisationViewer<Vertex, Edge> vv = syndrom.getVv();
        SyndromGraph<Vertex, Edge> graph = (SyndromGraph<Vertex, Edge>) vv.getGraphLayout().getGraph();

        /**
         * Using the JGraphTHandler for more complex calculations.
         */
        JGraphTHandler jGraphTHandler = new JGraphTHandler();


        /**
         * Calculating the indices.
         */
        scope = (double) (graph.getSpheres().size() + graph.getVertices().size());
        networkIndex = (double) (2 * graph.getEdges().size()) / graph.getVertices().size();
        structureIndex = (double) (jGraphTHandler.detectRelationChains().getKey().size() +
                jGraphTHandler.detectConvergentBranches().size() +
                jGraphTHandler.detectDivergentBranches().size() +
                jGraphTHandler.detectCycles().size()) / graph.getVertices().size();

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
