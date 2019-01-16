package actions.export_graph;

import actions.GraphAction;

/**
 * Imports an OOF file.
 */
public class ImportOofAction extends GraphAction {

    /**
     * The path that the OOF file is exported to.
     */
    private String path;


    /**
     * Constructs action handling for importing a graph as OOF file.
     *
     * @param pPath The path that the OOF file is imported from.
     */
    public ImportOofAction(String pPath) {
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
     * Disables the undo-funktion for the oof export
     */
    @Override
    public void undo() {
    }

    /**
     * Disables the redo-funktion for the oof export
     */
    @Override
    public void redo() {
    }
}
