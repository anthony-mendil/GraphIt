package actions.export_graph;

import actions.GraphAction;

/*
   Aktionsleiste -> Datei.. -> Exportieren als.. -> GXL
 */
public class ExportGxlAction extends GraphAction {

    /**
     * The path that the GXL is exported to
     */
    private String path;

    /**
     * The name of the file
     */
    private String name;

    /**
     * Constructs actionhandling for exporting the graph as GXL
     *
     * @param pPath The path that the GXL is exported to
     * @param pName The name of the file
     */
    public ExportGxlAction(String pPath, String pName) {
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
