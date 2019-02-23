package actions.analyse;

import actions.GraphAction;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import graph.algorithmen.AnalyseTypeSeveral;
import graph.algorithmen.AnalyseTypeSingle;
import graph.graph.Edge;
import graph.graph.EdgeArrowType;
import graph.graph.SyndromGraph;
import graph.graph.Vertex;
import graph.visualization.SyndromVisualisationViewer;
import graph.visualization.transformer.edge.EdgePaintAnalyseTransformer;
import graph.visualization.transformer.vertex.VertexPaintAnalyseTransformer;
import javafx.util.Pair;
import jgrapht.JGraphTHandler;
import org.jgrapht.GraphPath;

import java.util.*;

/**
 * Analyses the graph in matter of heavily connected vertices or highly important vertices.
 * <p>
 * This action finds all predecessors and successors of the selected symptom in x steps.
 */
public class AnalysisGraphNeighborsAction extends GraphAction {
    /**
     * The mode if either the predecessors/ successors or both should be highlighted.
     */
    private AnalyseTypeSeveral analyseTypeSeveral;
    /**
     * The amount of successors/predecessors of a specific vertex.
     */
    private int amountSteps = 0;

    /**
     * Constructor in case the user chooses a AnalyseTypeSingle - analyse option.
     * These analyse functions are implemented by JGraphT algorithms and will be processed through the JGraphT Handler.
     * After processing and finding out the values the action is looking for, the information is displayed
     * or the found vertices/ edges get highlighted.
     * The action is applied to all picked vertices/ edges or to all objects if nothing is picked.
     */
    public AnalysisGraphNeighborsAction(AnalyseTypeSeveral analyseTypeSeveral, int amountSteps) {
        this.analyseTypeSeveral = analyseTypeSeveral;
        this.amountSteps = amountSteps;
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

        if (analyseTypeSeveral == AnalyseTypeSeveral.NEIGHBOUR_PREDECESSOR) {
            Pair<List<Vertex>, List<Edge>> predecessors = jGraphTHandler.predecessorsIterations(amountSteps);
            verticesAnalyse.addAll(predecessors.getKey());
            edgesAnalyse.addAll(predecessors.getValue());
        } else if (analyseTypeSeveral == AnalyseTypeSeveral.NEIGHBOUR_SUCCESSOR) {
            Pair<List<Vertex>, List<Edge>> successors = jGraphTHandler.successorIterations(amountSteps);
            verticesAnalyse.addAll(successors.getKey());
            edgesAnalyse.addAll(successors.getValue());
        } else {
            Pair<List<Vertex>, List<Edge>> predecessors = jGraphTHandler.predecessorsIterations(amountSteps);
            verticesAnalyse.addAll(predecessors.getKey());
            edgesAnalyse.addAll(predecessors.getValue());

            Pair<List<Vertex>, List<Edge>> successors = jGraphTHandler.successorIterations(amountSteps);
            verticesAnalyse.addAll(successors.getKey());
            edgesAnalyse.addAll(successors.getValue());
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
