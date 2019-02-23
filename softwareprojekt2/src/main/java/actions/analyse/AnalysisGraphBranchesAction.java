package actions.analyse;

import actions.GraphAction;
import graph.graph.Edge;
import graph.graph.SyndromGraph;
import graph.graph.Vertex;
import graph.visualization.SyndromVisualisationViewer;
import jgrapht.JGraphTHandler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

/**
 * Analyses the graph in matter of heavily connected vertices or highly important vertices.
 * This action will find and highlight all branches in the graph.
 */
public class AnalysisGraphBranchesAction extends GraphAction {

    /**
     * Constructor in case the user chooses a AnalyseTypeSeveral - analyse option.
     * These analyse functions are implemented by JGraphT functions.
     * After analysing the graph and finding out the values the action is looking for,
     * the information is displayed or the found vertices/edges get highlighted.
     * The action is applied to all picked vertices/edges or to all objects if nothing is picked.
     */
    public AnalysisGraphBranchesAction() {
    }

    /**
     * Analyses the graph on the given criteria. All the
     * calculated edges and vertices will be highlighted.
     */
    @Override
    public void action() {
        SyndromVisualisationViewer<Vertex, Edge> vv = syndrom.getVv();
        SyndromGraph<Vertex, Edge> graph = (SyndromGraph<Vertex, Edge>) vv.getGraphLayout().getGraph();

        JGraphTHandler jGraphTHandler = new JGraphTHandler();
        ArrayList<Edge> edgesAnalyse = new ArrayList<>();
        ArrayList<Vertex> verticesAnalyse = new ArrayList<>();


        Set<Vertex> branches = jGraphTHandler.detectDivergentBranches();
        branches.addAll(jGraphTHandler.detectConvergentBranches());
        for (Vertex vertex : branches) {
            Collection<Edge> predecessors = graph.getInEdges(vertex);
            if (predecessors.size() > 1) {
                edgesAnalyse.addAll(predecessors);
            }
            Collection<Edge> successors = graph.getOutEdges(vertex);
            if (successors.size() > 1) {
                edgesAnalyse.addAll(successors);
            }
        }
        verticesAnalyse.addAll(branches);

        ShowAnalysisResultAction showAnalysisResultAction = new ShowAnalysisResultAction(verticesAnalyse, edgesAnalyse);
        showAnalysisResultAction.action();
    }

    /**
     * There is no undo for this action.
     */
    @Override
    public void undo() {
        return;
    }
}
