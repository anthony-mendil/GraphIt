package actions.export_graph;

import actions.GraphAction;
import graph.graph.Syndrom;

/**
 * Prints the current graph visualization as a PDF file.
 */
public class PrintPDFAction extends GraphAction {

    /**
     * The name of the file.
     */
    private String name;

    /**
     * Action handling for printing the graph as PDF file.
     *
     * @param pName The name that the file is printed as.
     */
    PrintPDFAction(String pName) {
        Syndrom.getInstance().getVv().getPickedSphereState().clear();
        throw new UnsupportedOperationException();
    }

    /**
     * Executes the defined behavior of the action.
     */
    @Override
    public void action() {
        throw new UnsupportedOperationException();
    }

    /**
     * Reverts the action. The internal state of the graph is the same as before the action was executed.
     */
    @Override
    public void undo() {
        throw new UnsupportedOperationException();
    }
}
