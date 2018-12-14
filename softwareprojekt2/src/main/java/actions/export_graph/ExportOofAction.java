package actions.export_graph;

import actions.GraphAction;

public class ExportOofAction extends GraphAction {

    /**
     * The path that the OOF is exported to
     */
    private String path;

    /**
     * The name of the file
     */
    private String name;

    /**
     * Constructs Actionhandling for exporting the graph as Oof
     *
     * @param pPath The path that the OOF is exported to
     * @param pName The name of the file
     */
    public ExportOofAction(String pPath, String pName) {

    }

    /**
     * Executes the defined behavior of the action.
     */
    @Override
    public void action() {

    }

    /**
     * Reverts the action. The internal state of the graph is the same as before the action was executed.
     */
    @Override
    public void undo() {

    }
}
