package actions.analyse;

import actions.GraphAction;
import graph.graph.Edge;
import graph.graph.SyndromGraph;
import graph.graph.Vertex;
import graph.visualization.SyndromVisualisationViewer;
import jgrapht.JGraphTHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Analyses the graph in matter of heavily connected vertices or highly important vertices.
 * <p>
 * This action finds all cycles in the graph.
 */
public class AnalysisGraphCyclesAction extends GraphAction {

    /**
     * Constructor in case the user chooses a AnalyseTypeSingle - analyse option.
     * These analyse functions are implemented by JGraphT algorithms and will be processed through the JGraphT Handler.
     * After processing and finding out the values the action is looking for, the information is displayed
     * or the found vertices/ edges get highlighted.
     * The action is applied to all picked vertices/ edges or to all objects if nothing is picked.
     */
    public AnalysisGraphCyclesAction() {

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
        return;
    }
}
