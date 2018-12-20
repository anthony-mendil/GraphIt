package actions.export_graph;

import actions.GraphAction;

/**
 * Exports an OOF file with the current syndrom graph.
 */
public class ExportOofAction extends GraphAction {

    /**
     * The path that the OOF file is exported to.
     */
    private String path;

    /**
     * The name of the file.
     */
    private String name;

    /**
     * Constructs action handling for exporting the graph as OOF file.
     *
     * @param pPath The path that the OOF file is exported to.
     * @param pName The name of the file.
     */
    public ExportOofAction(String pPath, String pName) {
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
