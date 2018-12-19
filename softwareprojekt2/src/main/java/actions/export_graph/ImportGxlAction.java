package actions.export_graph;

import actions.GraphAction;

/**
 * Imports a GXL file.
 */
public class ImportGxlAction extends GraphAction {

    /**
     * The path that the GXL file is exported to.
     */
    private String path;

    /**
     * Action handling for importing the graph as GXL file.
     *
     * @param pPath The path that the GXL file is imported from.
     */
    ImportGxlAction(String pPath) {

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
