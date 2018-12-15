package actions.export_graph;

import actions.GraphAction;

/**
 * imports an oof
 */
public class ImportOofAction extends GraphAction {

    /**
     * The path that the OOF is exported to
     */
    private String path;


    /**
     * Constructs Actionhandling for importing a graph as Oof
     *
     * @param pPath The path that the OOF is imported from
     */
    public ImportOofAction(String pPath) {

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
