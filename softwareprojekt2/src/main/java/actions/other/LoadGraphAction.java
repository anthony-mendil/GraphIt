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
        //macht momenatn da gleiche wie createGraphAction. Muss noch geändert werden.
        syndrom.generateNew();

        DatabaseManager databaseManager = DatabaseManager.getInstance();
        Action.attach(databaseManager);
        notifyObserverNewGraph();

        String gxlGraph = databaseManager.getGxlFromDatabase();
        GXLio gxLio = new GXLio();
        gxLio.gxlToInstance(gxlGraph);
        //notifyObserverGraph();
        notifyObserverNewGraph();
    }

    /**
     * Disables the undo-funktion for the gxl export
     */
    @Override
    public void undo() {
        //there is no undo/redo operation for io
    }

    /**
     * Disables the redo-funktion for the gxl export
     */
    @Override
    public void redo() {
        //there is no undo/redo operation for io
    }
}
