package actions.analyse;

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
public class AnalysisGraphAction {
    /**
     * the list of analyse types, several types can be passed
     */
    private List<AnalyseTypeSeveral> analyseTypeSeverals;

    /**
     * a single analyse type
     */
    private AnalyseTypeSingle analyseTypeSingle;

    /**
     * Constructor in case the user chooses a AnalyseTypeSeveral - analyse option.
     * These analyse functions are implemented by Jung functions.
     * After analysing the graph and finding out the values the action is looking for,
     * the information is displayed or the found vertices/ edges get highlighted.
     * The action is applied to all picked vertices/ edges or to all objects if nothing is picked.
     *
     * @param pAnalyseTypeSeveral a list of AnalyseTypeSeveral
     */
    public AnalysisGraphAction(List<AnalyseTypeSeveral> pAnalyseTypeSeveral) {
        throw new UnsupportedOperationException();
    }

    /**
     * Constructor in case the user chooses a AnalyseTypeSingle - analyse option.
     * These analyse functions are implemented by JGraphT algorithms and will be processed through the JGraphT Handler.
     * After processing and finding out the values the action is looking for, the information is displayed
     * or the found vertices/ edges get highlighted.
     * The action is applied to all picked vertices/ edges or to all objects if nothing is picked.
     * @param pAnalyseTypeSingle the analyse type
     */
    public AnalysisGraphAction(AnalyseTypeSingle pAnalyseTypeSingle) {
        throw new UnsupportedOperationException();
    }
}
