package actions.analyse;

import actions.GraphAction;
import graph.graph.Edge;
import graph.graph.Vertex;
import graph.visualization.control.HelperFunctions;
import gui.properties.Language;
import jgrapht.JGraphTHandler;
import org.jgrapht.GraphPath;

import java.util.ArrayList;

/**
 * Analyses the graph in matter of heavily connected vertices or highly important vertices.
 * This action finds the shortest path between two selected vertices.
 */
public class AnalysisGraphShortestPathAction extends GraphAction {

    /**
     * Analyses the graph on the given criteria. All the
     * calculated edges and vertices will be highlighted.
     */
    @Override
    public void action() {

        JGraphTHandler jGraphTHandler = new JGraphTHandler();
        ArrayList<Edge> edgesAnalyse = new ArrayList<>();
        ArrayList<Vertex> verticesAnalyse = new ArrayList<>();

        GraphPath<Vertex, Edge> shortestPath = jGraphTHandler.getShortestPath();
        if (shortestPath == null) {
            HelperFunctions helperFunctions = new HelperFunctions();
            helperFunctions.setActionText("Es sxistiert kein Weg von " + jGraphTHandler.getStartVertex().getAnnotation().get(Language.GERMAN.name()) + " nach " + jGraphTHandler.getEndVertex().getAnnotation().get(Language.GERMAN.name()), true, false);
        } else {
            verticesAnalyse.addAll(shortestPath.getVertexList());
            edgesAnalyse.addAll(shortestPath.getEdgeList());
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
