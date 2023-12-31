package actions.analyse;

import actions.GraphAction;
import graph.graph.Edge;
import graph.graph.SyndromGraph;
import graph.graph.Vertex;
import graph.visualization.SyndromVisualisationViewer;
import jgrapht.JGraphTHandler;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

/**
 * Analyses the graph in matter of heavily connected vertices or highly important vertices.
 * This action finds all divergent branches in the graph. A divergent branch is a vertex,
 * which has in degree of at least two of incoming relations.
 * @author Clement Phung
 */
public class AnalysisGraphDivergentBranchesAction extends GraphAction {
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
        SyndromVisualisationViewer<Vertex, Edge> vv = syndrom.getVv();
        SyndromGraph<Vertex, Edge> graph = (SyndromGraph<Vertex, Edge>) vv.getGraphLayout().getGraph();

        JGraphTHandler jGraphTHandler = new JGraphTHandler();

        Set<Vertex> divergentBranches = jGraphTHandler.detectDivergentBranches();
        for (Vertex vertex : divergentBranches) {
            Collection<Edge> successors = graph.getOutEdges(vertex);
            edgesAnalyse.addAll(successors);
        }
        verticesAnalyse.addAll(divergentBranches);

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
