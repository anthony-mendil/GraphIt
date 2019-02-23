package actions.analyse;

import actions.GraphAction;
import graph.algorithmen.SelectionType;

/**
 * Analyses only a selected section of the whole graph for:
 * 1. All nodes and edges that can be reached by the selection are appropriately visually highlighted
 * (with and without consideration of the edge direction).
 * 2. All nodes and edges on all paths between the selected elements are appropriately visually highlighted
 * (with and without consideration of the edge directions).
 */
public class SelectionAnalysisGraphAction extends GraphAction {
    /**
     * Constructor in case the user wants to analyse a section of the graph.
     * All picked vertices/ edges are analysed.
     *
     * @param selectionType The selection type.
     */
    public SelectionAnalysisGraphAction(SelectionType selectionType) {
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
