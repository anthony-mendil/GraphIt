package actions.analyse;

import actions.GraphAction;
import graph.graph.Edge;
import graph.graph.Vertex;
import javafx.util.Pair;
import jgrapht.JGraphTHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Analyses the graph in matter of heavily connected vertices or highly important vertices.
 * <p>
 * This Action finds all edge-chains in the graph.
 */
public class AnalysisGraphEdgeChainsAction extends GraphAction {


    /**
     * Analyses the graph on the given criteria. All the
     * calculated edges and vertices will be highlighted.
     */
    @Override
    public void action() {

        JGraphTHandler jGraphTHandler = new JGraphTHandler();
        ArrayList<Vertex> verticesAnalyse = new ArrayList<>();

        Pair<List<List<Vertex>>, Set<Edge>> edgeChains = jGraphTHandler.detectRelationChains();
        for (List<Vertex> list : edgeChains.getKey()) {
            verticesAnalyse.addAll(list);
        }
        ArrayList<Edge> edgesAnalyse = new ArrayList<>(edgeChains.getValue());

        ShowAnalysisResultAction showAnalysisResultAction = new ShowAnalysisResultAction(verticesAnalyse, edgesAnalyse);
        showAnalysisResultAction.action();
    }

    /**
     * There is no undo for this action.
     */
    @Override
    public void undo() {
        //no undo operation for this action
    }
}
