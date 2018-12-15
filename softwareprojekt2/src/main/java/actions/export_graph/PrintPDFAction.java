package actions.export_graph;

import actions.GraphAction;

/*
 * prints the current graph visualisation as a pdf
 */
public class PrintPDFAction extends GraphAction {

    /**
     * The name of the file
     */
    private String name;

    /**
     * Actionhandling for printing the graph as PDF
     *
     * @param pName The name that the file is printed as
     */
    PrintPDFAction(String pName) {
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
