package actions.analyse;

import actions.GraphAction;
import graph.graph.Edge;
import graph.graph.SyndromGraph;
import graph.graph.Vertex;
import graph.visualization.SyndromVisualisationViewer;
import jgrapht.JGraphTHandler;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * Analyses the graph in matter of heavily connected vertices or highly important vertices.
 * This action finds all cycles in the graph.
 * @author Clement Phung
 */
public class AnalysisGraphCyclesAction extends GraphAction {
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

        List<List<Vertex>> listCylces = jGraphTHandler.detectCycles();
        for (List<Vertex> list : listCylces) {
            for (int i = 0; i < list.size(); i++) {
                verticesAnalyse.add(list.get(i));
                if (i == list.size() - 1) {
                    edgesAnalyse.add(graph.findEdge(list.get(i), list.get(0)));
                } else {
                    edgesAnalyse.add(graph.findEdge(list.get(i), list.get(i + 1)));
                }
            }
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
