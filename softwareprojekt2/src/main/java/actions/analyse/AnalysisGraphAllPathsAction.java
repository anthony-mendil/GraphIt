package actions.analyse;

import actions.GraphAction;
import graph.graph.Edge;
import graph.graph.Vertex;
import graph.visualization.control.HelperFunctions;
import gui.properties.Language;
import jgrapht.JGraphTHandler;
import org.jgrapht.GraphPath;

import java.util.ArrayList;
import java.util.List;

/**
 * Analyses the graph in matter of heavily connected vertices or highly important vertices.
 * <p>
 * This Action find all different paths between two selected vertices.
 */
public class AnalysisGraphAllPathsAction extends GraphAction {


    /**
     * Analyses the graph on the given criteria. All the
     * calculated edges and vertices will be highlighted.
     */
    @Override
    public void action() {

        JGraphTHandler jGraphTHandler = new JGraphTHandler();
        ArrayList<Edge> edgesAnalyse = new ArrayList<>();
        ArrayList<Vertex> verticesAnalyse = new ArrayList<>();
        List<GraphPath<Vertex, Edge>> allPaths;
        allPaths = jGraphTHandler.getAllPaths();
        if (allPaths.isEmpty()) {
            HelperFunctions helperFunctions = new HelperFunctions();
            helperFunctions.setActionText("Es sxistiert kein Weg von " + jGraphTHandler.getStartVertex().getAnnotation().get(Language.GERMAN.name()) + " nach " + jGraphTHandler.getEndVertex().getAnnotation().get(Language.GERMAN.name()), true, false);

        }
        for (GraphPath<Vertex, Edge> path : allPaths) {
            verticesAnalyse.addAll(path.getVertexList());
            edgesAnalyse.addAll(path.getEdgeList());
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
