package actions.analyse;

import actions.GraphAction;
import graph.graph.Edge;
import graph.graph.EdgeArrowType;
import graph.graph.SyndromGraph;
import graph.graph.Vertex;
import graph.visualization.SyndromVisualisationViewer;

import java.util.ArrayList;

/**
 * Analyses the graph in matter of heavily connected vertices or highly important vertices.
 * <p>
 * This action finds all extenuating relations.
 */
public class AnalysisGraphExtenuatingRelationsAction extends GraphAction {

    /**
     * Analyses the graph on the given criteria. All the
     * calculated edges and vertices will be highlighted.
     */
    @Override
    public void action() {
        SyndromVisualisationViewer<Vertex, Edge> vv = syndrom.getVv();
        SyndromGraph<Vertex, Edge> graph = (SyndromGraph<Vertex, Edge>) vv.getGraphLayout().getGraph();

        ArrayList<Edge> edgesAnalyse = new ArrayList<>();
        ArrayList<Vertex> verticesAnalyse = new ArrayList<>();

        for (Edge edge : graph.getEdges()) {
            if (edge.getArrowType() == EdgeArrowType.EXTENUATING) {
                edgesAnalyse.add(edge);
                edu.uci.ics.jung.graph.util.Pair<Vertex> endPoints = graph.getEndpoints(edge);
                verticesAnalyse.add(endPoints.getFirst());
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