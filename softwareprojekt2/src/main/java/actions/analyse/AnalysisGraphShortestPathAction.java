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
 * <p>
 * This action finds the shortest path between two selected vertices.
 */
public class AnalysisGraphShortestPathAction extends GraphAction {
    /**
     * Constructor in case the user chooses a AnalyseTypeSingle - analyse option.
     * These analyse functions are implemented by JGraphT algorithms and will be processed through the JGraphT Handler.
     * After processing and finding out the values the action is looking for, the information is displayed
     * or the found vertices/ edges get highlighted.
     * The action is applied to all picked vertices/ edges or to all objects if nothing is picked.
     */
    public AnalysisGraphShortestPathAction() {
        //can analyse shortest path now
    }

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