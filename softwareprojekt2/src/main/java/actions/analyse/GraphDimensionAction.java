package actions.analyse;

import actions.GraphAction;
import graph.graph.Edge;
import graph.graph.SyndromGraph;
import graph.graph.Vertex;
import graph.visualization.SyndromVisualisationViewer;
import jgrapht.JGraphTHandler;
import lombok.Getter;

/**
 * Displays some useful data in matter of the dimension of the graph (scope, networking index, structural index). The
 * information is displayed in the gui. Pls look in the website for the formula of the indices.
 * @author Clement Phung
 */
public class GraphDimensionAction extends GraphAction {
    /**
     * The scope.
     */
    @Getter
    private int scope;
    /**
     * The networkIndex.
     */
    @Getter
    private Double networkIndex;
    /**
     * The structureIndex.
     */
    @Getter
    private Double structureIndex;


    /**
     * Does the processing/calculating stuff.
     */
    @Override
    public void action() {
        SyndromVisualisationViewer<Vertex, Edge> vv = syndrom.getVv();
        SyndromGraph<Vertex, Edge> graph = (SyndromGraph<Vertex, Edge>) vv.getGraphLayout().getGraph();

        /*
         * Using the JGraphTHandler for more complex calculations.
         */
        JGraphTHandler jGraphTHandler = new JGraphTHandler();

        /*
         * Calculating the indices. Dividing by zero is not so useful.
         */

        scope = graph.getEdges().size() + graph.getVertices().size();
        if (!graph.getVertices().isEmpty()) {
            networkIndex = (double) 2 * graph.getEdges().size() / graph.getVertices().size();
            structureIndex = (double) (jGraphTHandler.detectRelationChains().getKey().size() +
                    jGraphTHandler.detectConvergentBranches().size() +
                    jGraphTHandler.detectDivergentBranches().size() +
                    jGraphTHandler.detectCycles().size()) / graph.getVertices().size();
        } else {
            networkIndex = (double) -1;
            structureIndex = (double) -1;
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
