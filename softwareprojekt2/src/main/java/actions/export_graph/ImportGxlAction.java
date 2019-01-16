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
    public ImportGxlAction(String pPath) {
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
     * Disables the undo-funktion for the gxl export
     */
    @Override
    public void undo() {
    }

    /**
     * Disables the redo-funktion for the gxl export
     */
    @Override
    public void redo() {
    }
}
