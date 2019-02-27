package actions.analyse;

import actions.GraphAction;
import graph.graph.Edge;
import graph.graph.Vertex;
import javafx.util.Pair;
import jgrapht.JGraphTHandler;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Analyses the graph in matter of heavily connected vertices or highly important vertices.
 * This Action finds all edge-chains in the graph. An edge-chain is an chain of relations,
 * with a size of three vertices, which none of them is a branch.
 */
public class AnalysisGraphEdgeChainsAction extends GraphAction {

    /**
     * The set of edges calculated as a result.
     */
    @Getter
    private ArrayList<Edge> edgesAnalyse = new ArrayList<>();
    /**
     * The set of vertices calculated as a result.
     */
    @Getter
    private ArrayList<Vertex> verticesAnalyse = new ArrayList<>();
    /**
     * Analyses the graph on the given criteria. All the
     * calculated edges and vertices will be highlighted.
     */
    @Override
    public void action() {

        JGraphTHandler jGraphTHandler = new JGraphTHandler();

        Pair<List<List<Vertex>>, Set<Edge>> edgeChains = jGraphTHandler.detectRelationChains();
        for (List<Vertex> list : edgeChains.getKey()) {
            verticesAnalyse.addAll(list);
        }

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
