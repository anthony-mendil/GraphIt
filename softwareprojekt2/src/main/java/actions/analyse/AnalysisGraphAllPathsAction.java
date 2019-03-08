package actions.analyse;

import actions.GraphAction;
import graph.graph.Edge;
import graph.graph.Vertex;
import graph.visualization.control.HelperFunctions;
import gui.Values;
import gui.properties.LoadLanguage;
import jgrapht.JGraphTHandler;
import lombok.Getter;
import org.jgrapht.GraphPath;

import java.util.ArrayList;
import java.util.List;

/**
 * Analyses the graph in matter of heavily connected vertices or highly important vertices.
 * This action finds all disjunctive paths between two selected vertices.
 */
public class AnalysisGraphAllPathsAction extends GraphAction {
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
        List<GraphPath<Vertex, Edge>> allPaths;
        allPaths = jGraphTHandler.getAllPaths();
        if (allPaths.isEmpty()) {
            HelperFunctions helperFunctions = new HelperFunctions();
            helperFunctions.setActionText(LoadLanguage.getInstance().loadLanguagesKey("J_GRAPH_T_NO_WAY1")
                    + jGraphTHandler.getStartVertex().getAnnotation().get(Values.getInstance().getGraphLanguage().name())
                    + LoadLanguage.getInstance().loadLanguagesKey("J_GRAPH_T_NO_WAY2")+ " "
                    + jGraphTHandler.getEndVertex().getAnnotation()
                    .get(Values.getInstance().getGraphLanguage().name()), true, false);
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
