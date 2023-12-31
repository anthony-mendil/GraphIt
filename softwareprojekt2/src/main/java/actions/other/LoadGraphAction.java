package actions.other;

import actions.Action;
import actions.GraphAction;
import graph.graph.FunctionMode;
import gui.Controller;
import io.GXLio;
import log_management.DatabaseManager;

/**
 * Loads the existing graph to syndrom.
 * @author Clement Phung, Nina Unterberg
 */
public class LoadGraphAction extends GraphAction {
    private Controller controller;

    /**
     * Loads an existing graph from a file to syndrom.
     *
     * @param controller The unique controller of the syndrom.
     */
    public LoadGraphAction(Controller controller) {
        super();
        this.controller = controller;
    }

    @Override
    public void action() {
        actionHistory.wipe();
        DatabaseManager databaseManager = DatabaseManager.getInstance();
        String gxlGraph = databaseManager.getGxlFromDatabase();
        Action.attach(databaseManager);
        Action.attach(controller);
        GXLio gxLio = new GXLio();
        gxLio.gxlToInstance(gxlGraph, true);
        if (values.getMode() == FunctionMode.TEMPLATE || values.getMode() == FunctionMode.EDIT) {
            syndrom.setGraphMouseModeEdit();
        } else {
            syndrom.setGraphMouseModeAnalyse();
        }
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
