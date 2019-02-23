package actions.analyse;

import actions.GraphAction;
import edu.uci.ics.jung.graph.util.EdgeType;
import graph.algorithmen.AnalyseTypeSeveral;
import graph.algorithmen.AnalyseTypeSingle;

import java.util.List;

/**
 * Analyses the graph in matter of heavily connected vertices or highly important vertices and creates statistics for
 * it.
 * <p> There are a few possibilities for the user to analyse the graph. We differentiate between two analyse types:
 * AnalyseTypeSeveral and AnalyseTypeSingle. The user can analyse the graph with multiple analysis types at once with multiple
 * AnalyseTypeSeveral selections. Unlike the AnalyseTypeSingle. Here is only one type selectable.
 * </p>
 */
public class EvaluationGraphAction extends GraphAction{

    /**
     * Constructor in case the user chooses a AnalyseTypeSeveral - analyse option.
     * These analyse functions are implemented by JUNG functions.
     * After analysing the graph and finding out the values the action is looking for,
     * the information is displayed or the found vertices/edges get highlighted.
     * The action is applied to all picked vertices/edges or to all objects if nothing is picked and statistics
     * are shown as vertices labels.
     *
     * @param pAnalyseTypeSeveral A list of AnalyseTypeSeveral, several types can be analysed at once.
     * @param edgeType The edge type.
     */
    public EvaluationGraphAction(List<AnalyseTypeSeveral> pAnalyseTypeSeveral, EdgeType edgeType) {
        throw new UnsupportedOperationException();
    }

    /**
     * Constructor in case the user chooses a AnalyseTypeSingle - analyse option.
     * These analyse functions are implemented by JGraphT algorithms and will be processed through the JGraphT Handler.
     * After processing and finding out the values the action is looking for, the information is displayed
     * or the found vertices/edges get highlighted.
     * The action is applied to all picked vertices/edges or to all objects if nothing is picked and statistics
     * are shown shown as vertices labels.
     * @param pAnalyseTypeSingle The analyse type, one type can be analysed at once.
     */
    public EvaluationGraphAction(AnalyseTypeSingle pAnalyseTypeSingle) {
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
