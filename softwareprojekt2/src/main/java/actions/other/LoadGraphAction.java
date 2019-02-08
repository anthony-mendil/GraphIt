package actions.other;

import actions.Action;
import actions.GraphAction;
import io.GXLio;
import log_management.DatabaseManager;

/**
 * Loads the existing graph to syndrom.
 */
public class LoadGraphAction extends GraphAction {
    /**
     * Loads an existing graph from a file to syndrom.
     */
    public LoadGraphAction() { }

    @Override
    public void action() {
        DatabaseManager databaseManager = DatabaseManager.getInstance();
        String gxlGraph = databaseManager.getGxlFromDatabase();
        Action.attach(databaseManager);

        GXLio gxLio = new GXLio();
        gxLio.gxlToInstance(gxlGraph,true);
        //System.out.println(gxlGraph);
        notifyObserverGraph();
    }

    /**
     * Disables the undo-function for the gxl export
     */
    @Override
    public void undo() {
        //there is no undo/redo operation for io
    }

    /**
     * Disables the redo-function for the gxl export
     */
    @Override
    public void redo() {
        //there is no undo/redo operation for io
    }
}
