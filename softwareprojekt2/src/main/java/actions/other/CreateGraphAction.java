package actions.other;

import actions.Action;
import actions.GraphAction;
import graph.graph.FunctionMode;
import gui.Controller;
import log_management.DatabaseManager;

/**
 * Creates a new graph.
 *
 */
public class CreateGraphAction extends GraphAction {
    private String graphName;
    private Controller controller;


    /**
     * Constructor in case the user creates a new graph.
     *
     * @param pGraphName The name of the graph.
     */
    public CreateGraphAction(String pGraphName, Controller controller) {
       super();
       this.controller = controller;
       this.graphName=pGraphName;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void action() {
        syndrom.generateNew();
        if (values.getMode() == FunctionMode.TEMPLATE || values.getMode() == FunctionMode.EDIT) {
            syndrom.setGraphMouseModeEdit();
        } else {
            // FunctionMode.Analyse
            syndrom.setGraphMouseModeAnalyse();
        }
        actionHistory.wipe();
        DatabaseManager databaseManager = DatabaseManager.getInstance();
        Action.attach(databaseManager);
        Action.attach(controller);
        syndrom.setGraphName(graphName);
        notifyObserverNewGraph();
    }

    @Override
    public void undo() {
        //no undo operation for this action
    }
}
