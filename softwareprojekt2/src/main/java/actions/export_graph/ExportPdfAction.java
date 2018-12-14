package actions.export_graph;

import actions.GraphAction;

public class ExportPdfAction extends GraphAction {

    /**
     * The path that the PDF is exported to
     */
    private String path;

    /**
     * The name of the file
     */
    private String name;

    /**
     * Action handling for exporting the graph as PDF
     *
     * @param pPath The path that the PDF is exported to
     * @param pName The name of the file
     */
    public ExportPdfAction(String pPath, String pName) {
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
