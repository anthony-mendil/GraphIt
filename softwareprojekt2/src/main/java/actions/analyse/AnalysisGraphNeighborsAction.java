package actions.analyse;

import actions.GraphAction;
import graph.algorithmen.AnalyseType;
import graph.graph.Edge;
import graph.graph.Vertex;
import javafx.util.Pair;
import jgrapht.JGraphTHandler;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * Analyses the graph in matter of heavily connected vertices or highly important vertices.
 * This action finds all predecessors and successors of the selected symptom in the given steps.
 * @author Clement Phung
 */
public class AnalysisGraphNeighborsAction extends GraphAction {
    /**
     * The mode if either the predecessors/ successors or both should be highlighted.
     */
    private AnalyseType analyseType;
    /**
     * The amount of successors/predecessors of a specific vertex.
     */
    private int amountSteps;
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
     * Constructor in case the user chooses a AnalyseType - neighbor option.
     * These analyse functions are implemented by JGraphT algorithms and will be processed through the JGraphT Handler.
     * After processing and finding out the values the action is looking for, the information is displayed
     * or the found vertices/ edges get highlighted.
     * The action is applied to all picked vertices/ edges or to all objects if nothing is picked.
     *
     * @param analyseType The selected mode: successor/predecessor/both
     * @param amountSteps The amount of iterations that should be calculated.
     */
    public AnalysisGraphNeighborsAction(AnalyseType analyseType, int amountSteps) {
        this.analyseType = analyseType;
        this.amountSteps = amountSteps;
    }

    /**
     * Analyses the graph on the given criteria. All the
     * calculated edges and vertices will be highlighted.
     */
    @Override
    public void action() {

        JGraphTHandler jGraphTHandler = new JGraphTHandler();

        if (analyseType == AnalyseType.NEIGHBOUR_PREDECESSOR) {
            Pair<List<Vertex>, List<Edge>> predecessors = jGraphTHandler.predecessorsIterations(amountSteps);
            verticesAnalyse.addAll(predecessors.getKey());
            edgesAnalyse.addAll(predecessors.getValue());
        } else if (analyseType == AnalyseType.NEIGHBOUR_SUCCESSOR) {
            Pair<List<Vertex>, List<Edge>> successors = jGraphTHandler.successorIterations(amountSteps);
            verticesAnalyse.addAll(successors.getKey());
            edgesAnalyse.addAll(successors.getValue());
        } else if (analyseType == AnalyseType.NEIGHBOUR_PREDECESSOR_SUCCESSOR) {
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
        //no undo operation for this action
    }
}
