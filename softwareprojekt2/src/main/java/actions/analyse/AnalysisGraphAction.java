package actions.analyse;

import actions.GraphAction;
import edu.uci.ics.jung.graph.util.EdgeType;
import graph.algorithmen.AnalyseTypeSeveral;
import graph.algorithmen.AnalyseTypeSingle;

import java.util.List;

/**
 * Analyses the graph in matter of heavily connected vertices or highly important vertices.
 * <p>
 * There are a few possibilities for the user to analyse the graph. We differentiate between two analyse types:
 * AnalyseTypeSeveral and AnalyseTypeSingle. The user can analyse the graph with multiple analysis types at once with
 * multiple AnalyseTypeSeveral selections. Unlike the AnalyseTypeSingle. Here is only one type selectable.
 * </p>
 *
 */
public class AnalysisGraphAction extends GraphAction{
    /**
     * The list of analyse types, several types can be passed and processed at once.
     */
    private List<AnalyseTypeSeveral> analyseTypeSeverals;

    /**
     * A single analyse type, one type can be analysed at once.
     */
    private AnalyseTypeSingle analyseTypeSingle;

    /**
     * Constructor in case the user chooses a AnalyseTypeSeveral - analyse option.
     * These analyse functions are implemented by JUNG functions.
     * After analysing the graph and finding out the values the action is looking for,
     * the information is displayed or the found vertices/edges get highlighted.
     * The action is applied to all picked vertices/edges or to all objects if nothing is picked.
     *
     * @param pAnalyseTypeSeveral A list of AnalyseTypeSeveral, several types can be analysed at once.
     * @param counterEdges The number of incoming/outgoing edges to analyse.
     * @param counterVertex The number of adjacent vertices to analyse.
     * @param edgeType The edge type to analyse.
     */
    public AnalysisGraphAction(List<AnalyseTypeSeveral> pAnalyseTypeSeveral, int counterVertex, int counterEdges,
                               EdgeType edgeType) {
        throw new UnsupportedOperationException();
    }

    /**
     * Constructor in case the user chooses a AnalyseTypeSingle - analyse option.
     * These analyse functions are implemented by JGraphT algorithms and will be processed through the JGraphT Handler.
     * After processing and finding out the values the action is looking for, the information is displayed
     * or the found vertices/ edges get highlighted.
     * The action is applied to all picked vertices/ edges or to all objects if nothing is picked.
     * @param pAnalyseTypeSingle The analyse type, one type can be analysed at once.
     */
    public AnalysisGraphAction(AnalyseTypeSingle pAnalyseTypeSingle) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void action() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void undo() {
        throw new UnsupportedOperationException();
    }
}
